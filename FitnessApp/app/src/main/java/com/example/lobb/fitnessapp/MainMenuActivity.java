package com.example.lobb.fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity
{
    private Button buttonRout = null;
    private Button buttonTimedSess = null;
    private Button buttonHrtRt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        buttonRout = (Button) findViewById(R.id.buttonRoutines);
        buttonTimedSess = (Button) findViewById(R.id.buttonTimedSessions);
        buttonHrtRt = (Button) findViewById(R.id.buttonHeartRate);

        buttonRout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainMenuActivity.this, RoutineActivity.class));
            }
        });

        buttonTimedSess.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainMenuActivity.this, TimedSessionsActivity.class));
            }
        });

        buttonHrtRt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainMenuActivity.this, HeartRateActivity.class));
            }
        });

    }

}
