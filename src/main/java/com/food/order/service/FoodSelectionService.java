package com.food.order.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.food.order.controller.dto.FoodRequestDto;
import com.food.order.controller.dto.FoodResponseDto;
import com.food.order.model.Food;
import com.food.order.model.UserSelection;
import com.food.order.model.mapper.FoodMapper;
import com.food.order.repository.FoodRepository;

import lombok.RequiredArgsConstructor;


public interface FoodSelectionService {

//     List<Food> getAllFoods();

     void saveTempSelection(UserSelection selection);

     void clearUser(String userId);

     List<UserSelection> getAllSelections();

     FoodResponseDto addFood(FoodRequestDto requestDto);

     List<FoodResponseDto> getAllFood();

     void clearAll();

}
