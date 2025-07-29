package com.food.order.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.food.order.config.TelegramProperties;
import lombok.RequiredArgsConstructor;
import java.util.Map;

import org.springframework.http.*;

@RequiredArgsConstructor
@Service
public class TelegramService {

    private final TelegramProperties telegramProperties;

    public void sendMessage(String message) {
        String url = "https://api.telegram.org/bot" + telegramProperties.getBotToken() + "/sendMessage";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = Map.of(
                "chat_id", telegramProperties.getChatId(),
                "text", message
        );

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        new RestTemplate().postForEntity(url, request, String.class);
    }

}
