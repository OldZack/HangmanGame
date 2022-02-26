package com.example.w4_p5;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

import com.example.w4_p5.Player;
import com.example.w4_p5.Words;

import java.lang.reflect.Array;
import java.util.Hashtable;
import java.util.*;

public class Game {

    private Player player;
    private Words words;
    private String answer;
    private String currentWord;

    public Game() {
        words = new Words();
        player = new Player();
        answer = words.generateRandomWord();
        currentWord = new String();
        for (int i = 0; i < answer.length(); i++){
            currentWord += "_ ";
        }

    }

    //
    public int round(Character n){
        for (int i = 0; i < answer.length(); i++){
            if (answer.charAt(i) == n && currentWord.charAt(i*2) == '_') {
                currentWord = currentWord.substring(0, i*2) + n + currentWord.substring(i*2 + 1);
                if (answer.equals(currentWord.replace(" ", ""))){
                    return 1;
                }
                else{
                    return 0;
                }
            }
        }
        player.setTurnRemain(player.getTurnRemain()-1);
        if (player.checkLose()){
            return -1;
        }
        else{
            return 0;
        }
    }

    // click hint
    public void hintClick () {
        if (player.getCount() == 0 && player.getTurnRemain() > 0) {
            player.setCount(player.getCount()+1);
            player.setTurnRemain(player.getTurnRemain()-1);
            hintClickFirstTime(answer);
        }
        if (player.getCount() == 1 && player.getTurnRemain() > 0) {
            player.setCount(player.getCount()+1);
            player.setTurnRemain(player.getTurnRemain()-1);
            hintClickSecondTime();
        }
        if (player.getCount() == 2 && player.getTurnRemain() > 0) {
            player.setCount(player.getCount()+1);
            player.setTurnRemain(player.getTurnRemain()-1);
            hintClickThirdTime();
        }
    }
    // Click hint for the first time, and return the label of the word
    public String hintClickFirstTime (String str) {
        return words.getHint(answer);
    }
    // click hint for the second time
    public void hintClickSecondTime() {};

    // click hint for the third times, return all the vowels.
    public int[] hintClickThirdTime() {
        int[] index = new int[0];
        for (int i = 0;i<answer.length();i++) {
            if (answer.charAt(i) == 'A' || answer.charAt(i) == 'E' || answer.charAt(i) == 'I' || answer.charAt(i) == 'O' || answer.charAt(i) == 'U') {
                index = Arrays.copyOf(index,index.length+1);
                index[index.length-1] = i;
            }
        }
        return index;
    }
    //

    // return -1 if str is in word, else return the position of the str
    public int getPosition (int n,char ch) {
        return answer.indexOf(ch,n);
    }

    // restart the game
    public void reStart() {

    }

    public String getCurrentWord(){
       return currentWord;
    }

    public int getPlayerState(){
        return player.getTurnRemain();
    }

}
