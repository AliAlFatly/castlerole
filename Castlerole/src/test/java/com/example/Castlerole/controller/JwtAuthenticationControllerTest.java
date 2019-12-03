package com.example.Castlerole.controller;

import com.example.Castlerole.model.request.JwtRequest;
import com.example.Castlerole.service.JwtAuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@RunWith(SpringRunner.class)
@SpringBootTest
class JwtAuthenticationControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private JwtAuthenticationController jwtAuthenticationController;

    @InjectMocks
    private JwtAuthenticationService jwtAuthenticationService;

    //beforeeach and before not working
//    @BeforeEach
//    public void setUp() throws Exception {
//        //MockMvcBuilders create a mock of the controller with request/response objects (servlets too).
//        mockMvc = MockMvcBuilders.standaloneSetup(jwtAuthenticationController).build();
//        //voeg ifstatement waarbij de user (Test@Test.com, Test@1) toegevoegd wordt aan de database als die niet bestaat.
//    }

    @Test
    void login() throws Exception {
        //initiated at function test lvl as a temprary solution.
        mockMvc = MockMvcBuilders.standaloneSetup(jwtAuthenticationController).build();
        JwtRequest user = new JwtRequest("test@test.com", "Test@1");
        //!NOTE: MockMvcRequestBuilders.post turned into on demand static import
        //see static import
        //import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        //above
        //use mock and perform request, this case post request to /login
        mockMvc.perform(post("/login")
                .accept(MediaType.APPLICATION_JSON)
                .content(String.valueOf(user))
                .contentType(MediaType.APPLICATION_JSON)
        )
                //MockMvcResultMatchers.status / MockMvcResultMatchers.content turned into on demand static.
                //expect response status OK
                .andExpect(status().isOk())
                //check if return data is equal to correct value.
                //expect token for user
                .andExpect(content().json(jwtAuthenticationService.login(user)));
    }

    @Test
    void register() throws Exception {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("key","Value");
        mockMvc = MockMvcBuilders.standaloneSetup(jwtAuthenticationController).build();
        JwtRequest user = new JwtRequest("test9999@test.com", "Test@1");

        mockMvc.perform(post("/register")
                .accept(MediaType.APPLICATION_JSON)
                .content(String.valueOf(user))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(jwtAuthenticationService.login(user)));
    }
}