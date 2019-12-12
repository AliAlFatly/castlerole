package com.example.Castlerole.controller;

import com.example.Castlerole.service.AdminService;
import com.example.Castlerole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@CrossOrigin()
public class AdminController {

    @Autowired
    public UserService userService;

    @Autowired
    public AdminService adminService;

    @GetMapping("/addNodes/{amount}")
    public String addNodes(@PathVariable("amount") int amount) throws Exception {
        return adminService.generateNodes(amount);
    }

}
