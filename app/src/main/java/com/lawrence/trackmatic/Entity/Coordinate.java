package com.lawrence.trackmatic.Entity;


import com.lawrence.trackmatic.constant.Constant;

public class Coordinate {

    private int x;
    private int y;

    public Coordinate(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {

        if (x < 0)
            throw new IllegalArgumentException("X cannot be less than zero!");

        if (x > Constant.RIGHT_MAX)
            throw new IllegalArgumentException("X cannot be greater than " + Constant.RIGHT_MAX + "!");

        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {

        if (y < 0)
            throw new IllegalArgumentException("Y cannot be less than zero!");

        if (y > Constant.UPPER_MAX)
            throw new IllegalArgumentException("Y cannot be greater than " + Constant.UPPER_MAX + "!");

        this.y = y;
    }
}
