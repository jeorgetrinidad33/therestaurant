package com.example.therestaurant.repository;

import com.example.therestaurant.entity.Category;
import com.example.therestaurant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<User> findFirstByNameIsNotNull();
}