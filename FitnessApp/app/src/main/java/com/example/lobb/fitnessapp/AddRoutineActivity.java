package com.example.lobb.fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRoutineActivity extends AppCompatActivity
{
    private EditText editTextNm = null;
    private Button buttonAddRoutSv = null;
    private Button buttonAddRoutBck = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);

        buttonAddRoutSv = (Button) findViewById(R.id.buttonAddRoutineSave);
        buttonAddRoutBck = (Button) findViewById(R.id.buttonAddRoutineBack);
        editTextNm = (EditText) findViewById(R.id.editTextName);

        buttonAddRoutSv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                saveRoutineName();
            }
        });

        buttonAddRoutBck.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(AddRoutineActivity.this, RoutineActivity.class));
                finish();
            }
        });

    }

    private void saveRoutineName()
    {
        String routineName = editTextNm.getText().toString();

        if(routineName != null && !routineName.isEmpty())
        {
            Routine newRout = new Routine(routineName);

            //Pass routine back
            RoutineActivity.routineList.add(newRout);
            startActivity(new Intent(AddRoutineActivity.this, RoutineActivity.class));
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please enter a routine name", Toast.LENGTH_SHORT).show();
        }

    }
}
