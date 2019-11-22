package com.example.Castlerole.controller;
import com.example.Castlerole.config.JwtTokenUtil;
import com.example.Castlerole.model.request.JwtRequest;
import com.example.Castlerole.model.response.JwtResponse;
import com.example.Castlerole.model.dto.UserDTO;
import com.example.Castlerole.service.JwtUserService;
import com.example.Castlerole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "https://localhost:4200")
//maybe move injected classed to a service later and make controller cleaner.
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {
        //check if user is disabled or if credentials are invalid. if true => return error. break function when returned.
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        //get user from database, if user not found throw exception and break function when returned.
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        //generate token based on user claims.
        final String token = jwtTokenUtil.generateToken(userDetails);
        //return token.
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserDTO user) throws Exception {
        //if user exist throw exception
        boolean exist = userService.UserExist(user.getUsername());
        if (exist){
            throw new Exception("User with the username {" + user.getUsername() + "} already exists");
        }
        //after registration save new user
        var newUser = userDetailsService.registerNewUser(user);
        //get userDetails
        final UserDetails userDetails = userDetailsService.loadUserByUsername(newUser.getUsername());
        //generate token
        final String token = jwtTokenUtil.generateToken(userDetails);
        //return token
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}