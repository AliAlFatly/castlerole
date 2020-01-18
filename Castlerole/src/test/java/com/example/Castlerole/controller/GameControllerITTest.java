package com.example.Castlerole.controller;

import com.example.Castlerole.Config.ControllerTestConfig;
import com.example.Castlerole.model.User;
import com.example.Castlerole.model.dto.UserDTO;
import com.example.Castlerole.model.helpertypes.Vector;
import com.example.Castlerole.model.request.JwtRequest;
import com.example.Castlerole.service.JwtAuthenticationService;
import com.example.Castlerole.service.JwtUserService;
import org.junit.Before;
import org.junit.Test;
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
    @Before
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


        var newRegistredUser = mapFromJson(mvcResult.getResponse().getContentAsString(),User.class);

        int x = newRegistredUser.getxCoordinate();
        int y = newRegistredUser.getyCoordinate();

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
