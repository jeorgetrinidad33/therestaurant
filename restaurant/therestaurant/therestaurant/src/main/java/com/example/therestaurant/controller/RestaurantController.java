package com.example.therestaurant.controller;

import com.example.therestaurant.entity.Menu;
import com.example.therestaurant.repository.MenuRepository;
import com.example.therestaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RestaurantController {

    @Autowired
    private UserRepository uRepo;

    @Autowired
    private MenuRepository mRepo;

    @GetMapping({"/", "/index"})
    public ModelAndView getHome() {
        ModelAndView mav = new ModelAndView("index");
        List<Menu> list = mRepo.findAll();
        mav.addObject("menus", list);
        mav.addObject("pageTitle", "The Restaurant");
        mav.addObject("title", "Menu");
        return mav;
    }

    @GetMapping("/confirm")
    public ModelAndView getConfirm() {
        ModelAndView mav = new ModelAndView("confirmation");
        mav.addObject("pageTitle", "The Restaurant - Checkout");
        mav.addObject("title", "Confirmation");
        return mav;
    }

    @GetMapping("/payment")
    public  String getPayment() {
        return  "payment";
    }

    @GetMapping("/cart")
    public String getCart() {
        return  "cart";
    }
}
