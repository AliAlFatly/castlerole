package com.example.Castlerole.controller;

import com.example.Castlerole.Config.ControllerTestConfig;
import com.example.Castlerole.model.dto.UserDTO;
import com.example.Castlerole.service.JwtAuthenticationService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.util.NestedServletException;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class JwtAuthenticationControllerITTest extends ControllerTestConfig {

    @Autowired
    JwtAuthenticationService jwtAuthenticationService;

    @Override
    @Before
    public void setUpJwtAuth() {
        super.setUpJwtAuth();

    }


    @Test(expected = NestedServletException.class)
    public void login() throws Exception {

        String uri = "/login";
        UserDTO userRight = super.createDTO("admin3","password");
        UserDTO userWrong = super.createDTO("admin4","password");


        jwtAuthenticationService.register(userRight);

        String inputJsonWrong = super.mapToJson(userWrong);
        String inputJsonRight = super.mapToJson(userRight);

        MvcResult mvcResultRight = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJsonRight)
                .characterEncoding("utf-8")
                .header("Cache-Control","no-cache, no-store")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();

        MvcResult mvcResultWrong = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJsonWrong)
                .characterEncoding("utf-8")
                .header("Cache-Control","no-cache, no-store")
                .accept(MediaType.APPLICATION_JSON)
        )
//                .andDo(print())
//                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().is4xxClientError())
                .andReturn();

    }
    // eindigt it-test(integration) anders normaal

    @Test
    public void RegisterUserTest() throws Exception {
        String uri = "/register";
        final String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEyMzQxMzMiLCJleHAiOjE1NzUzMzkxNTEsImlhdCI6MTU3NTMyMTE1MX0.WFjAQ_MekvY6u-loMTjKPe2IGTqeUp3MMKNpI5ZdcEgIsN1BgfNrv0xcxG8Q1uV1fp40pgpGLrqgJLqfND1HDg";
        UserDTO user = super.createDTO("admin5","password");
        String inputJson = super.mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .characterEncoding("utf-8")
                .header("Cache-Control","no-cache, no-store")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();
        String[] content = mvcResult.getResponse().getContentAsString().substring(1).split(":");
        String contenttoken = content[1].replace("\"","");
        assertNotEquals(contenttoken, token);


    }

    public void RegisterUserTest_already_exists() throws Exception {
        String uri = "/register";
        UserDTO userEXISTS = super.createDTO("admin5","password");
        jwtAuthenticationService.register(userEXISTS);
        String inputJsonExists = super.mapToJson(userEXISTS);
        MvcResult mvcResult_User_Exists = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJsonExists)
                .characterEncoding("utf-8")
                .header("Cache-Control","no-cache, no-store")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andDo(MockMvcResultHandlers.log())

                //.andExpect(status().isOk())
                .andReturn();

    }
}