package com.example.Castlerole.controller;

import com.example.Castlerole.AbstractClass.AbstractTest;
import com.example.Castlerole.model.User;
import com.example.Castlerole.model.dto.UserDTO;
import com.example.Castlerole.model.request.JwtRequest;
import com.example.Castlerole.service.JwtAuthenticationService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GameControllerTest extends AbstractTest {


    @Autowired
    JwtAuthenticationService jwtAuthenticationService;

    @Override
    @Before
    public void setUpGameControl() {
        super.setUpGameControl();

    }

    @Test
    public void getUserData() throws Exception {

        String uri = "/userData";

        UserDTO newUser = super.createDTO("Admin1234", "password");

        jwtAuthenticationService.register(newUser);

        String token = jwtAuthenticationService.login(new JwtRequest("Admin1234", "password"));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(token)
                .characterEncoding("utf-8")
                .header("Cache-Control","no-cache, no-store")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();
    }

}
