package com.example.therestaurant.repository;

import com.example.therestaurant.entity.Menu;
import com.example.therestaurant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    Optional<User> findFirstByNameIsNotNull();
}