package com.example.ashutosh.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    int answer; TextView questionTextView; android.support.v7.widget.GridLayout optionsGridLayout;
    int score = 0; TextView scoreTextView; TextView timerTextView;
    int total = 0; LinearLayout controlLinearLayout;
    LinearLayout gameOverLinearLayout;
    TextView gameOverTextView; boolean gameActive = false;

    public void newRound(){

        if(gameActive) {
            questionTextView = (TextView) findViewById(R.id.questionTextView);


            Random rand = new Random();
            int first = rand.nextInt(50) + 1;
            int second = rand.nextInt(50) + 1;
            questionTextView.setText(String.valueOf(first) + " + " + String.valueOf(second));

            answer = first + second;
            int answerChild = rand.nextInt(4);

            int[] a = new int[4];
            for (int i = 0; i < 4; i++) {
                if (i != answerChild) {
                    int diff = rand.nextInt(30) - 17;
                    int k = 13;
                    if (diff == 0) {
                        diff += 1;
                    }
                    if ((a[0] == diff) || (a[1] == diff) || (a[2] == diff) || (a[3] == diff)) {

                        diff = k + rand.nextInt(15);

                    }
                    a[i] = diff;
                }
                ((TextView) optionsGridLayout.getChildAt(i)).setText(String.valueOf(a[i] + answer));
            }
        }

    }


    public void Check(View view){

        if (gameActive) {
            TextView option = (TextView) view;
            if (Integer.parseInt(option.getText().toString()) == answer) {
                score += 1;
            }
            total += 1;
            scoreTextView.setText(String.valueOf(score) + '/' + String.valueOf(total));
            newRound();
        }

    }


    public void gameBegin(){
        gameActive = true;
        newRound();
        new CountDownTimer(30300,1000){


            public void onTick(long millisecondsUntilLeft){

                timerTextView.setText(String.valueOf(millisecondsUntilLeft/1000));
            }
            public void onFinish(){
                timerTextView.setText(String.valueOf("0"));
                gameOverTextView.setText("Game Over!!\n" + "Your Score: " + String.valueOf(score) +"/" + String.valueOf(total));
                gameOverLinearLayout.setVisibility(View.VISIBLE);
                gameActive = false;
            }
        }.start();
    }

    public void gameStart(View view){
        controlLinearLayout.setVisibility(View.VISIBLE);
        optionsGridLayout.setVisibility(View.VISIBLE);
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setVisibility(View.GONE);
        gameBegin();
    }

    public void playAgain(View view){

        gameOverLinearLayout.setVisibility(View.GONE);
        gameBegin();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        optionsGridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.optionsGridLayout);
        gameOverTextView= (TextView) findViewById(R.id.gameOverTextView);
        gameOverLinearLayout = (LinearLayout) findViewById(R.id.gameOverLinearLayout);
        scoreTextView.setText("0/0");
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        timerTextView.setText("30");

        controlLinearLayout = (LinearLayout) findViewById(R.id.controlLinearLayout);
        controlLinearLayout.setVisibility(View.GONE);
        optionsGridLayout.setVisibility(View.GONE);
        gameOverLinearLayout.setVisibility(View.GONE);

    }

}
