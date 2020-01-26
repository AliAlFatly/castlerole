package com.example.castlerole.service;

import com.example.castlerole.model.User;
import com.example.castlerole.model.response.Tooptip;
import com.example.castlerole.model.response.UserDataResponse;
import com.example.castlerole.repository.CityRepository;
import com.example.castlerole.model.response.CityDataResponse;
import com.example.castlerole.model.City;
import com.example.castlerole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // In array: food, wood, stone, iron
    private HashMap<String, int[]> resourcesConsumptionByActionMapper = new HashMap<>();
    // Max building level
    private int maxBuildingLevel = 30;
    private String success = "Success";

    private final String castle = "Castle";
    private final String barrack = "Barrack";
    private final String forgery = "Forgery";
    private final String mine = "Mine";
    private final String oven = "Oven";
    private final String woodworks = "Woodworks";

    private void setLevelUpResources(){
        // In array: food, wood, stone, iron
        resourcesConsumptionByActionMapper.put(castle, new int[]{1,1,1,1});
        resourcesConsumptionByActionMapper.put(barrack, new int[]{1,1,1,1});
        resourcesConsumptionByActionMapper.put(forgery, new int[]{1,1,1,1});
        resourcesConsumptionByActionMapper.put(mine, new int[]{1,1,1,1});
        resourcesConsumptionByActionMapper.put(oven, new int[]{1,1,1,1});
        resourcesConsumptionByActionMapper.put(woodworks, new int[]{1,1,1,1});
        resourcesConsumptionByActionMapper.put("Recruit troops", new int[]{20,20,4,10});
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
        setLevelUpResources();
        // Get the array specifiek for the chosen building
        int[] resourcesMultipliersArray = resourcesConsumptionByActionMapper.get(action);

        // Set resources values after the update is complete
        int afterFood = userdata.getFood() - levelUpConsumptionFormula(resourcesMultipliersArray[0], level);
        int afterWood = userdata.getWood() - levelUpConsumptionFormula(resourcesMultipliersArray[1], level);
        int afterStone = userdata.getStone() - levelUpConsumptionFormula(resourcesMultipliersArray[2], level);
        int afterIron = userdata.getIron() - levelUpConsumptionFormula(resourcesMultipliersArray[3], level);

        // Check if the resources are below 0, in that case return not enough resources
        if (afterFood < 0 || afterWood < 0 || afterStone < 0 || afterIron < 0){
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
            city = cityRepository.findById(user.getId());
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
        City city = cityRepository.findById(user.getId());
        String response;
        int newLevel;
        switch (action){
            case castle:
                response = consumeResources(username, action, city.getCastleLevel());
                if (response.equals(success)){
                    newLevel = city.getCastleLevel() + 1;
                    cityRepository.updateCastleLevel(newLevel, user.getId());
                }
                break;
            case barrack:
                response = consumeResources(username, action, city.getBarracksLevel());
                if (response.equals(success)){
                    newLevel = city.getBarracksLevel() + 1;
                    cityRepository.updateBarracksLevel(newLevel, user.getId());
                }
                break;
            case forgery:
                response = consumeResources(username, action, city.getForgeryLevel());
                if (response.equals(success)){
                    newLevel = city.getForgeryLevel() + 1;
                    cityRepository.updateForgeryLevel(newLevel, user.getId());
                }
                break;
            case mine:
                response = consumeResources(username, action, city.getMineLevel());
                if (response.equals(success)){
                    newLevel = city.getMineLevel() + 1;
                    cityRepository.updateMineLevel(newLevel, user.getId());
                }
                break;
            case oven:
                response = consumeResources(username, action, city.getOvenLevel());
                if (response.equals(success)){
                    newLevel = city.getOvenLevel() + 1;
                    cityRepository.updateOvensLevel(newLevel, user.getId());
                }
                break;
            case woodworks:
                response = consumeResources(username, action, city.getWoodworksLevel());
                if (response.equals(success)){
                    newLevel = city.getWoodworksLevel() + 1;
                    cityRepository.updateWoodworksLevel(newLevel, user.getId());
                }
                break;
            default:
                response = "unknown building";
        }
        return response;
    }


    public Tooptip getTooltipData(String username, String action) throws Exception {
        User user = userRepository.findByUsername(username);
        City city = cityRepository.findById(user.getId());
        Tooptip response = new Tooptip();
        int level;
        boolean continueBool = false;
        switch (action){
            case castle:
                level = city.getCastleLevel();
                continueBool = true;
                break;
            case barrack:
                level = city.getBarracksLevel();
                continueBool = true;
                break;
            case forgery:
                level = city.getForgeryLevel();
                continueBool = true;
                break;
            case mine:
                level = city.getMineLevel();
                continueBool = true;
                break;
            case oven:
                level = city.getOvenLevel();
                continueBool = true;
                break;
            case woodworks:
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
            setLevelUpResources();
            // Get the array specifiek for the chosen building
            int[] resourcesMultipliersArray = resourcesConsumptionByActionMapper.get(action);

            int requiredFood = levelUpConsumptionFormula(resourcesMultipliersArray[0], level);
            int requiredWood = levelUpConsumptionFormula(resourcesMultipliersArray[1], level);
            int requiredStone = levelUpConsumptionFormula(resourcesMultipliersArray[2], level);
            int requiredIron = levelUpConsumptionFormula(resourcesMultipliersArray[3], level);

            response.setFood(requiredFood);
            response.setWood(requiredWood);
            response.setStone(requiredStone);
            response.setIron(requiredIron);


        }
        return response;

    }
}
