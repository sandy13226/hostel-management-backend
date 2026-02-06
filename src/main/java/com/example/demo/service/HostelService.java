package com.example.demo.service;

import com.example.demo.entity.Hostel;
import com.example.demo.repository.HostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostelService {
    
    @Autowired
    private HostelRepository hostelRepository;
    
    public List<Hostel> getAllHostels() {
        return hostelRepository.findAll();
    }
    
    public Hostel getHostelById(Long id) {
        return hostelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hostel not found with id: " + id));
    }
    
    public List<Hostel> getHostelsByType(String type) {
        return hostelRepository.findByType(type);
    }
    
    public Hostel createHostel(Hostel hostel) {
        if (hostelRepository.existsByName(hostel.getName())) {
            throw new RuntimeException("Hostel with name " + hostel.getName() + " already exists");
        }
        return hostelRepository.save(hostel);
    }
    
    public Hostel updateHostel(Long id, Hostel hostelDetails) {
        Hostel hostel = getHostelById(id);
        
        hostel.setName(hostelDetails.getName());
        hostel.setType(hostelDetails.getType());
        hostel.setTotalRooms(hostelDetails.getTotalRooms());
        hostel.setTotalCapacity(hostelDetails.getTotalCapacity());
        hostel.setWarden(hostelDetails.getWarden());
        hostel.setAddress(hostelDetails.getAddress());
        hostel.setFacilities(hostelDetails.getFacilities());
        
        return hostelRepository.save(hostel);
    }
    
    public void deleteHostel(Long id) {
        Hostel hostel = getHostelById(id);
        hostelRepository.delete(hostel);
    }
    
    public void updateOccupancy(Long hostelId, int change) {
        Hostel hostel = getHostelById(hostelId);
        hostel.setOccupiedCapacity(hostel.getOccupiedCapacity() + change);
        hostelRepository.save(hostel);
    }
}
