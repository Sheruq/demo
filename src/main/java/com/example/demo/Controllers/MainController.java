package com.example.demo.Controllers;

import com.example.demo.models.Arina;
import com.example.demo.models.User;
import com.example.demo.repo.AriRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;


@Controller
public class MainController {

    @Autowired
    private AriRepo AriRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Arina> arinass = AriRepo.findAll();
        Iterable<User> users = userRepo.findAll(); // Отримати список користувачів
        arinass.forEach(arina -> System.out.println(arina.getTitle()));  // Логування
        model.addAttribute("Arinass", arinass);
        model.addAttribute("users", users); // Додати список користувачів до моделі
        model.addAttribute("title", "Головна");
        return "home";
    }


    @GetMapping("/insert")
    public String insertadd(Model model) {
        model.addAttribute("title", "Додати книгу");
        return "insert";
    }


    @PostMapping("/insert")
    public String insertPostAdd(@RequestParam String title, @RequestParam String author, @RequestParam String status, @RequestParam int ISBN, @RequestParam int value, Model model) {
        Arina arina = new Arina(title, author, status, ISBN, value);
        AriRepo.save(arina);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String BookEdit(@PathVariable(value = "id") long id, Model model) {
        if (!AriRepo.existsById(id)) {
            return "redirect:/";
        }
        Optional<Arina> post = AriRepo.findById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
        }
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String updateBook(@PathVariable(value = "id") long id,
                             @RequestParam String title,
                             @RequestParam String author,
                             @RequestParam String status,
                             @RequestParam int ISBN,
                             @RequestParam int value) {
        Optional<Arina> post = AriRepo.findById(id);
        if (post.isPresent()) {
            Arina arina = post.get();
            arina.setTitle(title);
            arina.setAuthor(author);
            arina.setStatus(status);
            arina.setISBN(ISBN);
            arina.setValue(value);
            AriRepo.save(arina);
        }
        return "redirect:/";
    }

    @PostMapping("/{id}/remove")
    public String RemoveBook(@PathVariable(value = "id") long id) {
        if (AriRepo.existsById(id)) {
            AriRepo.deleteById(id);
        }
        return "redirect:/";
    }

    @PostMapping("/users/lend/{bookId}")
    public String lendBook(@PathVariable("bookId") long bookId, @RequestParam("userId") long userId) {
        Optional<User> user = userRepo.findById(userId);
        Optional<Arina> book = AriRepo.findById(bookId);

        if (user.isPresent() && book.isPresent()) {
            User u = user.get();
            Arina b = book.get();

            // Перевірка, чи є доступні книги для видачі
            if (b.getValue() > 0) {
                u.addBook(b); // Додаємо книгу користувачу
                b.setValue(b.getValue() - 1); // Зменшуємо кількість доступних книг
                userRepo.save(u);
                AriRepo.save(b);
            }
        }
        return "redirect:/users"; // Перенаправлення після успішної видачі
    }


}
