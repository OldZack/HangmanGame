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

public class GamePage extends AppCompatActivity implements View.OnClickListener{

    private TableLayout tlLetters;
    private TextView tvAnswer;
    private ImageView ivMan;

    private int gameState;
    Game game = new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAnswer = (TextView) findViewById(R.id.tvAnswer);
        tvAnswer.setText(game.getCurrentWord());
        ivMan = (ImageView) findViewById(R.id.ivMan);
        tlLetters = (TableLayout) findViewById(R.id.tlLetter);

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            for (int row = 0; row < 5; row++) {
                TableRow currRow = new TableRow(GamePage.this);
                for (int button = 0; button < 6; button++) {
                    String letter = Character.toString((char) (button + row * 6 + 65));
                    Button btn = new Button(GamePage.this);
                    btn.setText(letter);
                    btn.setTag(letter);
                    btn.setLayoutParams(new TableRow.LayoutParams(180, 95));
                    currRow.addView(btn);
                    btn.setOnClickListener(this);
                }
                tlLetters.addView(currRow);
            }
            TableRow currRow = new TableRow(GamePage.this);
        }
        else{
            for (int row = 0; row < 4; row++) {
                TableRow currRow = new TableRow(GamePage.this);
                for (int button = 0; button < 8; button++) {
                    String letter = Character.toString((char) (button + row * 8 + 65));
                    Button btn = new Button(GamePage.this);
                    btn.setText(letter);
                    btn.setTag(letter);
                    btn.setTextSize(12);
                    btn.setLayoutParams(new TableRow.LayoutParams(150, 90));
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


//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        // Bundle is a key value pair
//        outState.putString("total", tvAnswer.getText().toString());
//        outState.putString("Opt1", edtOpt1.getText().toString());
//        outState.putString("Opt2", edtOpt2.getText().toString());
//
//    }

}
