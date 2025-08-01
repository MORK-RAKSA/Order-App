package com.food.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Value("${app.APP_URL}")
    private String appUrl;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("appUrl", appUrl.toString());
        return "index";
    }
    @GetMapping("/management")
    public String managementPage(Model model) {
        model.addAttribute("appUrl", appUrl.toString());
        return "management";
    }
}
