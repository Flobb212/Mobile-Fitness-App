package com.example.lobb.fitnessapp;

import java.util.ArrayList;

/**
 * Created by Lobb on 18/05/2016.
 */
public class Exercises
{
    private String name;
    private ArrayList<String> weightList;

    public Exercises(String eName)
    {
        this.name = eName;
    }

    public Exercises(String eName, ArrayList<String> wList)
    {
        this.name = eName;
        this.weightList = wList;
    }

    public void addWeight(String newWeight)
    {
        if(weightList != null)
        {
            this.weightList.add(newWeight);
        }
        else
        {
            weightList = new ArrayList<>();
            this.weightList.add(newWeight);
        }
    }

    public  ArrayList<String> getWeightList()
    {
        if(weightList == null)
        {
            weightList = new ArrayList<>();
        }

        ArrayList<String> wList = weightList;
        return wList;
    }

    public String getName()
    {
        return name;
    }

    public void setWeightList(ArrayList<String> tempList)
    {
        weightList = tempList;
    }

    public void createTestData()
    {
        weightList = new ArrayList<>();
        String testWeight;

        testWeight = "15";
        this.addWeight(testWeight);

        testWeight = "20";
        this.addWeight(testWeight);

        testWeight = "25";
        this.addWeight(testWeight);
    }
}
