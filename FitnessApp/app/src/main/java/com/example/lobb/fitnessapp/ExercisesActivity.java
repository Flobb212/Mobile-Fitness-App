package com.example.lobb.fitnessapp;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ExercisesActivity extends AppCompatActivity
{
    private static Routine selectedRoutine = null;

    private TextView textViewNm = null;
    private Button buttonAddEx = null;
    private Button buttonDelEx = null;
    private Button buttonBckEx = null;
    private Button buttonAddWght = null;
    private ExpandableListView exListVw = null;
    private ExpandableListAdapter exListAdapter = null;
    private HashMap weightListing = null;
    ArrayList<String> exerciseNames = null;

    public static ArrayList<Exercises> exerciseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        textViewNm = (TextView) findViewById(R.id.textViewName);
        buttonAddEx = (Button) findViewById(R.id.buttonAddExercise);
        buttonDelEx = (Button) findViewById(R.id.buttonDeleteExercise);
        buttonBckEx = (Button) findViewById(R.id.buttonExercisesBack);
        buttonAddWght = (Button) findViewById(R.id.buttonAddWeight);
        exListVw = (ExpandableListView) findViewById(R.id.expandableListViewExercises);

        textViewNm.setText(selectedRoutine.getName());

        buttonAddEx.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(ExercisesActivity.this, AddExerciseActivity.class));
                finish();
            }
        });

        buttonDelEx.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Check for selected list item
                // Remove item from list
            }
        });

        buttonBckEx.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(ExercisesActivity.this, RoutineActivity.class));
                finish();
            }
        });

        buttonAddWght.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Check exercise is selected
                // Add new weight
            }
        });

        if(exerciseList.isEmpty())
        {
            fillExerciseList();
        }
        else
        {
            populateExpandableList();
        }

    }

    private void fillExerciseList()
    {
        exerciseList = selectedRoutine.getExerciseList();
        populateExpandableList();

    }

    private void populateExpandableList()
    {
        weightListing = new HashMap<String, ArrayList<String>>();
        exerciseNames = new ArrayList<>();

        for(int value = 0; value < exerciseList.size(); value++)
        {
            Exercises temp = exerciseList.get(value);
            String name = temp.getName();
            exerciseNames.add(name);

            ArrayList<String> exerciseWeights = temp.getWeightList();
            weightListing.put(name, exerciseWeights);

            //ArrayList<String> testing = new ArrayList<String>();
            //testing.add("Test1");
            //testing.add("Test2");
            //testing.add("Test3");
            //weightListing.put(name, testing);
        }

        exListAdapter = new CustomExpandListAdap(this, exerciseNames, weightListing);
        exListVw.setAdapter(exListAdapter);
    }

    public static void setRoutine(Routine thisRoutine)
    {
        selectedRoutine = thisRoutine;
    }
}
