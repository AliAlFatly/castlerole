package com.example.Castlerole.model.response;

public class CityDataResponse {
    private long id;
    private String owner;
    private int casteLevel;
    private int woodworksLevel;
    private int mineLevel;
    private int forgeryLevel;
    private int barracksLevel;
    private int ovenLevel;

    public CityDataResponse(String owner, int casteLevel, int woodworksLevel, int mineLevel, int forgeryLevel, int barracksLevel, int ovenLevel){
        this.owner = owner;
        this.casteLevel = casteLevel;
        this.woodworksLevel = woodworksLevel;
        this.mineLevel = mineLevel;
        this.forgeryLevel = forgeryLevel;
        this.barracksLevel = barracksLevel;
        this.ovenLevel = ovenLevel;
    }


    //GETTERS AND SETTERS
    //ID
    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    //OWNER
    public String geOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    //CASTLE
    public int getCasteLevel() {
        return casteLevel;
    }

    public void setCasteLevel(int casteLevel) {
        this.casteLevel = casteLevel;
    }

    //WOODWORKS
    public int getWoodworksLevel() {
        return woodworksLevel;
    }

    public void setWoodworksLevel(int woodworksLevel) {
        this.woodworksLevel = woodworksLevel;
    }

    //MINE
    public int getMineLevel() {
        return mineLevel;
    }

    public void setMineLevel(int mineLevel) {
        this.mineLevel = mineLevel;
    }

    //FORGERY
    public int getForgeryLevel() {
        return forgeryLevel;
    }

    public void setForgeryLevel(int forgeryLevel) {
        this.forgeryLevel = forgeryLevel;
    }

    //BARRACKS
    public int getBarracksLevel() {
        return barracksLevel;
    }

    public void setBarracksLevel(int barracksLevel) {
        this.barracksLevel = barracksLevel;
    }

    //OVEN
    public int getOvenLevel() {
        return ovenLevel;
    }

    public void setOvenLevel(int ovenLevel) {
        this.ovenLevel = ovenLevel;
    }
}
