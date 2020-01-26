package com.example.castlerole.controller;
import com.example.castlerole.model.request.JwtRequest;
import com.example.castlerole.model.response.JwtResponse;
import com.example.castlerole.model.dto.UserDTO;
import com.example.castlerole.service.JwtAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin()
//maybe move injected classed to a service later and make controller cleaner.
public class JwtAuthenticationController {

    @Autowired
    public JwtAuthenticationService jwtAuthenticationService;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody JwtRequest authenticationRequest) throws Exception {
        final String token = jwtAuthenticationService.login(authenticationRequest);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody UserDTO user) throws Exception {
        final String token = jwtAuthenticationService.register(user);
        return ResponseEntity.ok(new JwtResponse(token));
    }

}