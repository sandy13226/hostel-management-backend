package com.example.demo.repository;

import com.example.demo.entity.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HostelRepository extends JpaRepository<Hostel, Long> {
    Optional<Hostel> findByName(String name);
    List<Hostel> findByType(String type);
    boolean existsByName(String name);
}
