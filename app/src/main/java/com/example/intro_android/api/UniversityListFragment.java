package com.example.intro_android.api;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.intro_android.MainActivity;
import com.example.intro_android.model.University;

import java.util.List;
import com.example.intro_android.R;

public class UniversityListFragment extends Fragment {

    GridView grid;
    private TextView mTextMessage;

    private List<University> universitiesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View universitiesView = inflater.inflate(R.layout.fragment_universities, null);

        Bundle bundle = getArguments();
        universitiesList = bundle.getParcelableArrayList("list");

        UniversityListAdapter adapter = new UniversityListAdapter(getActivity(), universitiesList);

        grid = universitiesView.findViewById(R.id.universities_grid);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity)getActivity()).onUniversitySelected(universitiesView, position);
            }
        });

        mTextMessage = (TextView) universitiesView.findViewById(R.id.message);
        return universitiesView;
    }
}

