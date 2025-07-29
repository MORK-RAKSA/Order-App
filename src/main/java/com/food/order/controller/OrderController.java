package com.food.order.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.food.order.controller.dto.FoodRequestDto;
import com.food.order.controller.dto.FoodResponseDto;
import com.food.order.model.UserSelection;
import com.food.order.service.FoodSelectionService;
import com.food.order.service.TelegramService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final FoodSelectionService foodSelectionService;
    private final TelegramService telegramService;
    
    @PostMapping("/save-temp-selection")
    public ResponseEntity<Void> saveTemp(@RequestBody UserSelection selection) {
        foodSelectionService.saveTempSelection(selection);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/temp-selections")
    public List<UserSelection> getSelections() {
        return foodSelectionService.getAllSelections();
    }

    @DeleteMapping("/clear-temp-selection/{userId}")
    public ResponseEntity<Void> clearUser(@PathVariable("userId") String userId) {
        foodSelectionService.clearUser(userId);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/add-food")
    public ResponseEntity<FoodResponseDto> addFood(@RequestBody FoodRequestDto requestDto){
        FoodResponseDto responseDto = foodSelectionService.addFood(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<FoodResponseDto>> getAllFood(){
        return ResponseEntity.ok(foodSelectionService.getAllFood());
    }

    @PostMapping("/submit-all")
    public ResponseEntity<String> submitAll(@RequestBody List<UserSelection> selections) {

        if (selections.isEmpty()) {
            return null;
        }

        int userCount = selections.size();

        StringBuilder msg = new StringBuilder();
        msg.append("Order Summary")
        .append("\nDate: ").append(LocalDate.now())
        .append("\nTotal Users: ").append(userCount)
        .append("\n_____________________________\n");

        int index = 1;
        for (UserSelection sel : selections) {
            msg.append("\n").append(index++).append(": ").append(sel.getUserId())
            .append("\nOrder: ").append(sel.getName());

            if (sel.getVariant() != null && !sel.getVariant().isBlank()) {
                msg.append(" (").append(sel.getVariant()).append(")");
            }

            if (sel.getNote() != null && !sel.getNote().isBlank()) {
                msg.append("\nNote: ").append(sel.getNote());
            }

            msg.append("\n_____________________________");
        }


        telegramService.sendMessage(msg.toString());
        foodSelectionService.clearAll();

        return ResponseEntity.ok("Submitted to Telegram");
    }

}
