package com.project.foms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.foms.entity.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Integer>{
    
    Optional<List<MenuItem>> findByPriceGreaterThan(int price);
    Optional<MenuItem> findByItemName(String itemName);
}
