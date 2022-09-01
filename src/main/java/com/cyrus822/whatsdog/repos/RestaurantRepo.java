package com.cyrus822.whatsdog.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyrus822.whatsdog.models.Restaurants;

public interface RestaurantRepo extends JpaRepository<Restaurants, Integer> {
    
}
