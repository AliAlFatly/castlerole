package com.example.castlerole.controller;

import com.example.castlerole.model.Node;
import com.example.castlerole.model.dto.UserDTO;
import com.example.castlerole.repository.CityRepository;
import com.example.castlerole.repository.NodeRepository;
import com.example.castlerole.repository.UserRepository;
import com.example.castlerole.service.CityService;
import com.example.castlerole.service.JwtAuthenticationService;
import com.example.castlerole.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@SpringBootTest
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
    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private CityRepository cityRepository;


//https://stackoverflow.com/questions/29040317/nullpointerexception-when-spring-securitycontextholder-is-called

    @BeforeEach
    public void setUpMultiplayer() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.mvc = MockMvcBuilders
                .standaloneSetup(gameController,jwtAuthenticationController,adminController)
                .build();

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
}

    @Test
    public void Multiplayer_Test() throws Exception {
        //Set player credentials
        UserDTO userOneCredentials = createDTO("user1", "password");
        UserDTO userTwoCredentials = createDTO("user2", "password");

        String RegisterURI = "/register";
        String LoginURI = "/login";
        String UserData = "/userData";
        String userCoordinatesUri = "/userCoordinates";
        String adminNodes ="/addNodes/30/secretcodehere";
        String userRecruit = "/recruit/200";
        String UpdateCastle = "/update/Castle";
        String UpdateForgery = "/update/Forgery";
        String UpdateBarrack = "/update/Barrack";
        String UpdateWoodwork = "/update/Woodwork";
        String UpdateMine = "/update/Mine";
        String UpdateOven = "/update/Oven";
        String CityData = "/cityData";

        //Register user one
        MvcResult RegisterUserOne = mvc.perform(MockMvcRequestBuilders.post(RegisterURI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(userOneCredentials))
                .characterEncoding("utf-8")
                .header("Cache-Control","no-cache, no-store")
                .accept(MediaType.APPLICATION_JSON)
        )
//                .andDo(print())
//                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();
        //Register Nodes
        MvcResult adminAddNodes = mvc.perform(MockMvcRequestBuilders.get(adminNodes)
                //.contentType(MediaType.APPLICATION_JSON)
                //.content(mapToJson(userOneCredentials))
                .characterEncoding("utf-8")
                .header("Cache-Control","no-cache, no-store")
                .accept(MediaType.APPLICATION_JSON)
        )
//                .andDo(print())
//                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();
        //Register user two
        MvcResult RegisterUserTwo = mvc.perform(MockMvcRequestBuilders.post(RegisterURI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(userTwoCredentials))
                .characterEncoding("utf-8")
                .header("Cache-Control","no-cache, no-store")
                .accept(MediaType.APPLICATION_JSON)
        )
//                .andDo(print())
//                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();

        //Set jwtToken (for login mock)
        String jwtTokenUserOne = RegisterUserOne.getResponse().getContentAsString().split("\"")[3];
        String jwtTokenUserTwo = "Bearer " + RegisterUserTwo.getResponse().getContentAsString().split("\"")[3];


        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("user1");
        //Get player one information
        MvcResult UserOneData = mvc.perform(MockMvcRequestBuilders.get(UserData)
                //.contentType(MediaType.APPLICATION_JSON)

                .characterEncoding("utf-8")
                .header("authoriztion", jwtTokenUserOne))
                //.andDo(print())
                //.andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();

        // switch to user2
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("user2");
        //Get player Two information
        MvcResult UserTwoData = mvc.perform(MockMvcRequestBuilders.get(UserData)
                //.contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .header("Authorization", jwtTokenUserOne))
                //.andDo(print())
                //.andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();

        // switch to user1
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("user1");
        Iterator<Node> nodes = nodeRepository.findAll().iterator();
        List<Node> nodeIron = new ArrayList<>();
        List<Node> nodeFood = new ArrayList<>();
        List<Node> nodeWood = new ArrayList<>();
        List<Node> nodeStone = new ArrayList<>();
        while(nodes.hasNext()){
            Node mi = nodes.next();
            if(mi.getYieldType().equals("iron") && mi.getYieldMax() >= 60 && mi.getTroops() <= 150){
                nodeIron.add(mi);
            }
            if(mi.getYieldType().equals("wood") && mi.getYieldMax() >= 60 && mi.getTroops() <= 150){
                nodeWood.add(mi);
            }
            if(mi.getYieldType().equals("food") && mi.getYieldMax() >= 60 && mi.getTroops() <= 150){
                nodeFood.add(mi);
            }
            if(mi.getYieldType().equals("stone") && mi.getYieldMax() >= 60 && mi.getTroops() <= 150){
                nodeStone.add(mi);
            }
        }
        String attackNodeIron = "/attack/" + nodeIron.get(0).getxCoordinate() + "/" + nodeIron.get(0).getyCoordinate();
        String attackNodeStone = "/attack/" + nodeStone.get(0).getxCoordinate() + "/" + nodeStone.get(0).getyCoordinate();
        String attackNodeWood = "/attack/" + nodeWood.get(0).getxCoordinate() + "/" + nodeWood.get(0).getyCoordinate();
        String attackNodeFood = "/attack/" + nodeFood.get(0).getxCoordinate() + "/" + nodeFood.get(0).getyCoordinate();
        for (var i = 0; i < 250; i++) {
            // Attack Iron Node
            mvc.perform(MockMvcRequestBuilders.get(attackNodeIron)
                    .header("Authorization", jwtTokenUserOne))
                    .andDo(MockMvcResultHandlers.log())
                    .andExpect(status().isOk())
                    .andReturn();
            // Attack Stone Node
            mvc.perform(MockMvcRequestBuilders.get(attackNodeStone)
                    .header("Authorization", jwtTokenUserOne))
                    .andDo(MockMvcResultHandlers.log())
                    .andExpect(status().isOk())
                    .andReturn();
            // Attack Wood Node
            mvc.perform(MockMvcRequestBuilders.get(attackNodeWood)
                    .header("Authorization", jwtTokenUserOne))
                    .andDo(MockMvcResultHandlers.log())
                    .andExpect(status().isOk())
                    .andReturn();
            // Attack Food Node
            mvc.perform(MockMvcRequestBuilders.get(attackNodeFood)
                    .header("Authorization", jwtTokenUserOne))
                    .andDo(MockMvcResultHandlers.log())
                    .andExpect(status().isOk())
                    .andReturn();
        }
        // get userdate from user 1 after attack nodes
        MvcResult UseroneAttacknodeData = mvc.perform(MockMvcRequestBuilders.get(UserData)
                //.contentType(MediaType.APPLICATION_JSON)

                .characterEncoding("utf-8")
                .header("authoriztion", jwtTokenUserOne))
                .andDo(print())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();
        // user 1 upgrade buildings
        for(int i = 0; i < 10; i++) {
            mvc.perform(MockMvcRequestBuilders.get(UpdateCastle)
                    .header("authoriztion", jwtTokenUserOne))
                    .andDo(MockMvcResultHandlers.log())
                    .andExpect(status().isOk())
                    .andReturn();
            mvc.perform(MockMvcRequestBuilders.get(UpdateBarrack)
                    .header("authoriztion", jwtTokenUserOne))
                    .andDo(MockMvcResultHandlers.log())
                    .andExpect(status().isOk())
                    .andReturn();
            mvc.perform(MockMvcRequestBuilders.get(UpdateForgery)
                    .header("authoriztion", jwtTokenUserOne))
                    .andDo(MockMvcResultHandlers.log())
                    .andExpect(status().isOk())
                    .andReturn();
            mvc.perform(MockMvcRequestBuilders.get(UpdateMine)
                    .header("authoriztion", jwtTokenUserOne))
                    .andDo(MockMvcResultHandlers.log())
                    .andExpect(status().isOk())
                    .andReturn();
            mvc.perform(MockMvcRequestBuilders.get(UpdateOven)
                    .header("authoriztion", jwtTokenUserOne))
                    .andDo(MockMvcResultHandlers.log())
                    .andExpect(status().isOk())
                    .andReturn();
            mvc.perform(MockMvcRequestBuilders.get(UpdateWoodwork)
                    .header("authoriztion", jwtTokenUserOne))
                    .andDo(MockMvcResultHandlers.log())
                    .andExpect(status().isOk())
                    .andReturn();
        }
        // get CityData from user 1 after upgrading buildings
        MvcResult UserOneUpgradeData = mvc.perform(MockMvcRequestBuilders.get(CityData)
                //.contentType(MediaType.APPLICATION_JSON)

                .characterEncoding("utf-8")
                .header("authoriztion", jwtTokenUserOne))
                .andDo(print())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();
        // get userdate from user 1 after upgrading buildings
        MvcResult UserOneResourceData = mvc.perform(MockMvcRequestBuilders.get(UserData)
                //.contentType(MediaType.APPLICATION_JSON)

                .characterEncoding("utf-8")
                .header("authoriztion", jwtTokenUserOne))
                .andDo(print())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(UseroneAttacknodeData.getResponse());
        MvcResult UseroneRecruitUnits = mvc.perform(MockMvcRequestBuilders.get(userRecruit)
                //.contentType(MediaType.APPLICATION_JSON)

                .characterEncoding("utf-8")
                .header("authoriztion", jwtTokenUserOne))
                .andDo(print())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();
        // Player one attack player two
        // switch to player2
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("user2");
        MvcResult userTwoCoordinates = mvc.perform(MockMvcRequestBuilders.get(userCoordinatesUri)
                //.contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .header("Authorization", jwtTokenUserOne))
                .andDo(print())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();

        String playerTwoX = userTwoCoordinates.getResponse().getContentAsString().split("\"")[2].split(":")[1].split(",")[0];
        String playerTwoY = userTwoCoordinates.getResponse().getContentAsString().split("\"")[4].split(":")[1].split("}")[0];


        System.out.println(playerTwoX + "and" + playerTwoY);
        String attackPlayerTwoUri = "/attack/" + playerTwoX + "/" + playerTwoY;

        // switch to player 1
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("user1");
        MvcResult userOneAttackTwo = mvc.perform(MockMvcRequestBuilders.get(attackPlayerTwoUri)
                //.contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .header("Authorization", jwtTokenUserOne))
                .andDo(print())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();

        // switch to player 2
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("user2");
        //check for infomation changes after attack
        MvcResult userTwoLostResources = mvc.perform(MockMvcRequestBuilders.get(UserData)
                //.contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .header("Authorization", jwtTokenUserTwo))
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

    private static RequestPostProcessor sessionUser(final UserDetails userDetails) {
        return new RequestPostProcessor() {
            @Override
            public MockHttpServletRequest postProcessRequest(final MockHttpServletRequest request) {
                final SecurityContext securityContext = new SecurityContextImpl();
                securityContext.setAuthentication(
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
                );
                request.getSession().setAttribute(
                        HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext
                );
                return request;
            }
        };
    }

}
