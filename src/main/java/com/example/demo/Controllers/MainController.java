package com.example.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Бібліотека Онлайн");
        return "home";
    }
    @GetMapping("/insert")
    public String insert(Model model) {
        model.addAttribute("title", "Добавленя Книг");
        return "insert";
    }


}