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

    private static final String CASTLE = "Castle";
    private static final String BARRACK = "Barrack";
    private static final String FORGERY = "Forgery";
    private static final String MINE = "Mine";
    private static final String OVEN = "Oven";
    private static final String WOODWORK = "Woodwork";

    private void setLevelUpResources(){
        // In array: food, wood, stone, iron
        resourcesConsumptionByActionMapper.put(CASTLE, new int[]{1,1,20,20});
        resourcesConsumptionByActionMapper.put(BARRACK, new int[]{20,15,20,15});
        resourcesConsumptionByActionMapper.put(FORGERY, new int[]{10,5,16,20});
        resourcesConsumptionByActionMapper.put(MINE, new int[]{11,16,4,25});
        resourcesConsumptionByActionMapper.put(OVEN, new int[]{10,15,12,6});
        resourcesConsumptionByActionMapper.put(WOODWORK, new int[]{20,9,10,15});
        resourcesConsumptionByActionMapper.put("Recruit troops", new int[]{20,20,4,10});
    }

    // Resource consumption formula for buildings upgrade
    private int levelUpConsumptionFormula(int base, int level){
        return (base * 15) + (level * level);
    }

    // Resources consumption function
    private String consumeResources(String username, String action, int level) {
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

    public CityDataResponse getCityData(String username){
        City city;
        User user = userRepository.findByUsername(username);
        city = cityRepository.findById(user.getId());

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

    public String updateBuilding(String username, String action) {
        User user = userRepository.findByUsername(username);
        City city = cityRepository.findById(user.getId());
        String response;
        int newLevel;
        switch (action){
            case CASTLE:
                response = consumeResources(username, action, city.getCastleLevel());
                if (response.equals(success)){
                    newLevel = city.getCastleLevel() + 1;
                    cityRepository.updateCastleLevel(newLevel, user.getId());
                }
                break;
            case BARRACK:
                response = consumeResources(username, action, city.getBarracksLevel());
                if (response.equals(success)){
                    newLevel = city.getBarracksLevel() + 1;
                    cityRepository.updateBarracksLevel(newLevel, user.getId());
                }
                break;
            case FORGERY:
                response = consumeResources(username, action, city.getForgeryLevel());
                if (response.equals(success)){
                    newLevel = city.getForgeryLevel() + 1;
                    cityRepository.updateForgeryLevel(newLevel, user.getId());
                }
                break;
            case MINE:
                response = consumeResources(username, action, city.getMineLevel());
                if (response.equals(success)){
                    newLevel = city.getMineLevel() + 1;
                    cityRepository.updateMineLevel(newLevel, user.getId());
                }
                break;
            case OVEN:
                response = consumeResources(username, action, city.getOvenLevel());
                if (response.equals(success)){
                    newLevel = city.getOvenLevel() + 1;
                    cityRepository.updateOvensLevel(newLevel, user.getId());
                }
                break;
            case WOODWORK:
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


    public Tooptip getTooltipData(String username, String action) {
        User user = userRepository.findByUsername(username);
        City city = cityRepository.findById(user.getId());
        Tooptip response = new Tooptip();
        int level;
        boolean continueBool = false;
        switch (action){
            case CASTLE:
                level = city.getCastleLevel();
                continueBool = true;
                break;
            case BARRACK:
                level = city.getBarracksLevel();
                continueBool = true;
                break;
            case FORGERY:
                level = city.getForgeryLevel();
                continueBool = true;
                break;
            case MINE:
                level = city.getMineLevel();
                continueBool = true;
                break;
            case OVEN:
                level = city.getOvenLevel();
                continueBool = true;
                break;
            case WOODWORK:
                level = city.getWoodworksLevel();
                continueBool = true;
                break;
            default:
                level = maxBuildingLevel + 1;
                break;
        }
        if (continueBool){
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
