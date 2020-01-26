package com.example.castlerole.model.response;

public class UserDataResponse {
    private String username;
    private int coordinateX;
    private int coordinateY;
    private int wood;
    private int iron;
    private int stone;
    private int food;
    private int troops;

    public UserDataResponse() {
    }

    public UserDataResponse(String username, int coordinateX, int coordinateY, int wood, int iron, int stone, int food, int troops) {
        this.username = username;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.wood = wood;
        this.iron = iron;
        this.stone = stone;
        this.food = food;
        this.troops = troops;
    }

    public String getUsername() {
        return username;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public int getWood() {
        return wood;
    }

    public int getIron() {
        return iron;
    }

    public int getStone() {
        return stone;
    }

    public int getFood() {
        return food;
    }

    public int getTroops() {
        return troops;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public void setStone(int stone) {
        this.stone = stone;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setTroops(int troops) {
        this.troops = troops;
    }
}
