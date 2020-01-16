package com.example.Castlerole.service;

import com.example.Castlerole.model.dto.UserDTO;
import com.example.Castlerole.repository.NodeRepository;
import com.example.Castlerole.repository.UserRepository;
import org.junit.Before;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class JwtUserServiceTests {

    @Mock
    private UserRepository userRepository;
    @Mock
    private NodeRepository nodeRepository;
    @Mock
    private PasswordEncoder bcryptEncoder;
    @InjectMocks
    private JwtUserService jwtUserService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        int gridSize = 500;


    }

    @Test
    public void JwtUserService_getXYTest() throws Exception {
        jwtUserService.getXY();

    }

    @Test
    public void JwtUserService_RegisterTest() throws Exception {
        UserDTO jwtuserMock = new UserDTO();
        jwtuserMock.setUsername("jwtUserService");
        jwtuserMock.setPassword("password");
        jwtUserService.registerNewUser(jwtuserMock);
    }

    @Test
    public void JwtUserService_LoadUserTest() {
        jwtUserService.loadUserByUsername("jwtUserService");
    }

}
