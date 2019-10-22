package com.example.intro_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.intro_android.api.FavouriteListAdapter;
import com.example.intro_android.api.UniversityDetailsFragment;
import com.example.intro_android.api.UniversityListFragment;
import com.example.intro_android.model.University;
import com.example.intro_android.sql.DatabaseHelper;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements UniversityDetailsFragment.onGetUniversityFavouriteListener,
        UniversityDetailsFragment.OnUniversityFavouriteListener,
        UniversityListFragment.OnClickDetails {

    private List<University> universitiesList;

    private UniversitiesTask mUniversitiesTask = null;

    private FavouriteListAdapter favouritesListAdapter;
    private List<University> favouritesList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        favouritesList = databaseHelper.getAllUniversities();

        this.favouritesListAdapter = new FavouriteListAdapter(this, favouritesList);
        this.favouritesListAdapter.setFavouriteUniversities(favouritesList, databaseHelper);
        this.favouritesListAdapter.setOnItemClickedListener(new FavouriteListAdapter.OnItemUniversityClickedListener() {
            @Override
            public void onItemUniversityClicked(University university) {
                if (universitiesList!=null) {
                    Bundle args = new Bundle();
                    args.putSerializable("university", university);
                    UniversityDetailsFragment universityDetailsFragment = new UniversityDetailsFragment();
                    universityDetailsFragment.setArguments(args);
                    setOnUniversityFavouriteListener(universityDetailsFragment, university);
                    switchFragment(universityDetailsFragment, false);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Unable to get selected university", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mUniversitiesTask = new UniversitiesTask();
        mUniversitiesTask.execute((Void) null);
    }

    public FavouriteListAdapter getFavouritesAdapter(){
        return this.favouritesListAdapter;
    }

    public boolean switchFragment(Fragment fragment, boolean firstFragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            if (!firstFragment) {
                transaction.addToBackStack(null);
            }
            transaction.commit();
            return true;
        }
        return false;
    }

    public void setOnUniversityFavouriteListener(UniversityDetailsFragment fragment, University university){
        fragment.setOnUniversityFavouriteListener(this, this);
    };

    public void setOnUniversityDetailsListener(UniversityListFragment fragment){
        fragment.setOnUniversityDetailsListener(this);
    };

    @Override
    public void onUniversitySelected(View view, int position) {
        if (universitiesList!=null) {
        University university = universitiesList.get(position);
        Bundle args = new Bundle();
        args.putSerializable("university", university);
        UniversityDetailsFragment universityFragment = new UniversityDetailsFragment();
        universityFragment.setArguments(args);
        setOnUniversityFavouriteListener(universityFragment, university);
        switchFragment(universityFragment, false);
        }
        else {
            Toast.makeText(this, "Unable to get selected university", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFavouriteClicked(University university, Boolean addFavourite) {
        if (addFavourite){
            favouritesListAdapter.addItem(university);
            Toast.makeText(this, "Recipe added to favourites", Toast.LENGTH_SHORT).show();
        } else{
            favouritesListAdapter.removeItemByUniversity(university);
            Toast.makeText(this, "Recipe removed from favourites", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Boolean onUniversityFavourite(University university) {
        return favouritesListAdapter.isUniversityFavourite(university);
    }


    public class UniversitiesTask extends AsyncTask<Void, Void, List<University>> {

        private List<University> universities;

        UniversitiesTask() {
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected List<University> doInBackground(Void... params) {
            try {
                final String url;
                url = "http://universities.hipolabs.com/search?country=spain";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ResponseEntity<List<University>> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<University>>(){});
                universities = response.getBody();
                universitiesList = universities;
                return universities;
            } catch (Exception e) {
                Log.e("Error getting univ -", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(final List<University> universities) {
            mUniversitiesTask = null;

            if (universities == null || universities.isEmpty()) {
                Toast.makeText(getBaseContext(), "Error getting universities", Toast.LENGTH_LONG).show();
            } else {
                Bundle args = new Bundle();
                ArrayList<University> casted = new ArrayList<University>(universities);
                args.putParcelableArrayList("list", casted);
                UniversityListFragment universitiesFragment = new UniversityListFragment();
                universitiesFragment.setArguments(args);
                setOnUniversityDetailsListener(universitiesFragment);
                switchFragment(universitiesFragment, true);
            }
        }

        @Override
        protected void onCancelled() {

        }
    }

}