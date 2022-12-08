package com.ramazantiftik.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textTime;
    TextView textScore;
    Runnable runnable;
    Handler handler;
    int time;
    int score;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textTime=findViewById(R.id.textTime);
        textScore=findViewById(R.id.textScore);
        score=0;
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageArray=new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        handler=new Handler();

        new CountDownTimer(20000,1000){
            @Override
            public void onTick(long l) {
                textTime.setText("Left Time: "+l/1000);
            }
            @Override
            public void onFinish() {
                textTime.setText("Time Off");
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                handler.removeCallbacks(runnable);
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart the game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // restart
                        Intent intent=getIntent(); // MainActivity's intent
                        finish(); // MainActivity Off
                        startActivity(intent); // MainActivity On
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Game Over!",Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();
            }
        }.start();

        kennyMove();
    }

    public void inCreaseScore(View view){
        score++;
        textScore.setText("Score: "+score);
    }

    public void kennyMove(){
        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random random=new Random();
                int kenny= random.nextInt(9); // for kenny move
                imageArray[kenny].setVisibility(View.VISIBLE);
                handler.postDelayed(runnable,500); // difficult
            }
        };
        handler.post(runnable);
    }



}