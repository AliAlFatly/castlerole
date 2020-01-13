package com.example.Castlerole.service;

import com.example.Castlerole.model.dto.UserDTO;
import com.example.Castlerole.repository.NodeRepository;
import com.example.Castlerole.repository.UserRepository;
import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class JwtUserServiceTests {

    @Mock
    private UserRepository userRepository;
    @Mock
    private NodeRepository nodeRepository;
    @Mock
    private PasswordEncoder bcryptEncoder;
    @InjectMocks
    private JwtUserService jwtUserService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        int gridSize = 500;


    }

    @Test
    public String JwtUserService_getXYTest() throws Exception {
        jwtUserService.getXY();
        return "Done";

    }

    @Test
    public String JwtUserService_RegisterTest() throws Exception {
        UserDTO jwtuserMock = new UserDTO();
        jwtuserMock.setUsername("jwtUserService");
        jwtuserMock.setPassword("password");
        jwtUserService.registerNewUser(jwtuserMock);
        return "Done";
    }

    @Test
    public String JwtUserService_LoadUserTest() {
        jwtUserService.loadUserByUsername("jwtUserService");
        return "Done";
    }

}
