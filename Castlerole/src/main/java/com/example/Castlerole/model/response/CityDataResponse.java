package com.example.Castlerole.model.response;

public class CityDataResponse {
    private String owner;
    private int castleLevel;
    private int woodworksLevel;
    private int mineLevel;
    private int forgeryLevel;
    private int barracksLevel;
    private int ovenLevel;

    public CityDataResponse(String owner, int castleLevel, int woodworksLevel, int mineLevel, int forgeryLevel, int barracksLevel, int ovenLevel){
        this.owner = owner;
        this.castleLevel = castleLevel;
        this.woodworksLevel = woodworksLevel;
        this.mineLevel = mineLevel;
        this.forgeryLevel = forgeryLevel;
        this.barracksLevel = barracksLevel;
        this.ovenLevel = ovenLevel;
    }

    //OWNER
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    //CASTLE
    public int getCastleLevel() {
        return castleLevel;
    }

    public void setCastleLevel(int castleLevel) {
        this.castleLevel = castleLevel;
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
