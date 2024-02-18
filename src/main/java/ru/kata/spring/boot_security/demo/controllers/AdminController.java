package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("users", userService.show());
        return "/admin/index";
    }


    @GetMapping("/user")
    public String id(@RequestParam int id, Model model) {
        model.addAttribute("user", userService.find(id));
        return "/admin/id";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "/admin/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute @Valid User user, BindingResult bindingResult
            , @RequestParam(value = "roles", required = false) String[] roles) {
        if (bindingResult.hasErrors()){
            return "/admin/new";
        }
        userService.save(user,roles);
        return "redirect:/admin";
    }

    @GetMapping("/user/edit")
    public String editUser(@RequestParam int id, Model model) {
        model.addAttribute("user", userService.find(id));
        return "/admin/edit";
    }

    @PostMapping("/user/edit")
    public String update(@RequestParam int id, @ModelAttribute @Valid User user, BindingResult bindingResult,
                         @RequestParam(value = "roles", required = false) String[] roles) {
        if (bindingResult.hasErrors()) return "/admin/edit";
        userService.update(id, user,roles);
        return "redirect:/admin";
    }



    @GetMapping("/user/delete")
    public String delUser(@RequestParam int id, Model model) {
        model.addAttribute("user",userService.find(id));
        return "/admin/delete";
    }

    @PostMapping("/user/delete")
    public String delete(@RequestParam int id){
        userService.delete(id);
        return "redirect:/admin";
    }
}
