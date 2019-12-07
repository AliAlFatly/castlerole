package com.example.Castlerole.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "node")
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String type;
    @Column
    private String pictureReference;
//    @Column
//    private int ownerId;
    @Column
    private int troops;
    @Column
    private int yieldMax;
    @Column
    private String yieldType;
    @Column
    private int coordinateX;
    @Column
    private int coordinateY;

    public Node() {
    }

    public Node(String type, String pictureReference, int troops, int yieldMax, int yieldMin, String yieldType, int coordinateX, int coordinateY) {
        this.type = type;
        this.pictureReference = pictureReference;
        //this.ownerId = ownerId;
        this.troops = troops;
        this.yieldMax = yieldMax;
        this.yieldType = yieldType;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.yieldMin = yieldMin;
    }

    @Column
    private int yieldMin;

    public void setId(long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPictureReference(String pictureName) {
        this.pictureReference = pictureName;
    }

//    public void setOwnerId(int ownerId) {
//         this.ownerId = ownerId;
//    }

    public void setTroops(int troops) {
        this.troops = troops;
    }

    public void setYieldMin(int yieldMin) {
        this.yieldMin = yieldMin;
    }

    public void setYieldMax(int yieldMax) {
        this.yieldMax = yieldMax;
    }

    public void setYieldType(String yieldType) {
        this.yieldType = yieldType;
    }

    public void setXCoordinate(int xCoordinate) {
        this.coordinateX = xCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.coordinateY = yCoordinate;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPictureReference() {
        return pictureReference;
    }

//    public int getOwnerId() {
//        return ownerId;
//    }

    public int getTroops() {
        return troops;
    }

    public int getYieldMin() {
        return yieldMin;
    }

    public int getYieldMax() {
        return yieldMax;
    }

    public String getYieldType() {
        return yieldType;
    }

    public int getxCoordinate() {
        return coordinateX;
    }

    public int getyCoordinate() {
        return coordinateY;
    }




}
