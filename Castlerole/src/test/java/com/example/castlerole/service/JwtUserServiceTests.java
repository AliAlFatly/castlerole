package com.example.castlerole.service;

import com.example.castlerole.model.User;
import com.example.castlerole.model.dto.UserDTO;
import com.example.castlerole.repository.NodeRepository;
import com.example.castlerole.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.mock;

//@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
//@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = JwtUserService.class)
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration
//@TestPropertySource(properties = {"gridSize=500", "jwt.secret=secret"})
//@SpringBootTest(properties = "gridSize=500")
//@SpringJUnitConfig(classes = JwtUserService.class)
public class JwtUserServiceTests {

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
