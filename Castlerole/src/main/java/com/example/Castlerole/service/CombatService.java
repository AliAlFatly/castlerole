package com.example.Castlerole.service;

import com.example.Castlerole.model.helpertypes.Vector;
import com.example.Castlerole.model.response.AttackResponse;
import org.springframework.stereotype.Service;

@Service
public class CombatService {

    //add logic
    public AttackResponse attack(String jwtToken, Vector attackedPoint){
        AttackResponse attackResponse = new AttackResponse();

        return attackResponse;
    }
}
