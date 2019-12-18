package com.example.Castlerole.service;

import com.example.Castlerole.model.Node;
import com.example.Castlerole.model.User;
import com.example.Castlerole.model.response.GridResponse;
import com.example.Castlerole.repository.NodeRepository;
import com.example.Castlerole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

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

//    public ArrayList<GridResponse> getGrid(int x, int y){
//        x = setX(x);
//        y = setY(y);
//
//        ArrayList<GridResponse> response = new ArrayList<>();
//        //from bottom to top:
//        for (int yAxis = bottom(y); yAxis <= top(y); yAxis++){
//            //from left to right:
//            for (int xAxis = left(x); xAxis <= right(x); xAxis++){
//                // select picture from
//                String picture = "empty";
//                Optional<User> user = userRepository.findByCoordinateXAndCoordinateY(xAxis,yAxis);
//                if (!user.isEmpty()){
//                    picture = user.get().getPictureReference();
//                }
//                Optional<Node> node = nodeRepository.findByCoordinateXAndCoordinateY(xAxis,yAxis);
//                if (!node.isEmpty()){
//                    picture = node.get().getPictureReference();
//                }
//                GridResponse currentCoordinatesData = new GridResponse(xAxis, yAxis, picture);
//                response.add(currentCoordinatesData);
//            }
//        }
//        return response;
//    }

    public ArrayList<GridResponse> getGrid(int x, int y){
        x = setX(x);
        y = setY(y);

        var response = new ArrayList<GridResponse>();

        var users = userRepository.getUsersInGrid(left(x), right(x), bottom(y), top(y));
        var nodes = nodeRepository.getNodesInGrid(left(x), right(x), bottom(y), top(y));

        response.add(new GridResponse(left(x), bottom(y), "empty"));

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




    private int left(int x){
        return x - this.halfScreen();
    }

    private int right(int x){
        return x + this.halfScreen();
    }

    private int top(int y){
        return y + this.halfScreen();
    }

    private int bottom(int y){
        return y - this.halfScreen();
    }

    private int setX(int x){
        if (x + this.halfScreen() >= gridSize){
            return gridSize-this.halfScreen();
        }
        if (x - this.halfScreen() <= 0){
            return this.halfScreen();
        }
        return x;
    }

    private int setY(int y){
        if (y + this.halfScreen() >= gridSize){
            return gridSize-this.halfScreen();
        }
        if (y - this.halfScreen() <= 0){
            return this.halfScreen();
        }
        return y;
    }

    private int halfScreen(){
        if(screenGridSize < 0){
            screenGridSize = 4;
        }
        if(screenGridSize % 2 != 0){
            screenGridSize += 1;
        }
        return screenGridSize/2;
    }
}
