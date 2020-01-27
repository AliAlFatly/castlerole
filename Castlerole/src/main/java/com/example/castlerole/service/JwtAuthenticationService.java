package com.example.castlerole.service;

import com.example.castlerole.config.JwtTokenUtil;
import com.example.castlerole.model.dto.UserDTO;
import com.example.castlerole.model.request.JwtRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public class JwtAuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserService userDetailsService;

    @Autowired
    private UserService userService;

    public String login(JwtRequest authenticationRequest) throws Exception {
        // Check if user is disabled or if credentials are invalid. if true => return error. break function when returned.
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        // Get user from database, if user not found throw exception and break function when returned.
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        // Generate token based on user claims.
        return jwtTokenUtil.generateToken(userDetails);
    }

    public String register(UserDTO user) throws Exception {
        // If user exist throw exception
        boolean exist = userService.userExist(user.getUsername());
        if (exist){
            throw new EntityExistsException("User with the username {" + user.getUsername() + "} already exists");
        }
        // After registration save new user
        var newUser = userDetailsService.registerNewUser(user);
        userDetailsService.registerNewCity(newUser);
        // Get userDetails
        final UserDetails userDetails = userDetailsService.loadUserByUsername(newUser.getUsername());
        // Generate token
        return jwtTokenUtil.generateToken(userDetails);
    }

    private void authenticate(String username, String password) throws EntityNotFoundException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new EntityNotFoundException("INVALID_CREDENTIALS");
        }
    }


}
