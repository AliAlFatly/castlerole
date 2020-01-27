package com.example.castlerole.service;

import com.example.castlerole.config.JwtTokenUtil;
import com.example.castlerole.model.helpertypes.Vector;
import com.example.castlerole.model.response.AttackResponse;
import com.example.castlerole.repository.NodeRepository;
import com.example.castlerole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Service
public class CombatService {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private Random r = SecureRandom.getInstanceStrong();

    public CombatService() throws NoSuchAlgorithmException {
        // This method is empty because a constructor with throws NoSuchAlgorithmException is needed in order to use SecureRandom.getInstanceStrong().
    }

    //add logic against attacking a point outside of grid
    //add superclass for node/user later to lesser if statements
    //kan nog niet handmatig getest worden
    public AttackResponse attack(String username, Vector attackedPoint){
        //note if the client sends an unauthorized token he shouldn't be able to call this service (webSecurityConfig should block) so no need to set findUser as optional.orElse(null)
        var attacker = userRepository.findByUsername(username);
        var targetIsUser = userRepository.findByCoordinateXAndCoordinateY(attackedPoint.getX(), attackedPoint.getY()).orElse(null);
        var targetIsNode = nodeRepository.findByCoordinateXAndCoordinateY(attackedPoint.getX(), attackedPoint.getY()).orElse(null);
        var attackerCityData = cityService.getCityData(username);

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
        if (targetIsUser != null){
            attackResponse.setEnemyTroopCount(targetIsUser.getTroops());
            var targetCityData = cityService.getCityData(targetIsUser.getUsername());
            var attackIsWon = (attacker.getTroops() +
                    (attacker.getTroops() * attackerCityData.getCastle()/100) +
                    (attacker.getTroops() * attackerCityData.getBarrack()/100)) >
                    (targetIsUser.getTroops() +
                        (targetIsUser.getTroops() * targetCityData.getCastle()/100) +
                            (targetIsUser.getTroops() * targetCityData.getBarrack()/100));

            if(attackIsWon){
                attackResponse.setAttackable(true);
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
        }
        //if attacking node and if won
        if (targetIsNode != null && attacker.getTroops() > targetIsNode.getTroops()){
            attackResponse.setAttackable(true);
            attackResponse.setWon(true);

            //check what type of node (forest/mine/lake/mountain), not if yieldType was asked then wood,iron,food,stone instead of forest, mine, lake, mountain
            switch (targetIsNode.getType()){
                case "forest":
                    //get random number between min and max yield.
                    woodWon = r.nextInt((targetIsNode.getYieldMax() - targetIsNode.getYieldMin()) + targetIsNode.getYieldMin());
                    break;
                case "mine":
                    ironWon = r.nextInt((targetIsNode.getYieldMax() - targetIsNode.getYieldMin()) + targetIsNode.getYieldMin());
                    break;
                case "lake":
                    foodWon = r.nextInt((targetIsNode.getYieldMax() - targetIsNode.getYieldMin()) + targetIsNode.getYieldMin());
                    break;
                case "mountain":
                    stoneWon = r.nextInt((targetIsNode.getYieldMax() - targetIsNode.getYieldMin()) + targetIsNode.getYieldMin());
                    break;
                default:
                    foodWon = r.nextInt((targetIsNode.getYieldMax() - targetIsNode.getYieldMin()) + targetIsNode.getYieldMin());
                    break;
            }
        }

        if (targetIsNode != null){
            attackResponse.setEnemyTroopCount(targetIsNode.getTroops());
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
