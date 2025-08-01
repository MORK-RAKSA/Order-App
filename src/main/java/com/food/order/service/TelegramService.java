package com.food.order.service;
import com.food.order.model.UserSelection;
import java.util.List;


public interface TelegramService {

     void sendMessage(List<UserSelection> selections,String phone);

}
