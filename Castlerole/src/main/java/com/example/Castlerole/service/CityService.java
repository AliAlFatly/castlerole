package com.example.Castlerole.service;

import com.example.Castlerole.repository.CityRepository;
import com.example.Castlerole.model.response.CityDataResponse;
import com.example.Castlerole.model.City;
import org.springframework.beans.factory.annotation.Autowired;

public class CityService {
    @Autowired
    private CityRepository cityRepository;

    //Checks if the city exists using the user_id
    public boolean CityExists(long user_id) throws Exception{
        City city = cityRepository.findByUserId(user_id);
        if(city != null){
            return true;
        }
        else{
            return false;
        }
    }

    public CityDataResponse getCityData(long user_id) throws Exception{
        City city;
        try{
            city = cityRepository.findByUserId(user_id);
        }
        catch(Exception error){
            throw new Exception("Cannot find a corresponding city!");
        }

        return new CityDataResponse(
                city.getId(),
                city.getUser_id(),
                city.getCasteLevel(),
                city.getWoodworksLevel(),
                city.getMineLevel(),
                city.getForgeryLevel(),
                city.getBarracksLevel(),
                city.getOvenLevel()
        );
    }
}
