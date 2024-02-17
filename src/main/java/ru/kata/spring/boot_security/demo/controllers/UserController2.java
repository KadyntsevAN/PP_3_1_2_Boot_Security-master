package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
public class UserController2 {
    private final UserService userService;
    private final RoleDao roleDao;

    @Autowired
    public UserController2(UserService userService, RoleDao roleDao) {
        this.userService = userService;
        this.roleDao = roleDao;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userService.show());
        return "index";
    }

//    @GetMapping("/user")
//    public String id(@RequestParam int id, Model model) {
//        model.addAttribute("user", userService.find(id));
//        return "/id";
//    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("roles",roleDao.show());
        model.addAttribute("user", new User());
        return "/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute @Valid User user, BindingResult bindingResult
            , @RequestParam(value = "roles", required = false) String[] roles) {
        if (bindingResult.hasErrors()){
            return "/new";
        }
        userService.save(user,roles);
        return "redirect:/";
    }

    @GetMapping("/user/edit")
    public String editUser(@RequestParam int id, Model model) {
        model.addAttribute("user", userService.find(id));
        return "/edit";
    }

    @PostMapping("/user/edit")
    public String update(@RequestParam int id, @ModelAttribute @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "/edit";
        userService.update(id, user);
        return "redirect:/";
    }

    @GetMapping("/user/delete")
    public String delUser(@RequestParam int id, Model model) {
        model.addAttribute("user",userService.find(id));
        return "/delete";
    }

    @PostMapping("/user/delete")
    public String delete(@RequestParam int id){
        userService.delete(id);
        return "redirect:/";
    }
}