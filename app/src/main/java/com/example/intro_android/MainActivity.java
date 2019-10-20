package com.example.intro_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.intro_android.api.UniversityDetailsFragment;
import com.example.intro_android.api.UniversityListFragment;
import com.example.intro_android.model.University;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UniversitiesTask universitiesTask = null;
    private List<University> universitiesList;

    private UniversitiesTask mUniversitiesTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUniversitiesTask = new UniversitiesTask();
        mUniversitiesTask.execute((Void) null);
    }

    public boolean switchFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.commit();
            return true;
        }
        return false;
    }

    public void onUniversitySelected(View view, int position){
           if (universitiesList!=null) {
                University university = universitiesList.get(position);
                Bundle args = new Bundle();
                args.putSerializable("university", university);
                UniversityDetailsFragment universityFragment = new UniversityDetailsFragment();
                universityFragment.setArguments(args);
                //setOnUniversityFavouriteListener(universityFragment, university);
                switchFragment(universityFragment);
            }
            else {
                Toast.makeText(this, "Unable to get selected university", Toast.LENGTH_SHORT).show();
            }
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
            universitiesTask = null;

            if (universities == null || universities.isEmpty()) {
                Toast.makeText(getBaseContext(), "Error getting universities", Toast.LENGTH_LONG).show();
            } else {
                Bundle args = new Bundle();
                ArrayList<University> casted = new ArrayList<University>(universities);
                args.putParcelableArrayList("list", casted);
                UniversityListFragment universitiesFragment = new UniversityListFragment();
                universitiesFragment.setArguments(args);
                switchFragment(universitiesFragment);
            }
        }

        @Override
        protected void onCancelled() {

        }
    }

}