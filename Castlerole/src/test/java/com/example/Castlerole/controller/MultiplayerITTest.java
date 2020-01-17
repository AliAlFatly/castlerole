package com.example.Castlerole.controller;

import com.example.Castlerole.Config.JpaConfig;
import com.example.Castlerole.model.dto.UserDTO;
import com.example.Castlerole.service.JwtAuthenticationService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MultiplayerITTest {

    protected MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private GameController gameController;

    @Autowired
    private JwtAuthenticationController jwtAuthenticationController;

    @Autowired
    private AdminController adminController;

    @Autowired
    private JwtAuthenticationService jwtAuthenticationService;





    @Before
    public void setUpMultiplayer() {
        MockitoAnnotations.initMocks(this);

        this.mvc = MockMvcBuilders
                .standaloneSetup(gameController,jwtAuthenticationController,adminController)
                .build();

    }

    @Test
    public void Multiplayer_Test() throws Exception {

        UserDTO user1 = createDTO("user1", "password");
        UserDTO user2 = createDTO("user2", "password");
        UserDTO user3 = createDTO("user3", "password");

        String RegisterURI = "/register";
        String LoginURI = "/login";
        String UserData = "/userData";

        MvcResult RegisterUser1 = mvc.perform(MockMvcRequestBuilders.post(RegisterURI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(user1))
                .characterEncoding("utf-8")
                .header("Cache-Control","no-cache, no-store")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();

        MvcResult RegisterUser2 = mvc.perform(MockMvcRequestBuilders.post(RegisterURI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(user2))
                .characterEncoding("utf-8")
                .header("Cache-Control","no-cache, no-store")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();

        MvcResult RegisterUser3 = mvc.perform(MockMvcRequestBuilders.post(RegisterURI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(user3))
                .characterEncoding("utf-8")
                .header("Cache-Control","no-cache, no-store")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();

        String jwtTokenUser1 = RegisterUser1.getResponse().getContentAsString();
        String jwtTokenUser2 = RegisterUser2.getResponse().getContentAsString();
        String jwtTokenUser3 = RegisterUser3.getResponse().getContentAsString();
        System.out.println(
                "Token User1 = " + jwtTokenUser1 + " " + "\n" +
                "Token User2 = " + jwtTokenUser2 + " " + "\n" +
                "Token User3 = " + jwtTokenUser3
                );

        MvcResult LoginUser1 = mvc.perform(MockMvcRequestBuilders.post(LoginURI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(user1))
                .characterEncoding("utf-8")
                .header("Cache-Control","no-cache, no-store")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();

        String[] content = LoginUser1.getResponse().getContentAsString().substring(1).split(":");
        String contenttoken = content[1].replace("\"","");
        String[] betercontent = contenttoken.split("}");


        System.out.println(betercontent[0]);
        MvcResult User1Data = mvc.perform(MockMvcRequestBuilders.get(UserData)
                //.contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .header("authorization", "bearer " + betercontent[0])
                //.header("Cache-Control","no-cache, no-store")
                //.accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();

    }




    protected UserDTO createDTO(String username, String password) {
        UserDTO dto = new UserDTO();

        dto.setUsername(username);
        dto.setPassword(password);

        return dto;
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

}
