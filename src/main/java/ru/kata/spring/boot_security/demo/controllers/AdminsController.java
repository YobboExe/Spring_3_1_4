package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping()
    public String userList(Model model, Principal principal) {
        final String currentUser = principal.getName();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("users", userServiceImpl.allUsers());
        model.addAttribute("user", userServiceImpl.findByUsername(currentUser));
        return "admin-page";
    }

    @GetMapping("/user-page/{id}")
    public String selectUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userServiceImpl.fineOne(id).get());
        return "user-page";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());

        return "new-user";
    }

    @PostMapping("/create")
    public String addUser(@ModelAttribute("user") User user) {
        userServiceImpl.saveUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/update/{id}")
    public String updateUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userServiceImpl.fineOne(id).get());
        return "update-user";
    }

    @PatchMapping("/update/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userServiceImpl.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userServiceImpl.deleteUser(id);
        return "redirect:/admin";
    }


}
