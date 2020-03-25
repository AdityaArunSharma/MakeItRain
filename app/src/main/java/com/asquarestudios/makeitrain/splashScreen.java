package com.asquarestudios.makeitrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Handler;
import android.view.Window;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

import static android.graphics.Color.RED;
import static com.asquarestudios.makeitrain.R.layout.activity_main;
import static com.asquarestudios.makeitrain.R.layout.splash;

public class splashScreen extends AppCompatActivity
{

    private int moneyCounter = 0;
    private TextView moneyText;
    private ConstraintLayout background;
    long start=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d("", "onCreate: Main Activity started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moneyText = findViewById(R.id.money_text);
        background = findViewById(R.id.background);
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        // Log.d("onTouchEvent", "Clicked "+event);
        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            start=event.getEventTime();
            incrementMoney();
            changeBackground();
        }
        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            long end = event.getEventTime();
            Log.d("", "TIMMING!!!! end = " + end);
            long time_eclisped = ((end - start) / 1000);
            Log.d("Measure", "Time eclipsed = " + (end - start));
            if ((end - start) >= 400)
            {
                Log.d("onTouchEvent_if", "onTouchEvent: Long Press ");
                reset();
            }
        }
        return true;
    }
    public void changeBackground()
    {
        double red=Math.random();
        double green=Math.random();
        double blue=Math.random();
        int r=(int)(red*255)+1;
        int g=(int)(green*255)+1;
        int b=(int)(blue*255)+1;
        background.setBackgroundColor(Color.rgb(r,g,b));
        moneyText.setTextColor(Color.rgb(b,r,g));
    }
    public void incrementMoney()
    {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        if(moneyCounter<1000)
        {
            moneyCounter+=100;
        }
        else if(moneyCounter>=1000 && moneyCounter<10000)
        {
            moneyCounter+=1000;
        }
        else if(moneyCounter>=10000 && moneyCounter<100000)
        {
            moneyCounter+=5000;
        }
        else
        {
            moneyCounter+=10000;
        }
        moneyText.setText(String.valueOf(numberFormat.format(moneyCounter)));
    }
    public void reset()
    {

        moneyCounter=0;
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        moneyText.setText(String.valueOf(numberFormat.format(moneyCounter)));
    }
}

