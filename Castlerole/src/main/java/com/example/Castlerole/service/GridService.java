package com.example.Castlerole.service;

import com.example.Castlerole.model.response.GridResponse;
import com.example.Castlerole.repository.NodeRepository;
import com.example.Castlerole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
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


    @Autowired
    private JdbcTemplate jdbcTemplate;
    public ArrayList<GridResponse> getGrid(int x, int y){
        x = setX(x);
        y = setY(y);

        ArrayList<GridResponse> response = new ArrayList<>();
        //from bottom to top:
        for (int yAxis = bottom(y); yAxis <= top(y); yAxis++){
            //from left to right:
            for (int xAxis = left(x); xAxis <= right(x); xAxis++){
                // select picture from
                String picture = "Empty";
                String userPicture = userRepository.findByCoordinateXAndCoordinateY(xAxis,yAxis).getPictureReference();
                if (userPicture != null){
                    picture = userPicture;
                }
                String nodePicture = nodeRepository.findByCoordinateXAndCoordinateY(xAxis,yAxis).getPictureReference();
                if (nodePicture != null){
                    picture = nodePicture;
                }
                GridResponse currentCoordinatesData = new GridResponse(xAxis, yAxis, picture);
                response.add(currentCoordinatesData);
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
        if(screenGridSize < 0){
            screenGridSize = 4;
        }
        if(screenGridSize % 2 != 0){
            screenGridSize += 1;
        }
        return screenGridSize/2;
    }
}
