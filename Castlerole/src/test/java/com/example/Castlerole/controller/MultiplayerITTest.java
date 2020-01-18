//package com.example.Castlerole.controller;
//
//import com.example.Castlerole.Config.JpaConfig;
//import com.example.Castlerole.model.dto.UserDTO;
//import com.example.Castlerole.service.JwtAuthenticationService;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.jsonwebtoken.Jwt;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.io.IOException;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class MultiplayerITTest {
//
//    protected MockMvc mvc;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Autowired
//    private GameController gameController;
//
//    @Autowired
//    private JwtAuthenticationController jwtAuthenticationController;
//
//    @Autowired
//    private AdminController adminController;
//
//    @Autowired
//    private JwtAuthenticationService jwtAuthenticationService;
//
////    @Mock
////    SecurityContext securityContextMocked;
////    @Mock
////    Authentication authenticationMocked;
////    @Mock
////    Principal principal1;
//
////https://stackoverflow.com/questions/29040317/nullpointerexception-when-spring-securitycontextholder-is-called
//
//    @Before
//    public void setUpMultiplayer() {
//        MockitoAnnotations.initMocks(this);
//
//        this.mvc = MockMvcBuilders
//                .standaloneSetup(gameController,jwtAuthenticationController,adminController)
//                .build();
//
//    }
//
//    @Test
//    public void Multiplayer_Test() throws Exception {
//        //Set player credentials
//        UserDTO userOneCredentials = createDTO("user1", "password");
//        UserDTO userTwoCredentials = createDTO("user2", "password");
//
//        String RegisterURI = "/register";
//        String LoginURI = "/login";
//        String UserData = "/userData";
//        String userCoordinatesUri = "/userCoordinates";
//
//        //Register user one
//        MvcResult RegisterUserOne = mvc.perform(MockMvcRequestBuilders.post(RegisterURI)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapToJson(userOneCredentials))
//                .characterEncoding("utf-8")
//                .header("Cache-Control","no-cache, no-store")
//                .accept(MediaType.APPLICATION_JSON)
//        )
////                .andDo(print())
////                .andDo(MockMvcResultHandlers.log())
//                .andExpect(status().isOk())
//                .andReturn();
//        //Register user two
//        MvcResult RegisterUserTwo = mvc.perform(MockMvcRequestBuilders.post(RegisterURI)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapToJson(userTwoCredentials))
//                .characterEncoding("utf-8")
//                .header("Cache-Control","no-cache, no-store")
//                .accept(MediaType.APPLICATION_JSON)
//        )
////                .andDo(print())
////                .andDo(MockMvcResultHandlers.log())
//                .andExpect(status().isOk())
//                .andReturn();
//
//        //Set jwtToken (for login mock)
//        String jwtTokenUserOne = "Bearer " + RegisterUserOne.getResponse().getContentAsString().split("\"")[3];
//        String jwtTokenUserTwo = "Bearer " + RegisterUserTwo.getResponse().getContentAsString().split("\"")[3];
//
//        //Get player one information
//        MvcResult UserOneData = mvc.perform(MockMvcRequestBuilders.get(UserData)
//                //.contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("utf-8")
//                .header("Authorization", jwtTokenUserOne))
//                .andDo(print())
//                .andDo(MockMvcResultHandlers.log())
//                .andExpect(status().isOk())
//                .andReturn();
//
//        //Get player Two information
//        MvcResult UserTwoData = mvc.perform(MockMvcRequestBuilders.get(UserData)
//                //.contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("utf-8")
//                .header("Authorization", jwtTokenUserOne))
//                .andDo(print())
//                .andDo(MockMvcResultHandlers.log())
//                .andExpect(status().isOk())
//                .andReturn();
//
//        //Player one attack player two
//
//        MvcResult userTwoCoordinates = mvc.perform(MockMvcRequestBuilders.get(userCoordinatesUri)
//                //.contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("utf-8")
//                .header("Authorization", jwtTokenUserOne))
//                .andDo(print())
//                .andDo(MockMvcResultHandlers.log())
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String playerTwoX = userTwoCoordinates.getResponse().getContentAsString().split("\"")[3];
//        String playerTwoY = userTwoCoordinates.getResponse().getContentAsString().split("\"")[7];
//
//        System.out.println(playerTwoX + "and" + playerTwoY);
//        String attackPlayerTwoUri = "/attack/" + playerTwoX + "/" + playerTwoY;
//
//        MvcResult userOneAttackTwo = mvc.perform(MockMvcRequestBuilders.get(attackPlayerTwoUri)
//                //.contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("utf-8")
//                .header("Authorization", jwtTokenUserOne))
//                .andDo(print())
//                .andDo(MockMvcResultHandlers.log())
//                .andExpect(status().isOk())
//                .andReturn();
//
//        //check for infomation changes after attack
//
//
//
//    }
//
//
//    protected UserDTO createDTO(String username, String password) {
//        UserDTO dto = new UserDTO();
//
//        dto.setUsername(username);
//        dto.setPassword(password);
//
//        return dto;
//    }
//
//    protected String mapToJson(Object obj) throws JsonProcessingException {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(obj);
//    }
//
//    protected <T> T mapFromJson(String json, Class<T> clazz)
//            throws JsonParseException, JsonMappingException, IOException {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readValue(json, clazz);
//    }
//
//}
