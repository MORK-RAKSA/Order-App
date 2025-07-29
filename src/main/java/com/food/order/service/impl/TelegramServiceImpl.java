package com.food.order.service.impl;

import com.food.order.client.TelegramRestClient;
import com.food.order.model.UserSelection;
import com.food.order.service.FoodSelectionService;
import com.food.order.service.TelegramService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
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

        StringBuilder msg = new StringBuilder();

        msg.append("Order Summary")
                .append("\nDate: ").append(LocalDate.now())
                .append("\nTotal Users: ").append(selections.size())
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
        return msg.toString();
    }

    private static String buildMsgTemplateForOwner(List<UserSelection> selections) {

        StringBuilder msg = new StringBuilder();

        msg.append("បញ្ជាទិញពីក្រុមបុគ្គលិកវីងអាយធី")
                .append("\nកាលបរិច្ឆេទ: ").append(LocalDate.now())
                .append("\nមនុស្សសរុប: ").append(selections.size())
                .append("\n_____________________________\n");

        int index = 1;
        for (UserSelection sel : selections) {
            msg.append("\n").append(index++).append(": ").append(sel.getUserId())
                    .append("\nបញ្ជាទិញ: ").append(sel.getName());

            if (sel.getVariant() != null && !sel.getVariant().isBlank()) {
                msg.append(" (").append(sel.getVariant()).append(")");
            }

            if (sel.getNote() != null && !sel.getNote().isBlank()) {
                msg.append("\nកំណត់ចំណាំ: ").append(sel.getNote());
            }

            msg.append("\n_____________________________");
        }
        return msg.toString();
    }

}




