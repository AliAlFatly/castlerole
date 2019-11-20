package com.example.Castlerole.service;

import java.util.ArrayList;

import com.example.Castlerole.model.User;
import com.example.Castlerole.model.dto.UserDTO;
import com.example.Castlerole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Primary
public class JwtUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public User registerNewUser(UserDTO user) {
        //get UserDTO
        //initialize new empty user(to save in the database)
        User newUser = new User();
        //set username as given in dto
        newUser.setUsername(user.getUsername());
        //NOTE => when using password encryption set password length way more in database since encrypting increases the size of the string.
        //https://stackoverflow.com/questions/98768/should-i-impose-a-maximum-length-on-passwords
        //set to max 128 or no max.
        //encrypt password and set it.
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        //save new user into database.
        return userRepository.save(newUser);
    }
}