package com.example.Castlerole.service;

import com.example.Castlerole.config.JwtTokenUtil;
import com.example.Castlerole.model.dto.UserDTO;
import com.example.Castlerole.model.request.JwtRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;


@RunWith(MockitoJUnitRunner.class)
public class JwtAuthenticationServiceTest {

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

   @Before
   public void setUp() {
      MockitoAnnotations.initMocks(this);

   }
   @Test
   public void Jwtauthenticate_Test() throws Exception {
      UserDTO jwtuserMock = new UserDTO();
      jwtuserMock.setUsername("jwtUserService");
      jwtuserMock.setPassword("password");
      try {
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtuserMock.getUsername(), jwtuserMock.getPassword()));
      } catch (DisabledException e) {
         throw new Exception("USER_DISABLED", e);
      } catch (BadCredentialsException e) {
         throw new Exception("INVALID_CREDENTIALS", e);
      }

   }

   @Test
   public void login_Test() throws Exception{
      UserDTO jwtuserMock = new UserDTO();
      jwtuserMock.setUsername("jwtUserService");
      jwtuserMock.setPassword("password");
      UserDetails userDetails = userDetailsService.loadUserByUsername(jwtuserMock.getUsername());
      //generate token based on user claims.
      String token = jwtTokenUtil.generateToken(userDetails);
      //return token.
      System.out.println(token);
   }

   @Test
   public void register_Test() throws Exception{
      UserDTO jwtuserMock = new UserDTO();
      jwtuserMock.setUsername("jwtUserService");
      jwtuserMock.setPassword("password");
      boolean exist = userService.UserExist(jwtuserMock.getUsername());
      if (exist){
         //todo: generic excpetion veranderen naar speciek
         System.out.println("User with the username {" + jwtuserMock.getUsername() + "} already exists");
      }
      userDetailsService.registerNewUser(jwtuserMock);
      UserDetails userDetails = userDetailsService.loadUserByUsername(jwtuserMock.getUsername());
      //generate token
      String token = jwtTokenUtil.generateToken(userDetails);
      //return token
      System.out.println(token);
   }




}
