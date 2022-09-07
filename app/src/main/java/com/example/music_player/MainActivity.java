package com.example.music_player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button bs1,bs2,bs3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bs1=findViewById(R.id.btnsong1);

        bs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity.this,song1.class);
                startActivity(intent1);
                finish();


            }
        });
        bs2=findViewById(R.id.btnsong2);
        bs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(MainActivity.this,song2.class);
                startActivity(intent2);
                finish();

            }
        });
        bs3=findViewById(R.id.btnsong3);
        bs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 =new Intent(MainActivity.this,song3.class);
                startActivity(intent3);
                finish();

            }
        });
    }
}