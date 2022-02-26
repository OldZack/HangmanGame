package com.example.w4_p5;

public class Player {
    private int count;
    private int turnRemain;

    public Player() {
        count = 0;
        turnRemain = 6;
    }

    public int getCount() {
        return count;
    }

    public int getTurnRemain() {
        return turnRemain;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTurnRemain(int turnRemain) {
        this.turnRemain = turnRemain;
    }

    public boolean checkLose(){
        return turnRemain == 0;
    }
}
