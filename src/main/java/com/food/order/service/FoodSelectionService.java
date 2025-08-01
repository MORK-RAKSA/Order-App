package com.food.order.service;

import java.util.List;
import com.food.order.controller.dto.FoodRequestDto;
import com.food.order.controller.dto.FoodResponseDto;
import com.food.order.model.UserSelection;


public interface FoodSelectionService {

//     List<Food> getAllFoods();

     void saveTempSelection(UserSelection selection);

     void clearUser(String userId);

     List<UserSelection> getAllSelections();

     FoodResponseDto addFood(FoodRequestDto requestDto);

     List<FoodResponseDto> getAllFood();

     void clearAll();

     void deleteById(String id);

}
