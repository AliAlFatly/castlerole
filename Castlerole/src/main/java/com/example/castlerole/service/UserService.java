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
    private static final String NOT_ENOUGH_RESOURCES = "not enough resources";

    public boolean userExist(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    public Vector getUserCoordinates(String username) {
        User user = userRepository.findByUsername(username);
        return new Vector(user.getxCoordinate(), user.getyCoordinate());
    }

    public UserDataResponse getUserData(String username) {
        User user = userRepository.findByUsername(username);
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

    public String recruit(int amount, String username) {
        UserDataResponse userdata = getUserData(username);
        // Check if the user has enough resources for the given amount, in angular -1 => not enough resources
        if(foodAfterRecruit(userdata.getFood(), amount) < 0){
            return NOT_ENOUGH_RESOURCES;
        }
        if(woodAfterRecruit(userdata.getWood(), amount) < 0){
            return NOT_ENOUGH_RESOURCES;
        }
        if(stoneAfterRecruit(userdata.getStone(), amount) < 0){
            return NOT_ENOUGH_RESOURCES;
        }
        if(ironAfterRecruit(userdata.getIron(), amount) < 0){
            return NOT_ENOUGH_RESOURCES;
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


    public int getRecruitmentTooltip(String username) {
        UserDataResponse userdata = getUserData(username);
        var checkOne = Math.min((userdata.getFood() / troopRecruitmentFood), (userdata.getWood() / troopRecruitmentWood));
        var checkTwo = Math.min((userdata.getStone() / troopRecruitmentStone), (userdata.getIron() / troopRecruitmentIron));
        return Math.min(checkOne, checkTwo);
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
