package com.example.lobb.fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddExerciseActivity extends AppCompatActivity
{
    private Button buttonAddExBck = null;
    private Button buttonAddExSv = null;
    private Button buttonAddExAddWght = null;
    private EditText editTextExNm = null;
    private EditText editTextWght = null;

    private ArrayList<String> tempWeightList = null;
    private Boolean listAvailable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        buttonAddExBck = (Button) findViewById(R.id.buttonAddExerciseBack);
        buttonAddExSv = (Button) findViewById(R.id.buttonAddExerciseSave);
        buttonAddExAddWght = (Button) findViewById(R.id.buttonAddExerciseAddWeight);
        editTextExNm = (EditText) findViewById(R.id.editTextExerciseName);
        editTextWght = (EditText) findViewById(R.id.editTextWeightInput);

        buttonAddExBck.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(AddExerciseActivity.this, ExercisesActivity.class));
                finish();
            }
        });

        buttonAddExSv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                saveExercise();
            }
        });

        buttonAddExAddWght.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addWeight();
            }
        });
    }

    private void addWeight()
    {
        String weightEnt = editTextWght.getText().toString();

        if(weightEnt != null && !weightEnt.isEmpty())
        {
            listAvailable = true;

            if (tempWeightList == null)
            {
                tempWeightList = new ArrayList<String>();
            }

            tempWeightList.add(weightEnt);
        }
    }

    private void saveExercise()
    {
        String exerciseName = editTextExNm.getText().toString();

        if(exerciseName != null && !exerciseName.isEmpty())
        {
            Exercises newEx = new Exercises(exerciseName);

            if(listAvailable == true)
            {
                newEx.setWeightList(tempWeightList);
            }

            ExercisesActivity.exerciseList.add(newEx);
            startActivity(new Intent(AddExerciseActivity.this, ExercisesActivity.class));
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please enter a routine name", Toast.LENGTH_SHORT).show();
        }
    }

}
