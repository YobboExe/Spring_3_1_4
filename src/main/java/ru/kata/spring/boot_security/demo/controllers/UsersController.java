package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/table")
    public String tableOfUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "all-users";
    }

    @GetMapping("/{id}")
    public String selectUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("users", userService.fineOne(id));
        return "user-page";
    }

    @GetMapping("/new")
    public String newUser (Model model) {
        model.addAttribute("user", new User());
        return "new-user";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users/table";
    }

    @GetMapping("/update/{id}")
    public String updateUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.fineOne(id));
        return "update-user";
    }

    @PatchMapping("/update/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/users/table";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users/table";
    }

}
