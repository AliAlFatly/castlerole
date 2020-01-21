package com.example.Castlerole.service;

import com.example.Castlerole.config.JwtTokenUtil;
import com.example.Castlerole.model.User;
import com.example.Castlerole.model.response.UserDataResponse;
import com.example.Castlerole.repository.CityRepository;
import com.example.Castlerole.model.response.CityDataResponse;
import com.example.Castlerole.model.City;
import com.example.Castlerole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public CityDataResponse getCityData(String owner) throws Exception{
        City city;
        try{
            city = cityRepository.findByUserUsername(owner);
        }
        catch(Exception error){
            throw new Exception("Cannot find a corresponding city!");
        }
        System.out.println(city.getUser().getUsername());
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



}
