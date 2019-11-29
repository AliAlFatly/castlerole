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
    private String pictureName;
    @Column
    private int ownerId;
    @Column
    private int troops;
    @Column
    private int yieldMax;
    @Column
    private String yieldType;
    @Column
    private int xCoordinate;
    @Column
    private int yCoordinate;

    public Node() {
    }

    @Column
    private int yieldMin;

    public void setId(long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

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
        this.xCoordinate = xCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPictureName() {
        return pictureName;
    }

    public int getOwnerId() {
        return ownerId;
    }

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
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }




}
