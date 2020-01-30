package com.example.castlerole.service;

import com.example.castlerole.config.JwtTokenUtil;
import com.example.castlerole.model.City;
import com.example.castlerole.model.User;
import com.example.castlerole.model.dto.UserDTO;
import com.example.castlerole.model.response.Tooptip;
import com.example.castlerole.repository.CityRepository;
import com.example.castlerole.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class JwtAuthenticationServiceTests {


    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtTokenUtil jwtTokenUtil;
    @Mock
    private JwtUserService userDetailsService;
    @Mock
    private UserService userService;
    @InjectMocks
    private JwtAuthenticationService jwtAuthenticationService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        User newUserU = new User();
        newUserU.setId(1);
        newUserU.setUsername("User1");
        newUserU.setCoordinateX(25);
        newUserU.setCoordinateY(25);
        newUserU.setIron(300);
        newUserU.setFood(300);
        newUserU.setStone(300);
        newUserU.setWood(300);
        newUserU.setPassword("Password");
        newUserU.setTroops(300);
        City newCityu = new City();
        newCityu.setId(1);
        newCityu.setUser(newUserU);
        newCityu.setCasteLevel(1);
        newCityu.setWoodworksLevel(1);
        newCityu.setForgeryLevel(1);
        newCityu.setBarracksLevel(1);
        newCityu.setOvenLevel(1);
        newCityu.setMineLevel(1);
        UserDTO userdto = new UserDTO();
        userdto.setUsername("User1");
        userdto.setPassword("Password");
        Tooptip mocktoop = new Tooptip();
        mocktoop.setFood(200);
        mocktoop.setIron(200);
        mocktoop.setStone(200);
        mocktoop.setWood(200);
        Assert.assertEquals(200, mocktoop.getFood());
        Assert.assertEquals(200, mocktoop.getIron());
        Assert.assertEquals(200, mocktoop.getStone());
        Assert.assertEquals(200, mocktoop.getWood());


    }
    

    @Test
    public void Register_Test() throws Exception {

    }

}
