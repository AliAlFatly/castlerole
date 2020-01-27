package com.example.castlerole.service;

import com.example.castlerole.model.response.PointDataResponse;
import com.example.castlerole.repository.NodeRepository;
import com.example.castlerole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    public PointDataResponse getPointData(int x, int y){
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
