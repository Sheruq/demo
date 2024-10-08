package com.example.demo.Controllers;

import com.example.demo.models.Arina;
import com.example.demo.repo.AriRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {

    @Autowired
    private AriRepo ariRepo;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Arina> Arinass = ariRepo.findAll();
        model.addAttribute("Arinass", Arinass);
        return "home";
    }

    @GetMapping("/insert")
    public String insertadd(Model model) {
        return "insert";
    }


    @PostMapping("/insert")
    public String insertPostAdd(@RequestParam String title, @RequestParam String author, @RequestParam String status, @RequestParam int ISBN, @RequestParam int value, Model model) {
        Arina arina = new Arina(title, author, status, ISBN, value);
        ariRepo.save(arina);
        return "redirect:/";
    }
}
