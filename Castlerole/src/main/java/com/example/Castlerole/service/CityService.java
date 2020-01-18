package com.example.Castlerole.service;

import com.example.Castlerole.config.JwtTokenUtil;
import com.example.Castlerole.repository.CityRepository;
import com.example.Castlerole.model.response.CityDataResponse;
import com.example.Castlerole.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public CityDataResponse getCityData(String owner) throws Exception{
        City city;
        try{
            city = cityRepository.findByOwner(owner);
        }
        catch(Exception error){
            throw new Exception("Cannot find a corresponding city!");
        }

        return new CityDataResponse(
                city.getOwner(),
                city.getCasteLevel(),
                city.getWoodworksLevel(),
                city.getMineLevel(),
                city.getForgeryLevel(),
                city.getBarracksLevel(),
                city.getOvenLevel()
        );
    }
}
