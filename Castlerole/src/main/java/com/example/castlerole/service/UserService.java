package com.example.castlerole.service;

import com.example.castlerole.model.User;
import com.example.castlerole.model.helpertypes.Vector;
import com.example.castlerole.model.response.UserDataResponse;
import com.example.castlerole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private int troopRecruitmentWood = 20;
    private int troopRecruitmentFood = 20;
    private int troopRecruitmentIron = 10;
    private int troopRecruitmentStone = 4;

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

    public String recruit(int amount, String username) throws Exception {
        UserDataResponse userdata = getUserData(username);
        // Check if the user has enough resources for the given amount, in angular -1 => not enough resources
        if(foodAfterRecruit(userdata.getFood(), amount) < 0){
            return "not enough resources";
        }
        if(woodAfterRecruit(userdata.getWood(), amount) < 0){
            return "not enough resources";
        }
        if(stoneAfterRecruit(userdata.getStone(), amount) < 0){
            return "not enough resources";
        }
        if(ironAfterRecruit(userdata.getIron(), amount) < 0){
            return "not enough resources";
        }

        //recruit
        int afterFood = foodAfterRecruit(userdata.getFood(), amount);
        userRepository.updateFood(afterFood, username);

        int afterWood = woodAfterRecruit(userdata.getWood(), amount);
        userRepository.updateWood(afterWood, username);

        int afterStone = stoneAfterRecruit(userdata.getStone(), amount);
        userRepository.updateStone(afterStone, username);

        int afterIron = ironAfterRecruit(userdata.getIron(), amount);
        userRepository.updateIron(afterIron, username);

        int afterTroops = userdata.getTroops() + amount;
        userRepository.updateTroops(afterTroops, username);

        //response
        return "success";
    }


    public int getRecruitmentTooltip(String username) throws Exception {
        UserDataResponse userdata = getUserData(username);
        var checkOne = Math.min((userdata.getFood() / troopRecruitmentFood), (userdata.getWood() / troopRecruitmentWood));
        var checkTwo = Math.min((userdata.getStone() / troopRecruitmentStone), (userdata.getIron() / troopRecruitmentIron));
        var maximumAmount = Math.min(checkOne, checkTwo);
        return maximumAmount;
    }

    private int foodAfterRecruit(int food, int amount){
        return food - (amount * troopRecruitmentFood);
    }

    private int woodAfterRecruit(int wood, int amount){
        return wood - (amount * troopRecruitmentWood);
    }

    private int stoneAfterRecruit(int stone, int amount){
        return stone - (amount * troopRecruitmentStone);
    }

    private int ironAfterRecruit(int iron, int amount){
        return iron - (amount * troopRecruitmentIron);
    }


}
