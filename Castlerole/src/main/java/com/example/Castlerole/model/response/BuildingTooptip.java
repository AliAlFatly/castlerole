package com.example.Castlerole.model.response;

public class BuildingTooptip {
    private int food;
    private int wood;
    private int stone;
    private int iron;
    private boolean upgradeable;

    public BuildingTooptip() {
    }

    public BuildingTooptip(int food, int wood, int stone, int iron, boolean upgradeable) {
        this.wood = wood;
        this.food = food;
        this.stone = stone;
        this.iron = iron;
        this.upgradeable = upgradeable;
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

    public boolean isUpgradeable() {
        return upgradeable;
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

    public void setUpgradeable(boolean upgradeable) {
        this.upgradeable = upgradeable;
    }
}
