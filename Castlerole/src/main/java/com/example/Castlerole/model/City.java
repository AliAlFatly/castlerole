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
    private String owner;
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


    @OneToOne(mappedBy = "city")
    private User user;

    public City(String owner, int casteLevel, int woodworksLevel, int mineLevel, int forgeryLevel, int barracksLevel, int ovenLevel){
        this.owner = owner;
        this.casteLevel = casteLevel;
        this.woodworksLevel = woodworksLevel;
        this.mineLevel = mineLevel;
        this.forgeryLevel = forgeryLevel;
        this.barracksLevel = barracksLevel;
        this.ovenLevel = ovenLevel;
    }

    public City() {
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
    public String getOwner() {
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

    //USER
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
