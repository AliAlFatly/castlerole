package com.example.Castlerole.service;

import com.example.Castlerole.config.JwtTokenUtil;
import com.example.Castlerole.model.Node;
import com.example.Castlerole.model.User;
import com.example.Castlerole.model.helpertypes.Vector;
import com.example.Castlerole.model.response.GridResponse;
import com.example.Castlerole.model.response.PointDataResponse;
import com.example.Castlerole.model.response.UserDataResponse;
import com.example.Castlerole.repository.NodeRepository;
import com.example.Castlerole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PointService {
    @Value("${screenGridSize}")
    private int screenGridSize;

    @Value("${gridSize}")
    private int gridSize;

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private UserRepository userRepository;

    //todo: remove and replace into clientside, list of type that are attackable.
    public PointDataResponse getPointData(int x, int y){

        //TODO:add x y point validation
        PointDataResponse point = new PointDataResponse();

        point.setCoordinateX(x);
        point.setCoordinateY(y);
        point.setType("Empty");
        point.setAttackable(false);

        var user = userRepository.findByCoordinateXAndCoordinateY(x,y).orElse(null);
        if (user != null){
            point.setType("Player");
            point.setAttackable(true);
        }

        var node = nodeRepository.findByCoordinateXAndCoordinateY(x,y).orElse(null);
        if (node != null){
            point.setType(node.getType());
            point.setAttackable(true);
        }

        return point;
    }




}
