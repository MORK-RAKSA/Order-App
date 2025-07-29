package com.food.order.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.food.order.controller.dto.FoodRequestDto;
import com.food.order.controller.dto.FoodResponseDto;
import com.food.order.model.Food;

@Mapper(
    componentModel = "spring"
)
public interface FoodMapper {

    @Mapping(target = "id", ignore = true)
    Food toRequestDto(FoodRequestDto dto);

    FoodResponseDto toResponseDto(Food entity);
    
}
