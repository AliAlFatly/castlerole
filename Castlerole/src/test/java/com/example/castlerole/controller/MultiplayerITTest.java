package com.example.castlerole.controller;

import com.example.castlerole.model.Node;
import com.example.castlerole.model.User;
import com.example.castlerole.model.dto.UserDTO;
import com.example.castlerole.model.response.CityDataResponse;
import com.example.castlerole.model.response.UserDataResponse;
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

import org.junit.Assert;
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
        String adminNodes ="/addNodes/20/secretcodehere";
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
                .andExpect(status().isOk())
                .andReturn();
        //Register Nodes
        MvcResult adminAddNodes = mvc.perform(MockMvcRequestBuilders.get(adminNodes)
                .characterEncoding("utf-8")
                .header("Cache-Control","no-cache, no-store")
                .accept(MediaType.APPLICATION_JSON)
        )
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
                .andExpect(status().isOk())
                .andReturn();

        //Set jwtToken (for login mock)
        String jwtTokenUserOne = RegisterUserOne.getResponse().getContentAsString().split("\"")[3];
        String jwtTokenUserTwo = "Bearer " + RegisterUserTwo.getResponse().getContentAsString().split("\"")[3];
        // switch to user1
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("user1");
        // Get player one information
        MvcResult UserOneData = mvc.perform(MockMvcRequestBuilders.get(UserData)
                .header("authoriztion", jwtTokenUserOne))
                .andExpect(status().isOk())
                .andReturn();

        // switch to user2
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("user2");
        // Get player Two information
        MvcResult UserTwoData = mvc.perform(MockMvcRequestBuilders.get(UserData)
                .header("Authorization", jwtTokenUserOne))
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
            if(mi.getYieldType().equals("iron") && mi.getYieldMax() >= 60 && mi.getTroops() <= 200){
                nodeIron.add(mi);
            }
            if(mi.getYieldType().equals("wood") && mi.getYieldMax() >= 60 && mi.getTroops() <= 200){
                nodeWood.add(mi);
            }
            if(mi.getYieldType().equals("food") && mi.getYieldMax() >= 60 && mi.getTroops() <= 200){
                nodeFood.add(mi);
            }
            if(mi.getYieldType().equals("stone") && mi.getYieldMax() >= 60 && mi.getTroops() <= 200){
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
                .characterEncoding("utf-8")
                .header("authoriztion", jwtTokenUserOne))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();

        // Screen for easy showing of the difference
        UserDataResponse newUser = mapFromJson(UserOneData.getResponse().getContentAsString(), UserDataResponse.class);
        UserDataResponse newUserAfternodeAttack = mapFromJson(UseroneAttacknodeData.getResponse().getContentAsString(), UserDataResponse.class);
        System.out.println(
                "\n" + "User1 Data before Node Attack: " + "\n" +
                "Username: " + newUser.getUsername() + "\n" +
                "Food: " + newUser.getFood() + "\n" +
                "Iron: " + newUser.getIron() + "\n" +
                "Stone: " + newUser.getStone() + "\n" +
                "Wood: " + newUser.getWood() + "\n" +
                "Troops: " + newUser.getTroops() + "\n" +
                "X: " + newUser.getX() + "\n" +
                "Y: " + newUser.getY() + "\n" + "\n" +

                "Attacking 250*4 Nodes: " + " " + "\n" +
                "Wood won: " + (newUserAfternodeAttack.getWood() - 300) + " " + "\n" +
                "Food won: " + (newUserAfternodeAttack.getFood() - 300) + " " + "\n" +
                "Iron won: " + (newUserAfternodeAttack.getIron() - 300) + " " + "\n" +
                "Stone won: " + (newUserAfternodeAttack.getStone() - 300) + " " + "\n" + "\n" +

                "User1 Data after Node Attack: " + "\n" +
                "Username: " + newUserAfternodeAttack.getUsername() + "\n" +
                "Food: " + newUserAfternodeAttack.getFood() + "\n" +
                "Iron: " + newUserAfternodeAttack.getIron() + "\n" +
                "Stone: " + newUserAfternodeAttack.getStone() + "\n" +
                "Wood: " + newUserAfternodeAttack.getWood() + "\n" +
                "Troops: " + newUserAfternodeAttack.getTroops() + "\n" +
                "X: " + newUserAfternodeAttack.getX() + "\n" +
                "Y: " + newUserAfternodeAttack.getY() + "\n"
        );

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
                .characterEncoding("utf-8")
                .header("authoriztion", jwtTokenUserOne))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();
        // get userdate from user 1 after upgrading buildings
        MvcResult UserOneResourceData = mvc.perform(MockMvcRequestBuilders.get(UserData)
                .characterEncoding("utf-8")
                .header("authoriztion", jwtTokenUserOne))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();
        // Nice view for showing upgrade test data
        UserDataResponse userDataAfterUpgrade = mapFromJson(UserOneResourceData.getResponse().getContentAsString(), UserDataResponse.class);
        CityDataResponse CityDataAfterUpgrade = mapFromJson(UserOneUpgradeData.getResponse().getContentAsString(), CityDataResponse.class);

        System.out.println(
                "City Data(User1) After upgrade: " + "\n" +
                "Owner: " + CityDataAfterUpgrade.getOwner() + "\n" +
                "Barrack level: " + CityDataAfterUpgrade.getBarrack() + "\n" +
                "Castle level: " + CityDataAfterUpgrade.getCastle() + "\n" +
                "Forgery level: " + CityDataAfterUpgrade.getForgery() + "\n" +
                "Mine level: " + CityDataAfterUpgrade.getMine() + "\n" +
                "Oven level: " + CityDataAfterUpgrade.getOven() + "\n" +
                "Woodwork level: " + CityDataAfterUpgrade.getWoodwork() + "\n" + "\n" +

                "User1 Data after upgrade: " + "\n" +
                "Username: " + userDataAfterUpgrade.getUsername() + "\n" +
                "Food: " + userDataAfterUpgrade.getFood() + "\n" +
                "Iron: " + userDataAfterUpgrade.getIron() + "\n" +
                "Stone: " + userDataAfterUpgrade.getStone() + "\n" +
                "Wood: " + userDataAfterUpgrade.getWood() + "\n" +
                "Troops: " + userDataAfterUpgrade.getTroops() + "\n" +
                "X: " + userDataAfterUpgrade.getX() + "\n" +
                "Y: " + userDataAfterUpgrade.getY() + "\n"
        );
        //System.out.println(UseroneAttacknodeData.getResponse());
        // user1 recruit units
        MvcResult UseroneRecruitUnits = mvc.perform(MockMvcRequestBuilders.get(userRecruit)
                .characterEncoding("utf-8")
                .header("authoriztion", jwtTokenUserOne))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();
        // User1 data after recruit
        MvcResult useronerecruitdata = mvc.perform(MockMvcRequestBuilders.get(UserData)
                .characterEncoding("utf-8")
                .header("authoriztion", jwtTokenUserOne))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();
        UserDataResponse userrecruitdata = mapFromJson(useronerecruitdata.getResponse().getContentAsString(), UserDataResponse.class);

        System.out.println(
                "User1 Data after recruit(200 Troops): " + "\n" +
                        "Username: " + userrecruitdata.getUsername() + "\n" +
                        "Food: " + userrecruitdata.getFood() + "\n" +
                        "Iron: " + userrecruitdata.getIron() + "\n" +
                        "Stone: " + userrecruitdata.getStone() + "\n" +
                        "Wood: " + userrecruitdata.getWood() + "\n" +
                        "Troops: " + userrecruitdata.getTroops() + "\n" +
                        "X: " + userrecruitdata.getX() + "\n" +
                        "Y: " + userrecruitdata.getY() + "\n"
        );
        // Player one attack player two
        // switch to player2
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("user2");
        MvcResult userTwoCoordinates = mvc.perform(MockMvcRequestBuilders.get(userCoordinatesUri)
                .characterEncoding("utf-8")
                .header("Authorization", jwtTokenUserOne))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();

        String playerTwoX = userTwoCoordinates.getResponse().getContentAsString().split("\"")[2].split(":")[1].split(",")[0];
        String playerTwoY = userTwoCoordinates.getResponse().getContentAsString().split("\"")[4].split(":")[1].split("}")[0];


        //System.out.println(playerTwoX + "and" + playerTwoY);
        String attackPlayerTwoUri = "/attack/" + playerTwoX + "/" + playerTwoY;

        // switch to player 1
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("user1");
        MvcResult userOneAttackTwo = mvc.perform(MockMvcRequestBuilders.get(attackPlayerTwoUri)
                .characterEncoding("utf-8")
                .header("Authorization", jwtTokenUserOne))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();

        // switch to player 2
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("user2");
        //check for infomation changes after attack
        MvcResult userTwoLostResources = mvc.perform(MockMvcRequestBuilders.get(UserData)
                .characterEncoding("utf-8")
                .header("Authorization", jwtTokenUserTwo))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();
        // Assert food, iron, stone, wood won is equal to 100
        UserDataResponse user2afterattack = mapFromJson(userTwoLostResources.getResponse().getContentAsString(), UserDataResponse.class);
        UserDataResponse assertequal = new UserDataResponse();
        assertequal.setFood(200);
        assertequal.setStone(200);
        assertequal.setWood(200);
        assertequal.setIron(200);
        Assert.assertEquals(user2afterattack.getFood(),assertequal.getFood() );
        Assert.assertEquals(user2afterattack.getIron(),assertequal.getIron() );
        Assert.assertEquals(user2afterattack.getWood(),assertequal.getWood() );
        Assert.assertEquals(user2afterattack.getStone(),assertequal.getStone() );

        System.out.println(
                "User2 Data after User1 attack: " + "\n" +
                        "Username: " + user2afterattack.getUsername() + "\n" +
                        "Food: " + user2afterattack.getFood() + "\n" +
                        "Iron: " + user2afterattack.getIron() + "\n" +
                        "Stone: " + user2afterattack.getStone() + "\n" +
                        "Wood: " + user2afterattack.getWood() + "\n" +
                        "Troops: " + user2afterattack.getTroops() + "\n" +
                        "X: " + user2afterattack.getX() + "\n" +
                        "Y: " + user2afterattack.getY() + "\n"
        );
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
