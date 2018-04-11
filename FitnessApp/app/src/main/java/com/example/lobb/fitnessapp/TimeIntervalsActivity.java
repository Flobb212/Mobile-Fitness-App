package com.example.lobb.fitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TimeIntervalsActivity extends AppCompatActivity
{
    private Button buttonTmdIntBck = null;
    private int timeToUse = 0;

    private TextView textTest = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_intervals);

        textTest = (TextView) findViewById(R.id.textViewTest);
        buttonTmdIntBck = (Button) findViewById(R.id.buttonTimeIntervalBack);

        timeToUse = TimedSessionsActivity.checkTimeEntered;
        timeToUse = timeToUse - 1000;

        int msToHours = (timeToUse / 60 / 60 / 1000) - ((timeToUse / 60 / 60 / 1000) % 1);
        int msToMinutes = ((timeToUse / 60 / 1000) - (msToHours * 60)) - (((timeToUse / 60 / 1000) - (msToHours * 60)) % 1);
        int msToSeconds = (((timeToUse / 1000) - (msToHours * 3600)) - (msToMinutes * 60));

        String hours;
        String minutes;
        String seconds;

        if(msToHours < 10)
        {
            hours = "0" + String.valueOf(msToHours);
        }
        else
        {
            hours = String.valueOf(msToHours);
        }

        if(msToMinutes< 10)
        {
            minutes = "0" + String.valueOf(msToMinutes);
        }
        else
        {
            minutes = String.valueOf(msToMinutes);
        }

        if(msToSeconds < 10)
        {
            seconds = "0" + String.valueOf(msToSeconds);
        }
        else
        {
            seconds = String.valueOf(msToSeconds);
        }

        textTest.setText(hours + ":" + minutes + ":" + seconds);


        buttonTmdIntBck.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }
}
