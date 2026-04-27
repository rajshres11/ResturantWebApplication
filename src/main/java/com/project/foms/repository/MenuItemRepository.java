package com.project.foms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.foms.entity.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Integer>{
    
}
