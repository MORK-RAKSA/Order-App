// package com.food.order.service.impl;

// import org.springframework.stereotype.Component;
// import org.telegram.telegrambots.bots.TelegramLongPollingBot;
// import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
// import org.telegram.telegrambots.meta.api.objects.Update;

// @Component
// public class MyBot extends TelegramLongPollingBot {

//     private static final long ALLOWED_USER_ID = 123456789L; // Your chosen user
//     private static final long TARGET_GROUP_ID = -1009876543210L; // Your group chat ID (negative number for groups)

//     @Override
//     public String getBotUsername() {
//         return "my_spring_bot";
//     }

//     @Override
//     public String getBotToken() {
//         return System.getenv("TELEGRAM_BOT_TOKEN");
//     }

//     @Override
//     public void onUpdateReceived(Update update) {
//         if (update.hasMessage() && update.getMessage().hasText()) {
//             long fromUserId = update.getMessage().getFrom().getId();
//             if (fromUserId == ALLOWED_USER_ID) { // filter for specific user
//                 try {
//                     execute(ForwardMessage.builder()
//                         .fromChatId(update.getMessage().getChatId().toString()) // the chat where message came from
//                         .chatId(String.valueOf(TARGET_GROUP_ID)) // your target group
//                         .messageId(update.getMessage().getMessageId()) // ID of the message to forward
//                         .build());
//                 } catch (Exception e) {
//                     e.printStackTrace();
//                 }
//             }
//         }
//     }
// }
