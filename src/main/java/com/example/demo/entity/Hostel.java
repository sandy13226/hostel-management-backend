package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hostels")
public class Hostel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, name = "hostel_name")
    private String name;
    
    @Column(nullable = false, name = "hostel_type")
    private String type; // Boys/Girls
    
    @Column(nullable = false)
    private Integer totalRooms;
    
    @Column(nullable = false)
    private Integer totalCapacity;
    
    private Integer occupiedCapacity = 0;
    
    private String warden;
    
    private String address;
    
    private String facilities;
    
    // Constructors
    public Hostel() {
    }
    
    public Hostel(String name, String type, Integer totalRooms, Integer totalCapacity) {
        this.name = name;
        this.type = type;
        this.totalRooms = totalRooms;
        this.totalCapacity = totalCapacity;
        this.occupiedCapacity = 0;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Integer getTotalRooms() {
        return totalRooms;
    }
    
    public void setTotalRooms(Integer totalRooms) {
        this.totalRooms = totalRooms;
    }
    
    public Integer getTotalCapacity() {
        return totalCapacity;
    }
    
    public void setTotalCapacity(Integer totalCapacity) {
        this.totalCapacity = totalCapacity;
    }
    
    public Integer getOccupiedCapacity() {
        return occupiedCapacity;
    }
    
    public void setOccupiedCapacity(Integer occupiedCapacity) {
        this.occupiedCapacity = occupiedCapacity;
    }
    
    public String getWarden() {
        return warden;
    }
    
    public void setWarden(String warden) {
        this.warden = warden;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getFacilities() {
        return facilities;
    }
    
    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }
    
    public Integer getAvailableCapacity() {
        return totalCapacity - occupiedCapacity;
    }
}
