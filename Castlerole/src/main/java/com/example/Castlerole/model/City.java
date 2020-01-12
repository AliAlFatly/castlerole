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
    private int casteLevel;
    @Column
    private int woodworksLevel;
    @Column
    private int mineLevel;
    @Column
    private int forgeryLevel;
    @Column
    private int barracksLevel;
    @Column
    private int ovenLevel;

    public City(long id, long user_id, int casteLevel, int woodworksLevel, int mineLevel, int forgeryLevel, int barracksLevel, int ovenLevel){
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

    public void setCasteLevel(int casteLevel) {
        this.casteLevel = casteLevel;
    }

    //WOODWORKS
    public long getWoodworksLevel() {
        return woodworksLevel;
    }

    public void setWoodworksLevel(int woodworksLevel) {
        this.woodworksLevel = woodworksLevel;
    }

    //MINE
    public long getMineLevel() {
        return mineLevel;
    }

    public void setMineLevel(int mineLevel) {
        this.mineLevel = mineLevel;
    }

    //FORGERY
    public long getForgeryLevel() {
        return forgeryLevel;
    }

    public void setForgeryLevel(int forgeryLevel) {
        this.forgeryLevel = forgeryLevel;
    }

    //BARRACKS
    public long getBarracksLevel() {
        return barracksLevel;
    }

    public void setBarracksLevel(int barracksLevel) {
        this.barracksLevel = barracksLevel;
    }

    //OVEN
    public long getOvenLevel() {
        return ovenLevel;
    }

    public void setOvenLevel(int ovenLevel) {
        this.ovenLevel = ovenLevel;
    }
}
