package com.example.demo.controller;

import com.example.demo.entity.Hostel;
import com.example.demo.service.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hostels")
@CrossOrigin(origins = "https://hostel-management-frontend-beta.vercel.app")
public class HostelController {
    
    @Autowired
    private HostelService hostelService;
    
    @GetMapping
    public ResponseEntity<List<Hostel>> getAllHostels() {
        return ResponseEntity.ok(hostelService.getAllHostels());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Hostel> getHostelById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(hostelService.getHostelById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Hostel>> getHostelsByType(@PathVariable String type) {
        return ResponseEntity.ok(hostelService.getHostelsByType(type));
    }
    
    @PostMapping
    public ResponseEntity<?> createHostel(@RequestBody Hostel hostel) {
        try {
            Hostel created = hostelService.createHostel(hostel);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateHostel(@PathVariable Long id, @RequestBody Hostel hostel) {
        try {
            Hostel updated = hostelService.updateHostel(id, hostel);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHostel(@PathVariable Long id) {
        try {
            hostelService.deleteHostel(id);
            return ResponseEntity.ok().body("Hostel deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
