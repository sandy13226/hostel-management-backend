package com.example.demo.controller;

import com.example.demo.entity.Allocation;
import com.example.demo.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/allocations")
@CrossOrigin(origins = "http://localhost:3000")
public class AllocationController {
    
    @Autowired
    private AllocationService allocationService;
    
    @GetMapping
    public ResponseEntity<List<Allocation>> getAllAllocations() {
        return ResponseEntity.ok(allocationService.getAllAllocations());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Allocation> getAllocationById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(allocationService.getAllocationById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Allocation>> getAllocationsByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(allocationService.getAllocationsByStudentId(studentId));
    }
    
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<Allocation>> getAllocationsByRoomId(@PathVariable Long roomId) {
        return ResponseEntity.ok(allocationService.getAllocationsByRoomId(roomId));
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<Allocation>> getActiveAllocations() {
        return ResponseEntity.ok(allocationService.getActiveAllocations());
    }
    
    @PostMapping("/allocate")
    public ResponseEntity<?> allocateRoom(@RequestBody Map<String, Long> request) {
        try {
            Long studentId = request.get("studentId");
            Long roomId = request.get("roomId");
            Allocation allocation = allocationService.allocateRoom(studentId, roomId);
            return ResponseEntity.status(HttpStatus.CREATED).body(allocation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}/checkin")
    public ResponseEntity<?> checkIn(@PathVariable Long id, @RequestBody Map<String, String> request) {
        try {
            LocalDate checkInDate = request.containsKey("checkInDate") 
                ? LocalDate.parse(request.get("checkInDate"))
                : LocalDate.now();
            Allocation allocation = allocationService.checkIn(id, checkInDate);
            return ResponseEntity.ok(allocation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}/checkout")
    public ResponseEntity<?> checkOut(@PathVariable Long id, @RequestBody Map<String, String> request) {
        try {
            LocalDate checkOutDate = request.containsKey("checkOutDate")
                ? LocalDate.parse(request.get("checkOutDate"))
                : LocalDate.now();
            Allocation allocation = allocationService.checkOut(id, checkOutDate);
            return ResponseEntity.ok(allocation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAllocation(@PathVariable Long id) {
        try {
            allocationService.deleteAllocation(id);
            return ResponseEntity.ok().body("Allocation deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
