package com.example.Castlerole.service;

import com.example.Castlerole.Config.JpaConfig;
import com.example.Castlerole.model.User;
import com.example.Castlerole.model.dto.UserDTO;
import com.example.Castlerole.repository.NodeRepository;
import com.example.Castlerole.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.util.FieldUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.mock;

//@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
//@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = JwtUserService.class)
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration
@TestPropertySource(properties = {"gridSize=500", "jwt.secret=secret"})
//@SpringBootTest(properties = "gridSize=500")
//@SpringJUnitConfig(classes = JwtUserService.class)
public class JwtUserServiceTests extends JpaConfig {

    //@Value("${gridSize}") private int gridSize;
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
        ReflectionTestUtils.setField(jwtUserService, "gridSize", 500);
        //ReflectionTestUtils.setField(jwtUserService, "jwt.secret", "secret");
        Mockito.when(userRepository.findByUsername("jwtUserService")).thenReturn(new User("jwtUserService", "password"));
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
    public void JwtUserService_LoadUserTest() throws Exception {
        jwtUserService.loadUserByUsername("jwtUserService");
    }

}
