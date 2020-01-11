package com.example.Castlerole.model;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //TODO: LINK THIS TO ID FROM MODEL USER
    @Column
    private long user_id;
    @Column
    private long casteLevel;
    @Column
    private long woodworksLevel;
    @Column
    private long mineLevel;
    @Column
    private long forgeryLevel;
    @Column
    private long barracksLevel;
    @Column
    private long ovenLevel;

    public City(long id, long user_id, long casteLevel, long woodworksLevel, long mineLevel, long forgeryLevel, long barracksLevel, long ovenLevel){
        this.id = id;
        this.user_id = user_id;
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

    //USER_ID
    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    //CASTLE
    public long getCasteLevel() {
        return casteLevel;
    }

    public void setCasteLevel(long casteLevel) {
        this.casteLevel = casteLevel;
    }

    //WOODWORKS
    public long getWoodworksLevel() {
        return woodworksLevel;
    }

    public void setWoodworksLevel(long woodworksLevel) {
        this.woodworksLevel = woodworksLevel;
    }

    //MINE
    public long getMineLevel() {
        return mineLevel;
    }

    public void setMineLevel(long mineLevel) {
        this.mineLevel = mineLevel;
    }

    //FORGERY
    public long getForgeryLevel() {
        return forgeryLevel;
    }

    public void setForgeryLevel(long forgeryLevel) {
        this.forgeryLevel = forgeryLevel;
    }

    //BARRACKS
    public long getBarracksLevel() {
        return barracksLevel;
    }

    public void setBarracksLevel(long barracksLevel) {
        this.barracksLevel = barracksLevel;
    }

    //OVEN
    public long getOvenLevel() {
        return ovenLevel;
    }

    public void setOvenLevel(long ovenLevel) {
        this.ovenLevel = ovenLevel;
    }
}
