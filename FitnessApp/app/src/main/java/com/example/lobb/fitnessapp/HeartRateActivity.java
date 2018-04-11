package com.example.lobb.fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HeartRateActivity extends AppCompatActivity
{

    private Button buttonHrtRtBack = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);

        buttonHrtRtBack = (Button) findViewById(R.id.buttonHeartRateBack);

        buttonHrtRtBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

    }
}
