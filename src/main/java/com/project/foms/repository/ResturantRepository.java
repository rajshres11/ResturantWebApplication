package com.project.foms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.foms.entity.Resturant;

@Repository
public interface ResturantRepository extends JpaRepository<Resturant, Integer> {

    List<Resturant> findByLocation(String location);

    // Optional is for handle exception.
    Optional<Resturant> findByResturantName(String resturantName);
}
