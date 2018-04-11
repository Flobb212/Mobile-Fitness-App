package com.example.lobb.fitnessapp;

import java.util.ArrayList;

/**
 * Created by Lobb on 18/05/2016.
 */
public class Routine
{
    private String name;
    private ArrayList<Exercises> exerciseList;

    public Routine(String rName)
    {
        this.name = rName;
    }

    public ArrayList<Exercises> getExerciseList()
    {
        if(exerciseList == null)
        {
            exerciseList = new ArrayList<>();
        }

        ArrayList<Exercises> eList = exerciseList;
        return eList;
    }

    public void addExercise(Exercises newExercise)
    {
        if(exerciseList != null)
        {
            this.exerciseList.add(newExercise);
        }
        else
        {
            exerciseList = new ArrayList<>();
            this.exerciseList.add(newExercise);
        }
    }

    public String getName()
    {
        return name;
    }

    public void createTestData()
    {
        exerciseList = new ArrayList<>();
        Exercises testEx;

        testEx = new Exercises("Thing 1");
        testEx.createTestData();
        this.addExercise(testEx);

        testEx = new Exercises("Thing 2");
        testEx.createTestData();
        this.addExercise(testEx);

        testEx = new Exercises("Thing 3");
        testEx.createTestData();
        this.addExercise(testEx);
    }

}