package com.food.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.order.model.Food;

public interface FoodRepository extends JpaRepository<Food, String> {}
