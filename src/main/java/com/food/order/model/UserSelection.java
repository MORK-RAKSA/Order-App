package com.food.order.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSelection {
    private String userId;
    private String id;
    private String name;
    private LocalDate orderDate;
    private String variant;
    private String note;
}
