package com.example.Castlerole.controller;

import com.example.Castlerole.model.request.VectorRequest;
import com.example.Castlerole.model.response.GridResponse;
import com.example.Castlerole.service.GridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController("/game")
@CrossOrigin
public class GameController {

    @Autowired
    public GridService gridService;

    @GetMapping("/grid")
    public ArrayList<GridResponse> GetGrid(@RequestBody VectorRequest vector){
        return gridService.getGrid(vector.getX(), vector.getY());
    }

}
