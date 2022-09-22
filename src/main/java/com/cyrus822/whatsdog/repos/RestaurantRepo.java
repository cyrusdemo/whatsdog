package com.cyrus822.whatsdog.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import com.cyrus822.whatsdog.models.Restaurants;

public interface RestaurantRepo extends JpaRepository<Restaurants, Integer> {

    @Query(value="exec GetRestaurants @name=:name, @addr=:addr", nativeQuery=true)
    List<Restaurants> getRestaurants(@Param("name") String name, @Param("addr") String addr);

    @Procedure(procedureName = "dbo.GetRestaurants")
    List<Restaurants> getRestaurantsJPA(@Param("name") String name, @Param("addr") String addr);
}
