package com.example.lobb.fitnessapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TimedSessionsActivity extends AppCompatActivity
{
    private CountDownTimer currentTimer = null;

    private Button buttonTmdSessBack = null;
    private Button buttonStrtTmr = null;
    private Button buttonPsTmr = null;
    private Button buttonEntInt = null;

    private TextView txtVwHrs = null;
    private TextView txtVwMins = null;
    private TextView txtVwSecs = null;
    private TextView txtVwCount = null;

    private Spinner spinnerHrs = null;
    private Spinner spinnerMins = null;
    private Spinner spinnerSecs = null;

    public static int checkTimeEntered = 0;
    private int totalTimeInMS = 0;
    private Long millisAtPause = null;
    private Boolean timerActive = false;
    private Boolean startingTimer = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timed_sessions);

        buttonTmdSessBack = (Button) findViewById(R.id.buttonTimedSessionsBack);
        buttonStrtTmr = (Button) findViewById(R.id.buttonStartTimer);
        buttonPsTmr = (Button) findViewById(R.id.buttonPauseTimer);
        buttonEntInt = (Button) findViewById(R.id.buttonEnterIntervals);
        txtVwHrs = (TextView) findViewById(R.id.textViewHours);
        txtVwMins = (TextView) findViewById(R.id.textViewMinutes);
        txtVwSecs = (TextView) findViewById(R.id.textViewSeconds);
        txtVwCount = (TextView) findViewById(R.id.textViewCountdown);
        spinnerHrs = (Spinner) findViewById(R.id.spinnerHours);
        spinnerMins = (Spinner) findViewById(R.id.spinnerMinutes);
        spinnerSecs = (Spinner) findViewById(R.id.spinnerSeconds);

        buttonTmdSessBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        buttonStrtTmr.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(startingTimer == true)
                {
                    startingTimer = false;
                    timerActive = true;
                    buttonStrtTmr.setText("Stop");

                    spinnerHrs.setVisibility(View.INVISIBLE);
                    spinnerMins.setVisibility(View.INVISIBLE);
                    spinnerSecs.setVisibility(View.INVISIBLE);
                    txtVwHrs.setVisibility(View.INVISIBLE);
                    txtVwMins.setVisibility(View.INVISIBLE);
                    txtVwSecs.setVisibility(View.INVISIBLE);

                    totalTimeInMS = calculateMilliSecs();

                    timer(Long.valueOf(totalTimeInMS));
                }
                else
                {
                    startingTimer = true;
                    timerActive = false;
                    buttonStrtTmr.setText("Start Timer");
                    buttonPsTmr.setText("Pause");

                    currentTimer.cancel();
                    txtVwCount.setText("00:00:00");
                    millisAtPause = null;

                    spinnerHrs.setVisibility(View.VISIBLE);
                    spinnerMins.setVisibility(View.VISIBLE);
                    spinnerSecs.setVisibility(View.VISIBLE);
                    txtVwHrs.setVisibility(View.VISIBLE);
                    txtVwMins.setVisibility(View.VISIBLE);
                    txtVwSecs.setVisibility(View.VISIBLE);
                }
            }
        });

        buttonPsTmr.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(timerActive == true)
                {
                    timerActive = false;
                    buttonPsTmr.setText("Resume");

                    if (millisAtPause != null)
                    {
                        Long millisLeft = millisAtPause;
                        currentTimer.cancel();
                    }
                }
                else
                {
                    if (millisAtPause != null)
                    {
                        timerActive = true;
                        buttonPsTmr.setText("Pause");

                        timer(millisAtPause);
                    }
                }
            }
        });

        buttonEntInt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkTimeEntered = calculateMilliSecs();

                if(checkTimeEntered <= 1000)
                {
                    //ERROR - Enter a time before creating intervals
                    Toast.makeText(getApplicationContext(), "Please enter a time into the spinners first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    startActivity(new Intent(TimedSessionsActivity.this, TimeIntervalsActivity.class));
                }
            }
        });
    }

    private int calculateMilliSecs()
    {
        int getHours = 0;
        int getMinutes = 0;
        int getSeconds = 0;
        int totalMillisecs = 0;

        getHours = spinnerHrs.getSelectedItemPosition();
        getMinutes = spinnerMins.getSelectedItemPosition();
        getSeconds = spinnerSecs.getSelectedItemPosition();

        totalMillisecs = (getHours * 60 * 60 * 1000) + (getMinutes * 60 * 1000) + (getSeconds * 1000) + 1000;

        return totalMillisecs;
    }


    public void timer(Long tempMilsLeft)
    {
        currentTimer = new CountDownTimer(tempMilsLeft, 1000)
        {
            long msToHours = 0;
            long msToMinutes = 0;
            long msToSeconds = 0;

            @Override
            public void onTick(long millisUntilFinished)
            {
                millisAtPause = millisUntilFinished;

                msToHours = (millisUntilFinished / 60 / 60 / 1000) - ((millisUntilFinished / 60 / 60 / 1000) % 1);
                msToMinutes = ((millisUntilFinished / 60 / 1000) - (msToHours * 60)) - (((millisUntilFinished / 60 / 1000) - (msToHours * 60)) % 1);
                msToSeconds = (((millisUntilFinished / 1000) - (msToHours * 3600)) - (msToMinutes * 60));

                String stringHours;
                String stringMinutes;
                String stringSeconds;

                if(msToHours < 10)
                {
                    stringHours = "0" + String.valueOf(msToHours);
                }
                else
                {
                    stringHours = String.valueOf(msToHours);
                }

                if(msToMinutes< 10)
                {
                    stringMinutes = "0" + String.valueOf(msToMinutes);
                }
                else
                {
                    stringMinutes = String.valueOf(msToMinutes);
                }

                if(msToSeconds < 10)
                {
                    stringSeconds = "0" + String.valueOf(msToSeconds);
                }
                else
                {
                    stringSeconds = String.valueOf(msToSeconds);
                }

                txtVwCount.setText(stringHours + ":" + stringMinutes + ":" + stringSeconds);
            }

            public void onFinish()
            {
                timerActive = false;
                buttonStrtTmr.setText("Start Timer");
                startingTimer = true;
                txtVwCount.setText("Finished");
                millisAtPause = null;
                spinnerHrs.setVisibility(View.VISIBLE);
                spinnerMins.setVisibility(View.VISIBLE);
                spinnerSecs.setVisibility(View.VISIBLE);
                txtVwHrs.setVisibility(View.VISIBLE);
                txtVwMins.setVisibility(View.VISIBLE);
                txtVwSecs.setVisibility(View.VISIBLE);
            }

        }.start();
    }



}