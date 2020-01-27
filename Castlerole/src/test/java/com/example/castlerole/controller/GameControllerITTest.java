package com.example.castlerole.controller;

import com.example.castlerole.Config.ControllerTestConfig;
import com.example.castlerole.model.User;
import com.example.castlerole.model.dto.UserDTO;
import com.example.castlerole.model.helpertypes.Vector;
import com.example.castlerole.model.request.JwtRequest;
import com.example.castlerole.model.response.UserDataResponse;
import com.example.castlerole.service.JwtAuthenticationService;
import com.example.castlerole.service.JwtUserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GameControllerITTest extends ControllerTestConfig {

    @Autowired
    JwtAuthenticationService jwtAuthenticationService;

    @Autowired
    JwtUserService jwtUserService;

    @Override
    @BeforeEach
    public void setUpGameControl() {
        super.setUpGameControl();

    }

    // geef functies een waarde en return
    // end to tend testing


    @Test
    public void getUserData() throws Exception {

        String uri = "/userData";

        UserDTO newUser = super.createDTO("Admin1", "password");
        //SecurityContextHolder.getContext().getAuthentication().getName();
        jwtAuthenticationService.register(newUser);

        String token = jwtAuthenticationService.login(new JwtRequest("Admin1", "password"));
        UserDetails userDetails = jwtUserService.loadUserByUsername(newUser.getUsername());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
//        usernamePasswordAuthenticationToken
//                .setDetails(new WebAuthenticationDetailsSource().buildDetails());


        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)

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
        String uriData = "/grid/";

        UserDTO newUser = super.createDTO("Admin2", "password");

        jwtAuthenticationService.register(newUser);

        String token = jwtAuthenticationService.login(new JwtRequest("Admin2", "password"));

        UserDetails userDetails = jwtUserService.loadUserByUsername(newUser.getUsername());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
//        usernamePasswordAuthenticationToken
//                .setDetails(new WebAuthenticationDetailsSource().buildDetails());


        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);



        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)

                .characterEncoding("utf-8")
                .header("Cache-Control","no-cache, no-store")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();


        var newRegistredUser = mapFromJson(mvcResult.getResponse().getContentAsString(), UserDataResponse.class);

        int x = newRegistredUser.getX();
        int y = newRegistredUser.getY();

        System.out.println(x + " " + y);

        Vector newVector = new Vector();
        newVector.setX(x);
        newVector.setY(y);

        String VectorData = super.mapToJson(newVector);

        MvcResult mvcResultGrid = mvc.perform(MockMvcRequestBuilders.get(uriData + x +"/"+ y)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .header("Cache-Control","no-cache, no-store")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();
    }

    public void AttackPlayerTest() {


    }


}
