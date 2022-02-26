package com.example.w4_p5;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Words {

    private Map<String,String> words ;
    private String[] keys ;

    public Words () {
        words = new HashMap<>();
        words.put("APPLE","FOOD");
        words.put("BANANA","FOOD");
        words.put("CAT","ANIMAL");
        words.put("DOG","ANIMAL");
        words.put("ELEPHANT","ANIMAL");
        words.put("FOX","ANIMAL");
        words.put("GRAPE","FOOD");
        keys = new String[]{"APPLE","BANANA","CAT","DOG","ELEPHANT","FOX","GRAPE"};
    }

    public Map<String, String> getWords() {
        return words;
    }

    public String generateRandomWord () {
        Random rand = new Random(20);
        int index = 0;
        index = rand.nextInt(keys.length);
        return keys[index];
    }


    public String getHint(String key) {
        return words.get(key);
    }
}
