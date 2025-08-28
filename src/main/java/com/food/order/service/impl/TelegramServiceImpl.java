package com.food.order.service.impl;

import com.food.order.client.TelegramRestClient;
import com.food.order.model.UserSelection;
import com.food.order.service.FoodSelectionService;
import com.food.order.service.TelegramService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RequiredArgsConstructor
@Service
public class TelegramServiceImpl implements TelegramService {

    private final FoodSelectionService foodSelectionService;
    private final TelegramRestClient telegramRestClient;

    public void sendMessage(List<UserSelection> selections) {

        if (ObjectUtils.isEmpty(selections)) {
            throw new RuntimeException();
        }
        
        String messageGroup = buildMsgTemplateForGroup(selections);
        String messageOwner = buildMsgTemplateForOwner(selections);
        telegramRestClient.sendMessage(messageGroup, messageOwner);
        foodSelectionService.clearAll();

    }

    private static String buildMsgTemplateForGroup(List<UserSelection> selections) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = now.format(formatter);

        StringBuilder msg = new StringBuilder();
        msg.append("Order Summary")
        .append("\nDate: ").append(formattedTime)
        .append("\nTotal Users: ").append(selections.size())
        .append("\n-----------------------------------");

        int index = 1;
        for (UserSelection sel : selections) {
            msg.append("\n")
            .append(index++).append(".     User:\t").append(sel.getUserId())
            .append("\n        Order:\t").append(sel.getName());

            if (sel.getVariant() != null && !sel.getVariant().isBlank()) {
                msg.append(" (").append(sel.getVariant()).append(")");
            }

            if (sel.getNote() != null && !sel.getNote().isBlank()) {
                msg.append("\n        Note:\t").append(sel.getNote());
            }

        }
        msg.append("\n-----------------------------------");
        return msg.toString();
    }


    private static String buildMsgTemplateForOwner(List<UserSelection> selections) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = now.format(formatter);

        StringBuilder msg = new StringBuilder();

        msg.append(String.format("កុម្ម៉ង់ពី: 086252502"))
                .append("\nកាលបរិច្ឆេទ: ").append(formattedTime)
                .append("\nមនុស្សសរុប: ").append(selections.size())
                .append("\n-----------------------------------");

        int index = 1;
        msg.append("\n. មុខម្ហូប:");
        for (UserSelection sel : selections) {
            msg.append(String.format("\n%d.      ", index++)).append(sel.getName());

            if (sel.getVariant() != null && !sel.getVariant().isBlank()) {
                msg.append(" (").append(sel.getVariant()).append(")");
            }

            if (sel.getNote() != null && !sel.getNote().isBlank()) {
                msg.append("\n         កំណត់ចំណាំ:\t").append(sel.getNote());
            }
        }
        msg.append("\n-----------------------------------");
        return msg.toString();
    }

    @Scheduled(fixedRateString = "PT10S")
    public void sendTestMessage() {
        telegramRestClient.sendMessage("Test Message from Spring Boot Application", "Test Message from Spring Boot Application");
    }

}




