package com.example.intro_android.api;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.intro_android.model.University;

import java.util.List;
import com.example.intro_android.R;

public class UniversityListFragment extends Fragment {

    OnClickDetails mCallback;
    GridView grid;
    private TextView mTextMessage;

    private List<University> universitiesList;

    public void setOnUniversityDetailsListener(OnClickDetails callback) {
        this.mCallback = callback;
    }

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
                mCallback.onUniversitySelected(universitiesView, position);
            }
        });

        mTextMessage = universitiesView.findViewById(R.id.message);
        return universitiesView;
    }

    public interface OnClickDetails{
        void onUniversitySelected(View view, int position);
    }

}