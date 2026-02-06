package com.example.demo.repository;

import com.example.demo.entity.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {
    List<Allocation> findByStudentId(Long studentId);
    List<Allocation> findByRoomId(Long roomId);
    List<Allocation> findByStatus(String status);
    Optional<Allocation> findByStudentIdAndStatus(Long studentId, String status);
}
