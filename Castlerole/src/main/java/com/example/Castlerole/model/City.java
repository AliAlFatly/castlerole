package com.example.Castlerole.model;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private int castleLevel;
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


    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public City(int castleLevel, int woodworksLevel, int mineLevel, int forgeryLevel, int barracksLevel, int ovenLevel, User user){
        this.castleLevel = castleLevel;
        this.woodworksLevel = woodworksLevel;
        this.mineLevel = mineLevel;
        this.forgeryLevel = forgeryLevel;
        this.barracksLevel = barracksLevel;
        this.ovenLevel = ovenLevel;
        this.user = user;
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

    //CASTLE
    public int getCastleLevel() {
        return castleLevel;
    }

    public void setCasteLevel(int castleLevel) {
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

    //USER
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
