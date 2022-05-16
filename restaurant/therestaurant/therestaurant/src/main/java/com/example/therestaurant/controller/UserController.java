package com.example.therestaurant.controller;

import com.example.therestaurant.entity.Menu;
import com.example.therestaurant.entity.User;
import com.example.therestaurant.repository.UserRepository;
import com.example.therestaurant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository uRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public  String getLogin() {
        return "login";
    }

    @GetMapping("/register")
    public ModelAndView getRegister() {
        ModelAndView mav = new ModelAndView("register");
        User newUser = new User();
        mav.addObject("pageTitle", "The Restaurant - Register");
        mav.addObject("title", "Register");
        mav.addObject("user", newUser);
        return mav;
    }

    @PostMapping("/register")
    public String saveCustomer(@ModelAttribute User user, BindingResult result) {
        return userService.register(user, result);
    }

    public ModelAndView getUpdateForm(@RequestParam Integer userId) {
        ModelAndView mav = new ModelAndView("register");
        User user = uRepo.findById(userId).get();
        mav.addObject("pageTitle", "Update Info");
        mav.addObject("title", "Update Form");
        mav.addObject("user", user);
        return mav;
    }

}
