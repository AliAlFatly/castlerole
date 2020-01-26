package com.example.castlerole.service;

import com.example.castlerole.model.response.GridResponse;
import com.example.castlerole.repository.NodeRepository;
import com.example.castlerole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GridService {
    @Value("${screenGridSize}")
    private int screenGridSize;

    @Value("${gridSize}")
    private int gridSize;

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private UserRepository userRepository;

    public ArrayList<GridResponse> getGrid(int x, int y){
        x = setX(x);
        y = setY(y);

        var response = new ArrayList<GridResponse>();

        var users = userRepository.getUsersInGrid(left(x), right(x), bottom(y), top(y));
        var nodes = nodeRepository.getNodesInGrid(left(x), right(x), bottom(y), top(y));

        if(!users.isEmpty()){
            for (var user : users.get()) {
                response.add(new GridResponse(user.getxCoordinate(), user.getyCoordinate(), user.getPictureReference()));
            }
        }
        if(!nodes.isEmpty()){
            for (var node : nodes.get()) {
                response.add(new GridResponse(node.getxCoordinate(), node.getyCoordinate(), node.getPictureReference()));
            }
        }

        return response;
    }




    public int left(int x){
        return x - this.halfScreen();
    }

    public int right(int x){
        return x + this.halfScreen();
    }

    public int top(int y){
        return y + this.halfScreen();
    }

    public int bottom(int y){
        return y - this.halfScreen();
    }

    public int setX(int x){
        if (x + this.halfScreen() >= gridSize){
            return gridSize-this.halfScreen();
        }
        if (x - this.halfScreen() <= 0){
            return this.halfScreen();
        }
        return x;
    }

    public int setY(int y){
        if (y + this.halfScreen() >= gridSize){
            return gridSize-this.halfScreen();
        }
        if (y - this.halfScreen() <= 0){
            return this.halfScreen();
        }
        return y;
    }

    public int halfScreen(){
//        if(screenGridSize < 0){
//            screenGridSize = this.screenGridSize;
//        }
        if(screenGridSize % 2 != 0){
            screenGridSize += 1;
        }
        return screenGridSize/2;
    }
}
