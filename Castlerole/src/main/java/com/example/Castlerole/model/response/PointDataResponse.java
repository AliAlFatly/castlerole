package com.example.Castlerole.model.response;

public class PointDataResponse {
    private int coordinateX;
    private int coordinateY;
    private String type;

    private boolean isAttackable;

    public PointDataResponse() {
    }

    public PointDataResponse(int coordinateX, int coordinateY, String type, boolean attackable) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.type = type;
        this.isAttackable = attackable;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public String getType() {
        return type;
    }

    public boolean isAttackable() {
        return isAttackable;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAttackable(boolean attackable) {
        this.isAttackable = attackable;
    }
}

