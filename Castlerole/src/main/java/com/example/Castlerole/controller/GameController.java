package com.example.Castlerole.controller;

import com.example.Castlerole.model.helpertypes.Vector;
import com.example.Castlerole.model.response.AttackResponse;
import com.example.Castlerole.model.response.GridResponse;
import com.example.Castlerole.model.response.PointDataResponse;
import com.example.Castlerole.model.response.UserDataResponse;
import com.example.Castlerole.service.CombatService;
import com.example.Castlerole.service.GridService;
import com.example.Castlerole.service.PointService;
import com.example.Castlerole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController()
@CrossOrigin
public class GameController {

    @Autowired
    public GridService gridService;

    @Autowired
    public PointService pointService;

    @Autowired
    public UserService userService;

    @Autowired
    public CombatService combatService;

    @GetMapping("/grid")
    public ArrayList<GridResponse> GetGrid(@RequestBody Vector vector){
        return gridService.getGrid(vector.getX(), vector.getY());
    }

    @PostMapping("/addNodes")
    public String addNodes(@RequestBody int amount){
        return "";
    }

    @GetMapping("/pointData")
    public PointDataResponse getPointData(@RequestBody Vector vector) throws Exception {
        try{
            return pointService.getPointData(vector.getX(), vector.getY());
        }
        catch (Exception err){
            throw new Exception("Something went wrong");
        }
    }

    @GetMapping("/userCoordinates")
    public Vector getUserCoordinates(String jwtToken) throws Exception {
        return userService.getUserCoordinates(jwtToken);
    }

    @GetMapping("userData")
    public UserDataResponse getUserData(String jwtToken) throws Exception {
        return userService.getUserData(jwtToken);
    }

    @PutMapping("Attack")
    public AttackResponse attackPoint(String jwtToken, Vector attackedPoint){
        return combatService.attack(jwtToken, attackedPoint);
    }
}
