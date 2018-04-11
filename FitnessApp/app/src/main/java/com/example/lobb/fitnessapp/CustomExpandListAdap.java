package com.example.lobb.fitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.util.Log;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lobb on 19/05/2016.
 */
public class CustomExpandListAdap extends BaseExpandableListAdapter
{
    private Context adapContext;
    private ArrayList<String> adapExerciseNames;
    private HashMap<String, ArrayList<String>> adapWeights;

    public CustomExpandListAdap(Context thisCon, ArrayList<String> thisExNm, HashMap<String, ArrayList<String>> thisWeight)
    {
        this.adapContext = thisCon;
        this.adapExerciseNames = thisExNm;
        this.adapWeights = thisWeight;
    }


    @Override
    public int getGroupCount()
    {
        return this.adapExerciseNames.size();
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        //Take weight list and get size of exercise list
        Log.d("Error", adapWeights.toString());
        return this.adapWeights.get(this.adapExerciseNames.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return this.adapExerciseNames.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        // Gets a parent Exercise name, then gets the weight child from that
        return this.adapWeights.get(this.adapExerciseNames.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        String text = (String) getGroup(groupPosition);

        if(convertView == null)
        {
            LayoutInflater groupInflater = (LayoutInflater) this.adapContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = groupInflater.inflate(R.layout.list_view_text, null);
        }

        TextView listText = (TextView) convertView.findViewById(R.id.listViewText);
        listText.setText(text);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        //Take children text
        String text = (String) getChild(groupPosition, childPosition);

        //If list view isn't active, expand it
        if(convertView == null)
        {
            LayoutInflater childInflater = (LayoutInflater) this.adapContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = childInflater.inflate(R.layout.list_view_text, null);
        }

        //Link text to list text XML and fill expanded list properties with weights
        TextView listText = (TextView) convertView.findViewById(R.id.listViewText);
        listText.setText(text);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }
}
