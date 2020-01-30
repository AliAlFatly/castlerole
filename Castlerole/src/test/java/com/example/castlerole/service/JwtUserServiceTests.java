package com.example.castlerole.service;

import com.example.castlerole.model.User;
import com.example.castlerole.model.dto.UserDTO;
import com.example.castlerole.repository.CityRepository;
import com.example.castlerole.repository.NodeRepository;
import com.example.castlerole.repository.UserRepository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.Mockito.mock;

@SpringBootTest(classes = JwtUserService.class)
public class JwtUserServiceTests {


    @MockBean
    private UserRepository userRepository;
    @MockBean
    private NodeRepository nodeRepository;
    @MockBean
    private CityRepository cityRepository;
    @MockBean
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private JwtUserService jwtUserService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(userRepository.findByUsername("jwtUserService")).thenReturn(new User("jwtUserService", "password"));

    }

    @Test
    public void JwtUserService_getXYTest() throws Exception {
        var xy = jwtUserService.getXY();
        Assert.assertNotNull(xy);
    }

    @Test
    public void JwtUserService_RegisterTest() throws Exception {
        UserDTO jwtuserMock = new UserDTO();
        jwtuserMock.setUsername("jwtUserService");
        jwtuserMock.setPassword("password");
        Mockito.when(userRepository.save(any())).thenAnswer((Answer<User>) invocation -> {
            Object args = invocation.getArgument(0);
            var user1 = new ObjectMapper();
            user1.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            User newUser = user1.convertValue(args,User.class);
            Assert.assertEquals("jwtUserService",newUser.getUsername());
            Assert.assertEquals(300, newUser.getTroops());
            Assert.assertEquals(300, newUser.getFood());
            Assert.assertEquals(300, newUser.getIron());
            Assert.assertEquals("player", newUser.getPictureReference());
            return (User) args;
        });
        jwtUserService.registerNewUser(jwtuserMock);

    }

    @Test
    public void JwtUserService_LoadUserTest() throws Exception {
        var user = jwtUserService.loadUserByUsername("jwtUserService");
        Assert.assertEquals("jwtUserService", user.getUsername());
        Assert.assertEquals("password", user.getPassword());
    }

}
