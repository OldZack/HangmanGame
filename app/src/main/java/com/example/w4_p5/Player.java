package com.example.w4_p5;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(count);
        parcel.writeInt(turnRemain);
    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        public Player createFromParcel (Parcel in) {
            Player player = new Player();
            player.count = in.readInt();
            player.turnRemain = in.readInt();
            return player;
        }

        @Override
        public Player[] newArray(int i) {
            return new Player[i];
        }
    };
}
