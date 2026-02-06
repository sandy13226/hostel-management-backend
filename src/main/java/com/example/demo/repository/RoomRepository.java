package com.example.demo.repository;

import com.example.demo.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByHostelId(Long hostelId);
    
    @Query("SELECT r FROM Room r WHERE r.hostel.id = :hostelId AND r.occupiedBeds < r.capacity")
    List<Room> findAvailableRoomsByHostelId(@Param("hostelId") Long hostelId);
}
