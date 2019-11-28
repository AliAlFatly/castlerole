package com.example.Castlerole.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;




@RunWith(SpringRunner.class)
@SpringBootTest
class JwtAuthenticationControllerTest {

    @InjectMocks
    private JwtAuthenticationController jwtAuthenticationController;


    @Test
    void login() {
    }

    @Test
    void register() {
        HashMap<String, String> map = new HashMap<>();
        map.put("greeting","Hello, world!");

    }
}