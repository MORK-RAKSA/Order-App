package com.food.order.client;


import com.food.order.config.TelegramProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TelegramRestClient {

    private final TelegramProperties telegramProperties;
    private final RestTemplate restTemplate;

    public void sendMessage(String messageGroup, String messageOwner) {
        String url = "https://api.telegram.org/bot" + telegramProperties.getBotToken() + "/sendMessage";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> bodyGroup = Map.of("chat_id", telegramProperties.getChatId(), "text", messageGroup);
        HttpEntity<Map<String, String>> requestToGroup = new HttpEntity<>(bodyGroup, headers);

        Map<String, String> bodyOwner = Map.of("chat_id", telegramProperties.getOwnerChatId(), "text", messageOwner);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(bodyOwner, headers);

        restTemplate.postForEntity(url, requestToGroup, String.class);
        restTemplate.postForEntity(url, request, String.class);
    }
}
