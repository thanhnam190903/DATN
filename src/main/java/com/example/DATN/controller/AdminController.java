package com.example.DATN.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quantri")
public class AdminController {

    @GetMapping()
    public String showAdminPage(Model model){
        return "admin/index";
    }
}
