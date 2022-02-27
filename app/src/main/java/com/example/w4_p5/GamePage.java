package com.example.w4_p5;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GamePage extends AppCompatActivity implements View.OnClickListener{

    private TableLayout tlLetters;
    private TextView tvAnswer;
    private ImageView ivMan;
    Button[] buttons;

    private int gameState;
    private int hintCount;
    Game game = new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAnswer = (TextView) findViewById(R.id.tvAnswer);
        tvAnswer.setText(game.getCurrentWord());
        ivMan = (ImageView) findViewById(R.id.ivMan);
        tlLetters = (TableLayout) findViewById(R.id.tlLetter);
        buttons = new Button[26];

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            for (int row = 0; row < 5; row++) {
                TableRow currRow = new TableRow(GamePage.this);
                for (int button = 0; button < 6; button++) {
                    if (row == 4 && button == 2){
                        break;
                    }
                    String letter = Character.toString((char) (button + row * 6 + 65));
                    Button btn = new Button(GamePage.this);
                    btn.setText(letter);
                    btn.setTag(letter);
                    btn.setLayoutParams(new TableRow.LayoutParams(180, 95));
                    buttons[button + row*6] = btn;
                    currRow.addView(btn);
                    btn.setOnClickListener(this);
                }
                tlLetters.addView(currRow);
            }
            TableRow currRow = new TableRow(GamePage.this);
        }
        else{
            Button btnHint = (Button) findViewById(R.id.btnHint);
            TextView tvHint = (TextView) findViewById(R.id.tvHint);
            btnHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hintCount = game.hintClick();
                    switch(hintCount){
                        case 1:
                            tvHint.setText(game.showHint());
                            break;
                        case 2:
                            ArrayList<Character> chars = game.DisableLetter();
                            updateImage();
                            int c = 0;
                            int max_count = (26-chars.size())/2;
                            for (int i = 0; i < buttons.length; i++){
                                if (!chars.contains(buttons[i].getTag().toString().charAt(0))){
                                    buttons[i].setEnabled(false);
                                    buttons[i].setText("");
                                    c ++;
                                }
                                if (c == max_count){
                                    break;
                                }
                            }
                            break;
                        case 3:
                            game.showVowel();
                            for (int i = 0; i < buttons.length; i++){
                                Character tag = buttons[i].getTag().toString().charAt(0);
                                if (tag == 'A' || tag == 'E' || tag == 'I' || tag == 'O' || tag == 'U'){
                                    buttons[i].setEnabled(false);
                                    buttons[i].setText("");
                                }
                            }
                            tvAnswer.setText(game.getCurrentWord());
                            if (game.checkWin()){
                                Intent i = new Intent(GamePage.this, MainActivity.class);
                                i.putExtra("gs", true);
                                startActivity(i);
                            }
                            break;
                    }
                }
            });
            for (int row = 0; row < 4; row++) {
                TableRow currRow = new TableRow(GamePage.this);
                for (int button = 0; button < 8; button++) {
                    if (row == 3 && button == 2){
                        break;
                    }
                    String letter = Character.toString((char) (button + row * 8 + 65));
                    Button btn = new Button(GamePage.this);
                    btn.setText(letter);
                    btn.setTag(letter);
                    btn.setTextSize(12);
                    btn.setLayoutParams(new TableRow.LayoutParams(140, 90));
                    buttons[button + row*8] = btn;
                    currRow.addView(btn);
                    btn.setOnClickListener(this);
                }
                tlLetters.addView(currRow);
            }
        }
    }

    public void onClick(View v) {
        gameState = game.round(v.getTag().toString().charAt(0));
        tvAnswer.setText(game.getCurrentWord());
        updateImage();

        if (gameState == 1) {
            Intent i = new Intent(GamePage.this, MainActivity.class);
            i.putExtra("gs", true);
            startActivity(i);
        } else if (gameState == -1) {
            Intent i = new Intent(GamePage.this, MainActivity.class);
            i.putExtra("gs", false);
            startActivity(i);
        }
    }

    public void updateImage(){
        switch (game.getPlayerState()) {
            case 6:
                ivMan.setImageResource(R.drawable.a);
                break;
            case 5:
                ivMan.setImageResource(R.drawable.b);
                break;
            case 4:
                ivMan.setImageResource(R.drawable.c);
                break;
            case 3:
                ivMan.setImageResource(R.drawable.d);
                break;
            case 2:
                ivMan.setImageResource(R.drawable.e);
                break;
            case 1:
                ivMan.setImageResource(R.drawable.f);
                break;
            case 0:
                ivMan.setImageResource(R.drawable.g);
                break;
        }
    }


//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        // Bundle is a key value pair
//        outState.putString("total", tvAnswer.getText().toString());
//        outState.putString("Opt1", edtOpt1.getText().toString());
//        outState.putString("Opt2", edtOpt2.getText().toString());
//
//    }

}
