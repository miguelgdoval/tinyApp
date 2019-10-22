package com.example.intro_android.api;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.intro_android.R;
import com.example.intro_android.model.University;
import com.example.intro_android.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class FavouriteListAdapter extends ArrayAdapter<University> {

    private final Activity context;

    List<University> universities;
    DatabaseHelper databaseHelper;

    private OnItemUniversityClickedListener mItemClickListener;

    public void setFavouriteUniversities(List<University> favouriteList, DatabaseHelper databaseHelper) {
        this.universities = favouriteList;
        this.databaseHelper = databaseHelper;
    }

    public FavouriteListAdapter(Activity context, List<University> universities) {
        super(context, R.layout.university_list_row, universities);
        this.context=context;
    }

    public void setOnItemClickedListener(OnItemUniversityClickedListener l) {
        mItemClickListener = l;
    }

    public interface OnItemUniversityClickedListener {
        void onItemUniversityClicked(University university);
    }

    public View getView(int position, View view, ViewGroup parent) {
        // Get the data item for this position
        University university = getItem(position);

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.favourite_list_row, null,true);

        TextView nameText = (TextView) rowView.findViewById(R.id.univName);
        String universityName = university.getName();
        nameText.setText(universityName);

        return rowView;
    };

    public void removeItemByUniversity(University university){
        University u = existsUniversity(university);
        if (u != null) {
            databaseHelper.deleteUniversity(university);
            universities.remove(u);
            notifyDataSetChanged();
        }
    }

    public void addItem(University item) {
        if (existsUniversity(item) == null){
            databaseHelper.addUniversity(item);
            universities.add(item);
            notifyDataSetChanged();
        }
    }

    public Boolean isUniversityFavourite(University university){
        return existsUniversity(university) != null;
    }

    public University existsUniversity(Object other){
        if (other == null) return null;
        if (other == this) return null;
        if (!(other instanceof University))return null;
        University univFav = (University) other;
        for (University u: universities) {
            if (u.getName().equals(univFav.getName())){
                return u;
            }
        }
        return null;
    }

}