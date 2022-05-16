package com.example.therestaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String getAdmin() {
        return "admin/index";
    }

    @GetMapping("/admin/category")
    public String getCategory() {
        return  "admin/category";
    }

    @GetMapping({"/admin/menu"})
    public String getMenuItemList() {
        return "admin/menu";
    }

    @GetMapping("/admin/order")
    public  String getOrders() {
        return "admin/order";
    }

    @GetMapping("/admin/delivery")
    public String getDelivery() {
        return  "admin/delivery";
    }

    @GetMapping("/admin/customer")
    public String getCustomer() {
        return  "admin/customer";
    }

}
