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
                    //max yield between 85-95 ( less then player!)
                    r.nextInt((95 - 85) + 85),
                    //min yield
                    r.nextInt((95 - 85) + 85),
                    //yieldType
                    yieldTypes[typeIndex],
                    //x
                    initialCoordinates.getFirst(),
                    //y
                    initialCoordinates.getSecond()
            );
            nodeRepository.save(tempNode);
        }
        return "success";
    }

}
