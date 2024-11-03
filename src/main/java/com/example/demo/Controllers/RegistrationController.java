package com.example.demo.Controllers;

import com.example.demo.models.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserRepo userRepo; // Змініть на правильний референс

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("title", "Реєстрація");
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        logger.info("Attempting to register user with name: {}, email: {}", name, email);

        User existingUser = userRepo.findByEmail(email);
        if (existingUser != null) {
            logger.warn("User with email: {} already exists", email);
            return "redirect:/registration?error"; // Додати обробку помилки
        }

        // Створити нового користувача з зашифрованим паролем
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        userRepo.save(user);
        logger.info("User registered successfully with email: {}", email);

        return "redirect:/login"; // Перенаправлення на сторінку входу
    }

}
