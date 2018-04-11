package com.example.lobb.fitnessapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class RoutineActivity extends AppCompatActivity
{
    private Button buttonRoutBack = null;
    private Button buttonAddRout = null;
    private Button buttonDelRout = null;
    private Button buttonVwRout = null;
    public static ListView listVwRout = null;
    private Routine selectedRoutine = null;
    private int selectedPos = -1;

    public static ArrayList<Routine> routineList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);

        buttonRoutBack = (Button) findViewById(R.id.buttonRoutinesBack);
        buttonAddRout = (Button) findViewById(R.id.buttonAddRoutine);
        buttonDelRout = (Button) findViewById(R.id.buttonDeleteRoutine);
        buttonVwRout = (Button) findViewById(R.id.buttonViewRoutine);
        listVwRout = (ListView) findViewById(R.id.listViewRoutines);
        listVwRout.setChoiceMode((ListView.CHOICE_MODE_SINGLE));

        buttonAddRout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(RoutineActivity.this, AddRoutineActivity.class));
                finish();
            }
        });

        buttonDelRout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int position = selectedPos;

                if(position < 0)
                {
                    Toast.makeText(getApplicationContext(), "Please select a routine first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    selectedRoutine = routineList.get(selectedPos);
                    routineList.remove(selectedRoutine);
                    addRoutineToList();
                }
            }
        });

        buttonVwRout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int position = selectedPos;

                if(position < 0)
                {
                    Toast.makeText(getApplicationContext(), "Please select a routine first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    selectedRoutine = routineList.get(selectedPos);
                    ExercisesActivity.setRoutine(selectedRoutine);
                    startActivity(new Intent(RoutineActivity.this, ExercisesActivity.class));
                    finish();
                }
            }
        });

        buttonRoutBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        listVwRout.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(selectedPos == -1)
                {
                    view.setBackgroundColor(Color.GRAY);
                }
                else if(selectedPos != -1 && selectedPos != position)
                {
                    view.setBackgroundColor(Color.GRAY);
                    parent.getChildAt(selectedPos).setBackgroundColor(Color.WHITE);
                }

                selectedPos = position;
            }
        });

        if(routineList.isEmpty())
        {
            testData();
        }
        else
        {
            addRoutineToList();
        }

    }

    private void testData()
    {
        Routine item;

        item = new Routine("Back");
        item.createTestData();
        routineList.add(item);

        item = new Routine("Chest");
        item.createTestData();
        routineList.add(item);

        item = new Routine("Legs");
        item.createTestData();
        routineList.add(item);

        addRoutineToList();
    }

    public void addRoutineToList()
    {
        ArrayList<String> routineNames = new ArrayList<>();

        for(int value = 0; value < routineList.size(); value++)
        {
            Routine temp = routineList.get(value);
            String name = temp.getName();
            routineNames.add(name);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_view_text, routineNames);
        listVwRout.setAdapter(adapter);
    }


}
