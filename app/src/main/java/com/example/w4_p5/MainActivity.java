package com.example.w4_p5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvMessage;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);

        tvMessage = (TextView) findViewById(R.id.tvMessage);

        Intent i = getIntent();
        if (i.hasExtra("gs")){
            if((boolean)i.getSerializableExtra("gs")){
                tvMessage.setText("YOU WIN!");
            }
            else {
                tvMessage.setText("YOU LOSE...");
            }
        }
    }

    public void onClick(View view){
        Intent i = new Intent(MainActivity.this, GamePage.class );
        startActivity(i);
    }
}