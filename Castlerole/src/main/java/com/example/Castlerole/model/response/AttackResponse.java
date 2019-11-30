package com.example.Castlerole.model.response;

public class AttackResponse {
    private int x;
    private int y;
    private boolean isWon;
    private int enemyTroopCount;
    private int attackerTroopCount;
    private int postAttackAttackerTroopCount;
    private int troopsLost;
    private String resourceType;
    private int resourcesWon;

    public AttackResponse() {
    }

    public AttackResponse(int x, int y, boolean isWon, int enemyTroopCount, int attackerTroopCount, int postAttackAttackerTroopCount, int troopsLost, String resourceType, int resourcesWon) {
        this.x = x;
        this.y = y;
        this.isWon = isWon;
        this.enemyTroopCount = enemyTroopCount;
        this.attackerTroopCount = attackerTroopCount;
        this.postAttackAttackerTroopCount = postAttackAttackerTroopCount;
        this.troopsLost = troopsLost;
        this.resourceType = resourceType;
        this.resourcesWon = resourcesWon;
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

    public int getPostAttackAttackerTroopCount() {
        return postAttackAttackerTroopCount;
    }

    public int getTroopsLost() {
        return troopsLost;
    }

    public String getResourceType() {
        return resourceType;
    }

    public int getResourcesWon() {
        return resourcesWon;
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

    public void setPostAttackAttackerTroopCount(int postAttackAttackerTroopCount) {
        this.postAttackAttackerTroopCount = postAttackAttackerTroopCount;
    }

    public void setTroopsLost(int troopsLost) {
        this.troopsLost = troopsLost;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public void setResourcesWon(int resourcesWon) {
        this.resourcesWon = resourcesWon;
    }
}
