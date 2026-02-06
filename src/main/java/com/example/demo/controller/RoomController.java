package com.example.demo.controller;

import com.example.demo.entity.Room;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "https://hostel-management-frontend-beta.vercel.app")
public class RoomController {
    
    @Autowired
    private RoomService roomService;
    
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(roomService.getRoomById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/hostel/{hostelId}")
    public ResponseEntity<List<Room>> getRoomsByHostelId(@PathVariable Long hostelId) {
        return ResponseEntity.ok(roomService.getRoomsByHostelId(hostelId));
    }
    
    @GetMapping("/hostel/{hostelId}/available")
    public ResponseEntity<List<Room>> getAvailableRoomsByHostelId(@PathVariable Long hostelId) {
        return ResponseEntity.ok(roomService.getAvailableRoomsByHostelId(hostelId));
    }
    
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody Room room) {
        try {
            Room created = roomService.createRoom(room);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable Long id, @RequestBody Room room) {
        try {
            Room updated = roomService.updateRoom(id, room);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        try {
            roomService.deleteRoom(id);
            return ResponseEntity.ok().body("Room deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
