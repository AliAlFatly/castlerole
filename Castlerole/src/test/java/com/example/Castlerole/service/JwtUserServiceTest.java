package com.example.Castlerole.service;

import com.example.Castlerole.model.User;
import com.example.Castlerole.model.dto.UserDTO;
import com.example.Castlerole.model.helpertypes.IntVector;
import com.example.Castlerole.repository.NodeRepository;
import com.example.Castlerole.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class JwtUserServiceTest {

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
    public void JwtUserService_getXYTest() throws Exception {
        jwtUserService.getXY();

    }

    @Test
    public void JwtUserService_RegisterTest(UserDTO user) throws Exception {
        UserDTO jwtuserMock = new UserDTO();
        jwtuserMock.setUsername("jwtUserService");
        jwtuserMock.setPassword("password");
        int xCoordinate = 200;
        int yCoordinate = 250;
        Date date = new Date(System.currentTimeMillis());
        jwtUserService.registerNewUser(jwtuserMock);

        User newUser = new User(
                user.getUsername(),
                bcryptEncoder.encode(user.getPassword()),
                (java.sql.Date) date,
                xCoordinate,
                yCoordinate,
                "player",
                300,
                300,
                300,
                300,
                300
        );
//        Assert.assertEquals(userRepository.save(newUser));
    }

    @Test
    public void JwtUserService_LoadUserTest() {
        jwtUserService.loadUserByUsername("jwtUserService");
    }

}
