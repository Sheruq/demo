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

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AriRepo ariRepo;


    @GetMapping("/users")
    public String listUsers(Model model) {
        Iterable<User> users = userRepo.findAll();
        Iterable<Arina> books = ariRepo.findAll(); // Отримати всі доступні книги
        model.addAttribute("users", users);
        model.addAttribute("books", books); // Передати книги до моделі
        model.addAttribute("title", "Список користувачів");
        return "users";
    }


    // Додавання нового користувача
    @GetMapping("/users/add")
    public String addUserForm(Model model) {
        model.addAttribute("title", "Додати користувача");
        return "add_user";
    }

    @PostMapping("/users/add")
    public String addUser(@RequestParam String name, @RequestParam String email) {
        User user = new User(name, email);
        userRepo.save(user);
        return "redirect:/users";
    }

    @PostMapping("/users/{userId}/borrow")
    public String borrowBook(@PathVariable("userId") long userId, @RequestParam("bookId") long bookId) {
        Optional<User> user = userRepo.findById(userId);
        Optional<Arina> book = ariRepo.findById(bookId);

        if (user.isPresent() && book.isPresent()) {
            User u = user.get();
            Arina b = book.get();

            // Перевірка, чи є доступні книги для видачі
            if (b.getValue() > 0) {
                u.addBook(b); // Додаємо книгу користувачу
                b.setValue(b.getValue() - 1); // Зменшуємо кількість доступних книг
                userRepo.save(u);
                ariRepo.save(b);
            }
        }
        return "redirect:/users";
    }

    // Перегляд виданих книг для конкретного користувача
    @GetMapping("/users/{id}")
    public String userDetail(@PathVariable(value = "id") long id, Model model) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            model.addAttribute("title", "Деталі користувача");
            model.addAttribute("borrowedBooks", user.get().getBorrowedBooks()); // Додати видані книги до моделі
            return "user_detail"; // Переконайтеся, що у вас є шаблон user_detail.html
        }
        return "redirect:/users";
    }

    @PostMapping("/users/{id}/remove")
    public String removeUser(@PathVariable("id") long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        }
        return "redirect:/users"; // Повертаємося до списку користувачів після видалення
    }


}


