package com.example.demo.service;

import com.example.demo.entity.Allocation;
import com.example.demo.entity.Room;
import com.example.demo.entity.Student;
import com.example.demo.repository.AllocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AllocationService {
    
    @Autowired
    private AllocationRepository allocationRepository;
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private RoomService roomService;
    
    @Autowired
    private HostelService hostelService;
    
    public List<Allocation> getAllAllocations() {
        return allocationRepository.findAll();
    }
    
    public Allocation getAllocationById(Long id) {
        return allocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Allocation not found with id: " + id));
    }
    
    public List<Allocation> getAllocationsByStudentId(Long studentId) {
        return allocationRepository.findByStudentId(studentId);
    }
    
    public List<Allocation> getAllocationsByRoomId(Long roomId) {
        return allocationRepository.findByRoomId(roomId);
    }
    
    public List<Allocation> getActiveAllocations() {
        return allocationRepository.findByStatus("ACTIVE");
    }
    
    @Transactional
    public Allocation allocateRoom(Long studentId, Long roomId) {
        Student student = studentService.getStudentById(studentId);
        Room room = roomService.getRoomById(roomId);
        
        // Check if student already has an active allocation
        if (allocationRepository.findByStudentIdAndStatus(studentId, "ACTIVE").isPresent()) {
            throw new RuntimeException("Student already has an active room allocation");
        }
        
        // Check if room has available beds
        if (!room.isAvailable()) {
            throw new RuntimeException("Room is full, no beds available");
        }
        
        // Create allocation
        Allocation allocation = new Allocation();
        allocation.setStudent(student);
        allocation.setRoom(room);
        allocation.setAllocationDate(LocalDate.now());
        allocation.setStatus("ACTIVE");
        
        // Update room and hostel occupancy
        roomService.updateOccupancy(roomId, 1);
        hostelService.updateOccupancy(room.getHostel().getId(), 1);
        
        return allocationRepository.save(allocation);
    }
    
    @Transactional
    public Allocation checkIn(Long allocationId, LocalDate checkInDate) {
        Allocation allocation = getAllocationById(allocationId);
        allocation.setCheckInDate(checkInDate);
        return allocationRepository.save(allocation);
    }
    
    @Transactional
    public Allocation checkOut(Long allocationId, LocalDate checkOutDate) {
        Allocation allocation = getAllocationById(allocationId);
        allocation.setCheckOutDate(checkOutDate);
        allocation.setStatus("CHECKED_OUT");
        
        // Update room and hostel occupancy
        roomService.updateOccupancy(allocation.getRoom().getId(), -1);
        hostelService.updateOccupancy(allocation.getRoom().getHostel().getId(), -1);
        
        return allocationRepository.save(allocation);
    }
    
    @Transactional
    public void deleteAllocation(Long id) {
        Allocation allocation = getAllocationById(id);
        
        // If allocation is active, update occupancy
        if ("ACTIVE".equals(allocation.getStatus())) {
            roomService.updateOccupancy(allocation.getRoom().getId(), -1);
            hostelService.updateOccupancy(allocation.getRoom().getHostel().getId(), -1);
        }
        
        allocationRepository.delete(allocation);
    }
}
