package com.example.demo.service;

import com.example.demo.entity.Hostel;
import com.example.demo.entity.Room;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    
    @Autowired
    private RoomRepository roomRepository;
    
    @Autowired
    private HostelService hostelService;
    
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
    
    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }
    
    public List<Room> getRoomsByHostelId(Long hostelId) {
        return roomRepository.findByHostelId(hostelId);
    }
    
    public List<Room> getAvailableRoomsByHostelId(Long hostelId) {
        return roomRepository.findAvailableRoomsByHostelId(hostelId);
    }
    
    public Room createRoom(Room room) {
        Hostel hostel = hostelService.getHostelById(room.getHostel().getId());
        room.setHostel(hostel);
        return roomRepository.save(room);
    }
    
    public Room updateRoom(Long id, Room roomDetails) {
        Room room = getRoomById(id);
        
        room.setRoomNumber(roomDetails.getRoomNumber());
        room.setCapacity(roomDetails.getCapacity());
        room.setFloor(roomDetails.getFloor());
        room.setRoomType(roomDetails.getRoomType());
        
        return roomRepository.save(room);
    }
    
    public void deleteRoom(Long id) {
        Room room = getRoomById(id);
        roomRepository.delete(room);
    }
    
    public void updateOccupancy(Long roomId, int change) {
        Room room = getRoomById(roomId);
        room.setOccupiedBeds(room.getOccupiedBeds() + change);
        roomRepository.save(room);
    }
}
