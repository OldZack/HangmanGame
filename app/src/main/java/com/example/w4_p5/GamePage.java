package com.example.w4_p5;

import android.content.Intent;
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

public class GamePage extends AppCompatActivity {

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

        for (int row = 0; row < 4; row ++){
            TableRow currRow = new TableRow(GamePage.this);
            for (int button = 0; button < 6; button ++){
                String letter = Character.toString((char) (button+row*6+65));
                Button btn = new Button(GamePage.this);
                btn.setText(letter);
                btn.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                currRow.addView(btn);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gameState = game.round(btn.getText().charAt(0));
                        tvAnswer.setText(game.getCurrentWord());
                        switch(game.getPlayerState()){
                            case 6:
                                ivMan.setBackgroundResource(R.drawable.a);
                                break;
                            case 5:
                                ivMan.setBackgroundResource(R.drawable.b);
                                break;
                            case 4:
                                ivMan.setBackgroundResource(R.drawable.c);
                                break;
                            case 3:
                                ivMan.setBackgroundResource(R.drawable.d);
                                break;
                            case 2:
                                ivMan.setBackgroundResource(R.drawable.e);
                                break;
                            case 1:
                                ivMan.setBackgroundResource(R.drawable.f);
                                break;
                            case 0:
                                ivMan.setBackgroundResource(R.drawable.g);
                                break;
                        }


                        if (gameState == 1){
                            Intent i = new Intent(GamePage.this, MainActivity.class );
                            i.putExtra("gs", true);
                            startActivity(i);
                        }
                        else if (gameState == -1){
                            Intent i = new Intent(GamePage.this, MainActivity.class );
                            i.putExtra("gs", false);
                            startActivity(i);
                        }
                    }
                });
            }
            tlLetters.addView(currRow);
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
