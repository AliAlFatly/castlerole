package com.example.castlerole.controller;

import com.example.castlerole.service.AdminService;
import com.example.castlerole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@CrossOrigin()
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    private static final String secretKey = "secretcodehere";

    @GetMapping("/addNodes/{amount}/{key}")
    public String addNodes(@PathVariable("amount") int amount, @PathVariable("key") String key) throws Exception {
        if (key.equals(secretKey)){
            return adminService.generateNodes(amount);
        }
        else{
            return "Bad Request";
        }
    }

}
