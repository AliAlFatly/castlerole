package com.example.Castlerole.service;

import com.example.Castlerole.model.City;
import com.example.Castlerole.model.User;
import com.example.Castlerole.repository.CityRepository;
import com.example.Castlerole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourcesUpdatingService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityService cityService;

    private int addFood = 10;
    private int addWood = 10;
    private int addStone = 6;
    private int addIron = 4;

    public void updateResources(){
        List<User> users = userRepository.findAll();

        for (User user: users
             ) {
            City city = cityRepository.findByid(user.getId());

            var newFood = user.getFood() + ((1 + city.getOvenLevel()/20 ) * addFood);
            var newWood = user.getWood() + ((1 + city.getWoodworksLevel()/20 ) * addWood);;
            var newStone = user.getStone() + ((1 + city.getMineLevel()/20 ) * addStone);;
            var newIron = user.getIron() + ((1 + city.getForgeryLevel()/20 ) * addIron);;

            userRepository.updateResources(user.getUsername(), newFood, newWood, newStone, newIron);
        }

    }


}
