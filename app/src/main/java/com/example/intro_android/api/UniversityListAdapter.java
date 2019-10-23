package com.example.intro_android.api;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.intro_android.model.University;

import java.util.List;
import com.example.intro_android.R;

public class UniversityListAdapter  extends ArrayAdapter<University> {

    private final Activity context;

    public UniversityListAdapter(Activity context, List<University> universities) {
        super(context, R.layout.university_list_row, universities);
        this.context=context;
    }

    public View getView(int position, View view, ViewGroup parent) {
        // Get the data item for this position
        University university = getItem(position);

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.university_list_row, null,true);

        TextView titleText = rowView.findViewById(R.id.universityName);

        String universityName = university.getName();
        titleText.setText(universityName);

        return rowView;
    }

}