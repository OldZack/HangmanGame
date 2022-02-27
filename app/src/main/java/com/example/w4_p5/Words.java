package com.example.w4_p5;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Words implements Parcelable {

    private Map<String,String> words ;
    private String[] keys ;

    public Words () {
        words = new HashMap<>();
        words.put("APPLE","FOOD");
        words.put("BANANA","FOOD");
        words.put("CAT","ANIMAL");
        words.put("DOG","ANIMAL");
        words.put("TIGER","ANIMAL");
        words.put("FOX","ANIMAL");
        words.put("GRAPE","FOOD");
        keys = new String[]{"APPLE","BANANA","CAT","DOG","TIGER","FOX","GRAPE"};
    }

    public Map<String, String> getWords() {
        return words;
    }

    public String generateRandomWord () {
        Random rand = new Random();
        int index = 0;
        index = rand.nextInt(keys.length);
        return keys[index];
    }


    public String getHint(String key) {
        return words.get(key);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeMap(words);
        parcel.writeStringArray(keys);
    }
    public static final Parcelable.Creator<Words> CREATOR = new Parcelable.Creator<Words>() {
        public Words createFromParcel (Parcel in) {
            Words words = new Words();
            in.readMap(words.words,Map.class.getClassLoader());
            in.readStringArray(words.keys);
            return words;
        }

        @Override
        public Words[] newArray(int i) {
            return new Words[i];
        }
    };
}
