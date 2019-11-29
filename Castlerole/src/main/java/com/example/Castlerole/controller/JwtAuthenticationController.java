package com.example.Castlerole.controller;
import com.example.Castlerole.config.JwtTokenUtil;
import com.example.Castlerole.model.request.JwtRequest;
import com.example.Castlerole.model.response.JwtResponse;
import com.example.Castlerole.model.dto.UserDTO;
import com.example.Castlerole.service.JwtAuthenticationService;
import com.example.Castlerole.service.JwtUserService;
import com.example.Castlerole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JwtAuthenticationController {

    @Autowired
    public JwtAuthenticationService jwtAuthenticationService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {
        final String token = jwtAuthenticationService.login(authenticationRequest);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody UserDTO user) throws Exception {
        final String token = jwtAuthenticationService.register(user);
        return ResponseEntity.ok(new JwtResponse(token));
    }

}