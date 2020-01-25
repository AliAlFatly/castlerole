package com.example.Castlerole.service;

import com.example.Castlerole.config.JwtTokenUtil;
import com.example.Castlerole.model.User;
import com.example.Castlerole.model.response.BuildingTooptip;
import com.example.Castlerole.model.response.UserDataResponse;
import com.example.Castlerole.repository.CityRepository;
import com.example.Castlerole.model.response.CityDataResponse;
import com.example.Castlerole.model.City;
import com.example.Castlerole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.HashMap.*;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // In array: food, wood, stone, iron
    private HashMap<String, int[]> ResourcesConsumptionByActionMapper = new HashMap<String, int[]>();
    // Max building level
    private int maxBuildingLevel = 30;
    private String success = "Success";


    //    private int[] castleLevelUpResources = {1,2,3,1};
//    private int[] barackLevelUpResources = {1,2,3,1};
//    private int[] forgeryLevelUpResources = {1,2,3,1};
//    private int[] mineLevelUpResources = {1,2,3,1};
//    private int[] ovenLevelUpResources = {1,2,3,1};
//    private int[] woodworksLevelUpResources = {1,2,3,1};



    private void SetLevelUpResources(){
        // In array: food, wood, stone, iron
        ResourcesConsumptionByActionMapper.put("Castle", new int[]{1,1,1,1});
        ResourcesConsumptionByActionMapper.put("Barack", new int[]{1,1,1,1});
        ResourcesConsumptionByActionMapper.put("Forgery", new int[]{1,1,1,1});
        ResourcesConsumptionByActionMapper.put("Mine", new int[]{1,1,1,1});
        ResourcesConsumptionByActionMapper.put("Oven", new int[]{1,1,1,1});
        ResourcesConsumptionByActionMapper.put("Woodworks", new int[]{1,1,1,1});
        ResourcesConsumptionByActionMapper.put("Recruit troops", new int[]{20,20,4,10});
    }

    // Resource consumption formula for buildings upgrade
    private int levelUpConsumptionFormula(int base, int level){
        return (base * 15) + (level * level);
    }

    // Resources consumption function
    private String consumeResources(String username, String action, int level) throws Exception {
        // Check if building level is already max
        if (level >= maxBuildingLevel){
            return "Building is already max level";
        }
        // Get user data
        UserDataResponse userdata = userService.getUserData(username);
        // Set base resources for leveling up for each building and save them into the hashmap
        SetLevelUpResources();
        // Get the array specifiek for the chosen building
        int[] resourcesMultipliersArray = ResourcesConsumptionByActionMapper.get(action);

        // Set resources values after the update is complete
        int afterFood = userdata.getFood() - levelUpConsumptionFormula(resourcesMultipliersArray[0], level);
        int afterWood = userdata.getWood() - levelUpConsumptionFormula(resourcesMultipliersArray[1], level);
        int afterStone = userdata.getStone() - levelUpConsumptionFormula(resourcesMultipliersArray[2], level);
        int afterIron = userdata.getIron() - levelUpConsumptionFormula(resourcesMultipliersArray[3], level);

        // Check if the resources are below 0, in that case return not enough resources
        if (afterFood < 0 | afterWood < 0 | afterStone < 0 | afterIron < 0){
            return "Not enough resources";
        }

        // Update the new value of the resources into the database and return Success
        userRepository.updateResources(username, afterFood, afterWood, afterStone, afterIron);
        return success;
    }


    public CityDataResponse getCityData(String username) throws Exception{
        City city;
        User user = userRepository.findByUsername(username);
        try{
            city = cityRepository.findByid(user.getId());
        }
        catch(Exception error){
            throw new Exception("Cannot find a corresponding city!");
        }
        return new CityDataResponse(

                city.getUser().getUsername(),
                city.getCastleLevel(),
                city.getWoodworksLevel(),
                city.getMineLevel(),
                city.getForgeryLevel(),
                city.getBarracksLevel(),
                city.getOvenLevel()
        );
    }

    public String updateBuilding(String username, String action) throws Exception {
        User user = userRepository.findByUsername(username);
        City city = cityRepository.findByid(user.getId());
        String response;
        int newLevel;
        switch (action){
            case "Castle":
                response = consumeResources(username, action, city.getCastleLevel());
                if (response == success){
                    newLevel = city.getCastleLevel() + 1;
                    cityRepository.updateCastleLevel(newLevel, user.getId());
                }
                break;
            case "Barack":
                response = consumeResources(username, action, city.getBarracksLevel());
                if (response == success){
                    newLevel = city.getBarracksLevel() + 1;
                    cityRepository.updateBarracksLevel(newLevel, user.getId());
                }
                break;
            case "Forgery":
                response = consumeResources(username, action, city.getForgeryLevel());
                if (response == success){
                    newLevel = city.getForgeryLevel() + 1;
                    cityRepository.updateForgeryLevel(newLevel, user.getId());
                }
                break;
            case "Mine":
                response = consumeResources(username, action, city.getMineLevel());
                if (response == success){
                    newLevel = city.getMineLevel() + 1;
                    cityRepository.updateMineLevel(newLevel, user.getId());
                }
                break;
            case "Oven":
                response = consumeResources(username, action, city.getOvenLevel());
                if (response == success){
                    newLevel = city.getOvenLevel() + 1;
                    cityRepository.updateOvensLevel(newLevel, user.getId());
                }
                break;
            case "Woodworks":
                response = consumeResources(username, action, city.getWoodworksLevel());
                if (response == success){
                    newLevel = city.getWoodworksLevel() + 1;
                    cityRepository.updateWoodworksLevel(newLevel, user.getId());
                }
                break;
            default:
                response = "unknown building";
        }
        return response;
    }


    public BuildingTooptip getTooltipData(String username, String action) throws Exception {
        User user = userRepository.findByUsername(username);
        City city = cityRepository.findByid(user.getId());
        BuildingTooptip response = new BuildingTooptip();
        int level;
        boolean continueBool = false;
        switch (action){
            case "Castle":
                level = city.getCastleLevel();
                continueBool = true;
                break;
            case "Barack":
                level = city.getBarracksLevel();
                continueBool = true;
                break;
            case "Forgery":
                level = city.getForgeryLevel();
                continueBool = true;
                break;
            case "Mine":
                level = city.getMineLevel();
                continueBool = true;
                break;
            case "Oven":
                level = city.getOvenLevel();
                continueBool = true;
                break;
            case "Woodworks":
                level = city.getWoodworksLevel();
                continueBool = true;
                break;
            default:
                level = maxBuildingLevel + 1;
                break;
        }
        if (continueBool){
            // Get user data
            UserDataResponse userdata = userService.getUserData(username);
            // Set base resources for leveling up for each building and save them into the hashmap
            SetLevelUpResources();
            // Get the array specifiek for the chosen building
            int[] resourcesMultipliersArray = ResourcesConsumptionByActionMapper.get(action);

            boolean upgradeable = true;

            int requiredFood = levelUpConsumptionFormula(resourcesMultipliersArray[0], level);
            int requiredWood = levelUpConsumptionFormula(resourcesMultipliersArray[1], level);
            int requiredStone = levelUpConsumptionFormula(resourcesMultipliersArray[2], level);
            int requiredIron = levelUpConsumptionFormula(resourcesMultipliersArray[3], level);

            int afterFood = userdata.getFood() - requiredFood;
            int afterWood = userdata.getWood() - requiredWood;
            int afterStone = userdata.getStone() - requiredStone;
            int afterIron = userdata.getIron() - requiredIron;

            // Check if the resources are below 0, in that case return not enough resources
            if (afterFood < 0 | afterWood < 0 | afterStone < 0 | afterIron < 0){
                upgradeable = false;
            }
            response.setFood(requiredFood);
            response.setWood(requiredWood);
            response.setStone(requiredStone);
            response.setIron(requiredIron);
            response.setUpgradeable(upgradeable);

        }
        return response;

    }
}
