package com.example.castlerole.model.response;

public class CityDataResponse {
    private String owner;
    private int castle;
    private int woodwork;
    private int mine;
    private int forgery;
    private int barrack;
    private int oven;

    public CityDataResponse() {

    }

    public CityDataResponse(String owner, int castle, int woodwork, int mine, int forgery, int barrack, int oven){
        this.owner = owner;
        this.castle = castle;
        this.woodwork = woodwork;
        this.mine = mine;
        this.forgery = forgery;
        this.barrack = barrack;
        this.oven = oven;
    }

    //OWNER
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    //CASTLE
    public int getCastle() {
        return castle;
    }

    public void setCastle(int castle) {
        this.castle = castle;
    }

    //WOODWORKS
    public int getWoodwork() {
        return woodwork;
    }

    public void setWoodwork(int woodwork) {
        this.woodwork = woodwork;
    }

    //MINE
    public int getMine() {
        return mine;
    }

    public void setMine(int mine) {
        this.mine = mine;
    }

    //FORGERY
    public int getForgery() {
        return forgery;
    }

    public void setForgery(int forgery) {
        this.forgery = forgery;
    }

    //BARRACKS
    public int getBarrack() {
        return barrack;
    }

    public void setBarrack(int barrack) {
        this.barrack = barrack;
    }

    //OVEN
    public int getOven() {
        return oven;
    }

    public void setOven(int oven) {
        this.oven = oven;
    }
}
