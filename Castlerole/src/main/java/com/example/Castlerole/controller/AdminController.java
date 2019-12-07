package com.example.Castlerole.controller;

import com.example.Castlerole.service.AdminService;
import com.example.Castlerole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@CrossOrigin()
public class AdminController {

    @Autowired
    public UserService userService;

    @Autowired
    public AdminService adminService;

    @PostMapping("/addNodes")
    public String addNodes(@RequestBody int amount) throws Exception {
        return adminService.generateNodes(amount);
    }

}
