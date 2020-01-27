package com.example.castlerole.model.response;

public class AttackResponse {
    private int x;
    private int y;
    private boolean isWon;
    private int enemyTroopCount;
    private int attackerTroopCount;
    private int ironWon;
    private int foodWon;
    private int stoneWon;
    private int woodWon;
    private boolean attackable;

    public AttackResponse() {
    }

    public AttackResponse(int x, int y, boolean isWon, int enemyTroopCount, int attackerTroopCount, int ironWon, int foodWon, int stoneWon, int woodWon, boolean attackable) {
        this.x = x;
        this.y = y;
        this.isWon = isWon;
        this.enemyTroopCount = enemyTroopCount;
        this.attackerTroopCount = attackerTroopCount;
        this.ironWon = ironWon;
        this.foodWon = foodWon;
        this.stoneWon = stoneWon;
        this.woodWon = woodWon;
        this.attackable = attackable;
    }

    public void setAttackable(boolean attackable) {
        this.attackable = attackable;
    }

    public boolean getAttackable() {
        return attackable;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isWon() {
        return isWon;
    }

    public int getEnemyTroopCount() {
        return enemyTroopCount;
    }

    public int getAttackerTroopCount() {
        return attackerTroopCount;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWon(boolean won) {
        isWon = won;
    }

    public void setEnemyTroopCount(int enemyTroopCount) {
        this.enemyTroopCount = enemyTroopCount;
    }

    public void setAttackerTroopCount(int attackerTroopCount) {
        this.attackerTroopCount = attackerTroopCount;
    }

    public int getIronWon() {
        return ironWon;
    }

    public int getFoodWon() {
        return foodWon;
    }

    public int getStoneWon() {
        return stoneWon;
    }

    public int getWoodWon() {
        return woodWon;
    }

    public void setIronWon(int ironWon) {
        this.ironWon = ironWon;
    }

    public void setFoodWon(int foodWon) {
        this.foodWon = foodWon;
    }

    public void setStoneWon(int stoneWon) {
        this.stoneWon = stoneWon;
    }

    public void setWoodWon(int woodWon) {
        this.woodWon = woodWon;
    }
}
