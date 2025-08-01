package com.food.order.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.food.order.controller.dto.FoodRequestDto;
import com.food.order.controller.dto.FoodResponseDto;
import com.food.order.model.UserSelection;
import com.food.order.service.FoodSelectionService;
import com.food.order.service.TelegramService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    @Value("${app.APP_URL}")
    private String appUrl;
    
    @GetMapping("/launch")
    public String launchPage(Model model) {
        model.addAttribute("appUrl", appUrl.toString());
        return "launch";
    }

    private final FoodSelectionService foodSelectionService;
    private final TelegramService telegramService;
    
    @PostMapping("/save-temp-selection")
    public ResponseEntity<Void> saveTemp(@RequestBody UserSelection selection) {
        foodSelectionService.saveTempSelection(selection);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> saveTemp(@RequestParam String id) {
        foodSelectionService.deleteById(id);
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
    public ResponseEntity<String> submitAll(@RequestBody List<UserSelection> selections, String phone) {
        telegramService.sendMessage(selections,phone);
        return ResponseEntity.ok("Submitted to Telegram");
    }

}
