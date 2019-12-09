package com.example.Castlerole.service;

import com.example.Castlerole.config.JwtTokenUtil;
import com.example.Castlerole.model.User;
import com.example.Castlerole.model.helpertypes.Vector;
import com.example.Castlerole.model.response.UserDataResponse;
import com.example.Castlerole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public boolean UserExist(String username) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return true;
        }
        return false;
    }

    public Vector getUserCoordinates(String username) throws Exception {
        //String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        //later make findByUsername return optional<user>, to combat fabricated jwtToken errors.
        User user;
        try{
            user = userRepository.findByUsername(username);
        }
        catch(Exception error){
            //log error
            throw new Exception("Something went wrong, this has been logged");
        }
        return new Vector(user.getxCoordinate(), user.getyCoordinate());
    }

    public UserDataResponse getUserData(String username) throws Exception {
        //String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        //later make findByUsername return optional<user>, to combat fabricated jwtToken errors.
        User user;
        //todo change try catch to beter error handling
        try{
            user = userRepository.findByUsername(username);
        }
        catch(Exception error){
            //log error
            throw new Exception("Something went wrong, this has been logged");
        }
        //fill in what must be filled
        //todo add transformer for this functionality
        return new UserDataResponse(
                user.getUsername(),
                user.getxCoordinate(),
                user.getyCoordinate(),
                user.getWood(),
                user.getIron(),
                user.getStone(),
                user.getFood(),
                user.getTroops()
        );
    }
}
