package com.example.castlerole.model.response;

public class Tooptip {
    private int food;
    private int wood;
    private int stone;
    private int iron;

    public Tooptip() {
    }

    public Tooptip(int food, int wood, int stone, int iron) {
        this.wood = wood;
        this.food = food;
        this.stone = stone;
        this.iron = iron;
    }

    public int getWood() {
        return wood;
    }

    public int getFood() {
        return food;
    }

    public int getStone() {
        return stone;
    }

    public int getIron() {
        return iron;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setStone(int stone) {
        this.stone = stone;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

}
