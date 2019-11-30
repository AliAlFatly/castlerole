package com.example.Castlerole.service;

import com.example.Castlerole.config.JwtTokenUtil;
import com.example.Castlerole.model.helpertypes.Vector;
import com.example.Castlerole.model.response.AttackResponse;
import com.example.Castlerole.repository.NodeRepository;
import com.example.Castlerole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CombatService {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    //add logic against attacking a point outside of grid
    //add superclass for node/user later to lesser if statements
    //kan nog niet handmatig getest worden
    public AttackResponse attack(String jwtToken, Vector attackedPoint){
        //note if the client sends an unauthorized token he shouldn't be able to call this service (webSecurityConfig should block) so no need to set findUser as optional.orElse(null)
        var attacker = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));
        var targetIsUser = userRepository.findByCoordinateXAndCoordinateY(attackedPoint.getX(), attackedPoint.getY()).orElse(null);
        var targetIsNode = nodeRepository.findByCoordinateXAndCoordinateY(attackedPoint.getX(), attackedPoint.getY()).orElse(null);
        Random r = new Random();
        //initiate all values with 0, if user won then the value gets set to a correct amount inside the if statement.
        int foodWon = 0;
        int ironWon = 0;
        int woodWon = 0;
        int stoneWon = 0;

        //init empty attack response
        AttackResponse attackResponse = new AttackResponse();

        //set x y
        attackResponse.setX(attackedPoint.getX());
        attackResponse.setY(attackedPoint.getY());
        //!set attackable false, if target is attacked set to true, this is done to remove another ifstatement (if target is not node or user)
        attackResponse.setAttackable(false);
        //set user troop count
        attackResponse.setAttackerTroopCount(attacker.getTroops());
        //set is won false, if user won, it get set to true inside if statement, this is done so to remove else statement and nested if statement.
        attackResponse.setWon(false);

        //if attacking user, and if won
        if (targetIsUser != null && attacker.getTroops() > targetIsUser.getTroops()){
            attackResponse.setAttackable(true);
            attackResponse.setEnemyTroopCount(targetIsUser.getTroops());
            attackResponse.setWon(true);

            //if enemy user have lower food then 100, get the amount he have, else get 100 (so if enemy have 23 < 100 => get 23, if enemy have 110 > 100 => get 100 remain 10).
            foodWon = Math.min(targetIsUser.getFood(), 100);
            userRepository.updateFood(targetIsUser.getFood() - foodWon, targetIsUser.getUsername());

            ironWon = Math.min(targetIsUser.getIron(), 100);
            userRepository.updateIron(targetIsUser.getIron() - ironWon, targetIsUser.getUsername());

            stoneWon = Math.min(targetIsUser.getStone(), 100);
            userRepository.updateStone(targetIsUser.getStone() - stoneWon, targetIsUser.getUsername());

            woodWon = Math.min(targetIsUser.getWood(), 100);
            userRepository.updateWood(targetIsUser.getWood() - woodWon, targetIsUser.getUsername());

        }
        //if attacking node and if won
        if (targetIsNode != null && attacker.getTroops() > targetIsNode.getTroops()){
            attackResponse.setAttackable(true);
            attackResponse.setEnemyTroopCount(targetIsNode.getTroops());
            attackResponse.setWon(true);

            //check what type of node (forest/mine/lake/mountain)
            switch (targetIsNode.getType()){
                case "wood":
                    //get random number between min and max yield.
                    woodWon = r.nextInt((targetIsNode.getYieldMax() - targetIsNode.getYieldMin()) + targetIsNode.getYieldMin());
                    break;
                case "iron":
                    ironWon = r.nextInt((targetIsNode.getYieldMax() - targetIsNode.getYieldMin()) + targetIsNode.getYieldMin());
                    break;
                case "food":
                    foodWon = r.nextInt((targetIsNode.getYieldMax() - targetIsNode.getYieldMin()) + targetIsNode.getYieldMin());
                    break;
                case "stone":
                    stoneWon = r.nextInt((targetIsNode.getYieldMax() - targetIsNode.getYieldMin()) + targetIsNode.getYieldMin());
                    break;
            }
        }

        //after knowing how much is won, update attacker with values
        userRepository.updateFood(attacker.getFood() + foodWon, attacker.getUsername());
        userRepository.updateIron(attacker.getIron() + ironWon, attacker.getUsername());
        userRepository.updateWood(attacker.getWood() + woodWon, attacker.getUsername());
        userRepository.updateStone(attacker.getStone() + stoneWon, attacker.getUsername());

        //set the amount won in response, note if attacking for example a mountain, then stone is X and other resource are 0 since mountain only reward stones
        attackResponse.setFoodWon(foodWon);
        attackResponse.setIronWon(ironWon);
        attackResponse.setWoodWon(woodWon);
        attackResponse.setStoneWon(stoneWon);

        //return response
        return attackResponse;
    }
}
