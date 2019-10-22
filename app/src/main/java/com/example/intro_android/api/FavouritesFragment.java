package com.example.intro_android.api;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.fragment.app.Fragment;

import com.example.intro_android.MainActivity;
import com.example.intro_android.R;
import com.example.intro_android.model.University;

import java.util.List;

public class FavouritesFragment extends Fragment {

    private ListView listView;
    private FavouriteListAdapter favAdapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View favouritesView = (View) inflater.inflate(R.layout.fragment_favourites, container , false);

        listView = favouritesView.findViewById(R.id.favourites_list);
        this.favAdapter = ((MainActivity) getActivity()).getFavouritesAdapter();
        listView.setAdapter(favAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity)getActivity()).onUniversitySelected(favouritesView, position);
            }
        });

        return favouritesView;
    }

}
