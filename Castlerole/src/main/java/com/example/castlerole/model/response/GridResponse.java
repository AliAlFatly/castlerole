package com.example.castlerole.model.response;

public class GridResponse {
    private int x;
    private int y;
    private String picture;

    public GridResponse(int x, int y, String picture) {
        this.x = x;
        this.y = y;
        this.picture = picture;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPicture() {
        return picture;
    }
}
