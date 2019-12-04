package com.example.Castlerole.controller;

import com.example.Castlerole.AbstractClass.AbstractTest;
import com.example.Castlerole.model.User;
import com.example.Castlerole.model.dto.UserDTO;
import com.example.Castlerole.model.helpertypes.Vector;
import com.example.Castlerole.model.request.JwtRequest;
import com.example.Castlerole.service.JwtAuthenticationService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.persistence.Column;
import java.sql.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GameControllerTest extends AbstractTest {



    protected User newRegistredUser;


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

    @Test
    public void getGrid() throws Exception {

        String uri = "/userData";
        String uriData = "/grid";

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


        var newRegistredUser = mapFromJson(mvcResult.getResponse().getContentAsString(),User.class);

        int x = newRegistredUser.getxCoordinate();
        int y = newRegistredUser.getyCoordinate();

        System.out.println(x + " " + y);

        Vector newVector = new Vector();
        newVector.setX(x);
        newVector.setY(y);

        String VectorData = super.mapToJson(newVector);

        MvcResult mvcResultGrid = mvc.perform(MockMvcRequestBuilders.get(uriData)
                .contentType(MediaType.APPLICATION_JSON)
                .content(VectorData)
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
