package com.food.order.controller.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodResponseDto {
    private String id;
    private String name;
    private List<String> variants;
}
