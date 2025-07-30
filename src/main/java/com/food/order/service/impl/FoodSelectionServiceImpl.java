package com.food.order.service.impl;


import com.food.order.controller.dto.FoodRequestDto;
import com.food.order.controller.dto.FoodResponseDto;
import com.food.order.model.Food;
import com.food.order.model.UserSelection;
import com.food.order.model.mapper.FoodMapper;
import com.food.order.repository.FoodRepository;
import com.food.order.service.FoodSelectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
// import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class FoodSelectionServiceImpl implements FoodSelectionService {
    // private final Map<String, Food> availableFoods = new LinkedHashMap<>();
    private final Map<String, UserSelection> selections = new ConcurrentHashMap<>();
    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;

//    public List<Food> getAllFoods() {
//        return new ArrayList<>(availableFoods.values());
//    }

    public void saveTempSelection(UserSelection selection) {
        String key = selection.getUserId().trim().toLowerCase();
        selections.put(key, selection);
    }

    public void clearUser(String userId) {
        String key = userId.trim().toLowerCase();
        System.out.println("Trying to clear key: " + key);
        System.out.println("Stored keys before clear: " + selections.keySet());
        selections.remove(key);
        System.out.println("Stored keys after clear: " + selections.keySet());
    }


    public List<UserSelection> getAllSelections() {
        return new ArrayList<>(selections.values());
    }

    public FoodResponseDto addFood(FoodRequestDto requestDto) {
        Food entity = foodMapper.toRequestDto(requestDto);
        Food saved = foodRepository.save(entity);
        return foodMapper.toResponseDto(saved);
    }

    public List<FoodResponseDto> getAllFood() {
        List<Food> entity = foodRepository.findAll();
        return entity.stream()
                .map(foodMapper::toResponseDto)
                .toList();
    }

    public void clearAll() {
        selections.clear();
    }

}
