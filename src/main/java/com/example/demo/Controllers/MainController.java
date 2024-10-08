package com.example.demo.Controllers;

import com.example.demo.models.Arina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.repo.listRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
@Autowired
private listRepo listRepo;
    @GetMapping("/")
    public String home(Model model) {
        Iterable<Arina> lists = listRepo.findAll();
        model.addAttribute("list", lists);
        model.addAttribute("title", "Бібліотека Онлайн");
        return "home";
    }

    @GetMapping("/insert")
    public String insert(Model model) {
        model.addAttribute("title", "Додавання Книг");
        return "insert";
    }

    @PostMapping("/insert")
    public String sending(@RequestParam String title, @RequestParam String author,@RequestParam String status,@RequestParam int ISBN,@RequestParam int value,Model model){
         Arina arina = new Arina(title, author, status,ISBN,value);
        listRepo.save(arina);
        return "redirect:/";
    }



    @GetMapping("/give")
    public String give(Model model) {
        model.addAttribute("title", "Видача Книг");
        return "give";
    }



}