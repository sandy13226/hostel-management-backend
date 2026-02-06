package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "hostel_id", nullable = false)
    private Hostel hostel;
    
    @Column(nullable = false)
    private String roomNumber;
    
    @Column(nullable = false)
    private Integer capacity;
    
    @Column(nullable = false)
    private Integer occupiedBeds = 0;
    
    private String floor;
    
    private String roomType; // Single/Double/Triple/Quad
    
    // Constructors
    public Room() {
    }
    
    public Room(Hostel hostel, String roomNumber, Integer capacity, String floor, String roomType) {
        this.hostel = hostel;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.floor = floor;
        this.roomType = roomType;
        this.occupiedBeds = 0;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Hostel getHostel() {
        return hostel;
    }
    
    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }
    
    public String getRoomNumber() {
        return roomNumber;
    }
    
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    public Integer getCapacity() {
        return capacity;
    }
    
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    
    public Integer getOccupiedBeds() {
        return occupiedBeds;
    }
    
    public void setOccupiedBeds(Integer occupiedBeds) {
        this.occupiedBeds = occupiedBeds;
    }
    
    public String getFloor() {
        return floor;
    }
    
    public void setFloor(String floor) {
        this.floor = floor;
    }
    
    public String getRoomType() {
        return roomType;
    }
    
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    
    public Integer getAvailableBeds() {
        return capacity - occupiedBeds;
    }
    
    public boolean isAvailable() {
        return occupiedBeds < capacity;
    }
}
