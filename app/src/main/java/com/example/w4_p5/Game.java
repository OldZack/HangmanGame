package com.example.w4_p5;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

import com.example.w4_p5.Player;
import com.example.w4_p5.Words;

import java.lang.reflect.Array;
import java.util.Hashtable;
import java.util.*;

public class Game implements Parcelable {

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
                if (checkWin()){
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

    public boolean checkWin(){
        return answer.equals(currentWord.replace(" ", ""));
    }

    // click hint
    public int hintClick () {
        player.setCount(player.getCount()+1);
        return player.getCount();
    }
    // Click hint for the first time, and return the label of the word
    public String showHint () {
        return words.getHint(answer);
    }
    // click hint for the second time
    public ArrayList<Character> DisableLetter() {
        player.setTurnRemain(player.getTurnRemain()-1);
        ArrayList<Character> chars = new ArrayList<Character>();
        String givenAnswer = currentWord.replace(" ", "");
        for (int i = 0; i < answer.length(); i++){
            if (givenAnswer.charAt(i) == '_'){
                chars.add(answer.charAt(i));
            }
        }
        return chars;
    };

    // click hint for the third times, return all the vowels.
    public void showVowel() {
        player.setTurnRemain(player.getTurnRemain()-1);
        for (int i = 0;i<answer.length();i++) {
            if (answer.charAt(i) == 'A' || answer.charAt(i) == 'E' || answer.charAt(i) == 'I' || answer.charAt(i) == 'O' || answer.charAt(i) == 'U') {
                currentWord = currentWord.substring(0, i*2) + answer.charAt(i) + currentWord.substring(i*2 + 1);
            }
        }
    }
    //

    // return -1 if str is in word, else return the position of the str
    public int getPosition (int n,char ch) {
        return answer.indexOf(ch,n);
    }


    public String getCurrentWord(){
       return currentWord;
    }

    public int getPlayerState(){
        return player.getTurnRemain();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(player);
        parcel.writeValue(words);
        parcel.writeString(answer);
        parcel.writeString(currentWord);
    }

    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {
        public Game createFromParcel (Parcel in) {
            Game game = new Game();
            game.player = (Player) in.readValue(Player.class.getClassLoader());
            game.words = (Words) in.readValue(Words.class.getClassLoader());
            game.answer = in.readString();
            game.currentWord = in.readString();
            return game;
        }

        @Override
        public Game[] newArray(int i) {
            return new Game[i];
        }
    };
}
