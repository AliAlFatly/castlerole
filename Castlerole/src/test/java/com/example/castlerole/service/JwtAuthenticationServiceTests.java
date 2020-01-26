package com.example.castlerole.service;

import com.example.castlerole.config.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;

//@ExtendWith(MockitoExtension.class)
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
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void Jwtauthenticate_Test() {

    }

}
