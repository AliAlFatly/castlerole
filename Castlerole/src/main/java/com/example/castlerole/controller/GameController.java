package com.example.castlerole.controller;

import com.example.castlerole.model.helpertypes.Vector;
import com.example.castlerole.model.response.*;
import com.example.castlerole.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController()
@CrossOrigin()
public class GameController {

    @Autowired
    public GridService gridService;

    @Autowired
    public PointService pointService;

    @Autowired
    public UserService userService;

    @Autowired
    public CityService cityService;

    @Autowired
    public CombatService combatService;

    @GetMapping("/grid/{x}/{y}")
    public ArrayList<GridResponse> GetGrid(@PathVariable("x") int x, @PathVariable("y") int y){
        return gridService.getGrid(x, y);
    }

    @GetMapping("/pointData/{x}/{y}")
    public PointDataResponse getPointData(@PathVariable("x") int x, @PathVariable("y") int y) throws Exception {
        try{
            return pointService.getPointData(x, y);
        }
        catch (Exception err){
            throw new Exception("Something went wrong");
        }
    }

    //todo: get token from context
    @GetMapping("/userCoordinates")
    public Vector getUserCoordinates() throws Exception {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUserCoordinates(username);
    }

    @GetMapping("/userData")
    public UserDataResponse getUserData() throws Exception {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUserData(username);
    }

    @GetMapping("/recruit/{amount}")
    public String recruitTroops(@PathVariable("amount") int amount) throws Exception {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.recruit(amount, username);
    }

    @GetMapping("/update/{action}")
    public String upgradeBuilding(@PathVariable("action") String action) throws Exception {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return cityService.updateBuilding(username, action);
    }

    @GetMapping("/cityData")
    public CityDataResponse getCityData() throws Exception{
        var owner = SecurityContextHolder.getContext().getAuthentication().getName();
        return cityService.getCityData(owner);
    }

    @GetMapping("/tooltip/{action}")
    public Tooptip getToolTip(@PathVariable("action") String action) throws Exception {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return cityService.getTooltipData(username, action);
    }

    @GetMapping("/recruitmentTooltip")
    public int getRecruitmentTooltip() throws Exception {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getRecruitmentTooltip(username);
    }

    @GetMapping("/attack/{x}/{y}")
    public AttackResponse attackPoint(@PathVariable("x") int x, @PathVariable("y") int y) throws Exception {
        var attackedPoint = new Vector(x, y);
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return combatService.attack(username, attackedPoint);
    }
}
