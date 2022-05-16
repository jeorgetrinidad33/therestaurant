package com.example.therestaurant.repository;

import com.example.therestaurant.entity.Cart;
import com.example.therestaurant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    public List<Cart> findByCustomer(User customer);
}