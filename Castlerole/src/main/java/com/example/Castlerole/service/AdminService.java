package com.example.Castlerole.service;


import com.example.Castlerole.model.Node;
import com.example.Castlerole.model.helpertypes.IntVector;
import com.example.Castlerole.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class AdminService {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private JwtUserService userService;

    public String generateNodes(int amount) throws Exception {
        amount = Math.max(0, amount);
        Random r = new Random();
        String[] types = {"forest", "lake", "mountain", "mine"};
        String[] yieldTypes = {"wood", "food", "stone", "iron"};
        for (int i = 0; i < amount; i++){
            IntVector initialCoordinates = userService.getXY();
            //generate 0,1,2,3 as index for types
            int typeIndex = r.nextInt(4);
            Node tempNode = new Node(
                    //type
                    types[typeIndex],
                    //picture
                    types[typeIndex],
                    //ownerId not applicable
                    //null,
                    //troops, between 250-350
                    r.nextInt((350 - 250) + 250),
                    //max yield between 65-95 ( less then player!)
                    //generate between 0 and (95-65) => 0 - 30 and add 65 to it => 0+65 => 65 min, 30 + 65 => 95 max
                    (r.nextInt((95 - 65)) + 65),
                    //min yield 45-64
                    //generate between 0 and (64-45) => 0 - 19 and add 45 to it => 0+45 => 45 min, 19 + 45 => 64 max
                    (r.nextInt((64 - 45)) + 45),
                    //yieldType
                    yieldTypes[typeIndex],
                    //x
                    initialCoordinates.getFirst(),
                    //y
                    initialCoordinates.getSecond()
            );
            nodeRepository.save(tempNode);
        }
        return "done";
    }

}
