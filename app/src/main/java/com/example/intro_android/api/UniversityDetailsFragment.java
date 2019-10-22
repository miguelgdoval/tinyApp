package com.example.intro_android.api;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.intro_android.model.University;

import com.example.intro_android.R;

public class UniversityDetailsFragment extends Fragment {

    private University university;
    private OnUniversityFavouriteListener callback;
    private onGetUniversityFavouriteListener callbackFav;
    private Boolean isUniversityFavourite;

    public void setOnUniversityFavouriteListener(OnUniversityFavouriteListener callback, onGetUniversityFavouriteListener callbackFav) {
        this.callback = callback;
        this.callbackFav = callbackFav;
    }

    public interface OnUniversityFavouriteListener{
        void onFavouriteClicked(University university, Boolean addFavourite);
    }

    public interface onGetUniversityFavouriteListener{
        Boolean onUniversityFavourite(University university);
    }

    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_university_details, container, false);

        Bundle bundle = getArguments();
        university = (University) bundle.getSerializable("university");

        final TextView nameText = (TextView) view.findViewById(R.id.titleUniversityName);
        nameText.setText(university.getName());

        if (university.getAlphaTwoCode()!=null) {
            TextView alphaText = (TextView) view.findViewById(R.id.universityAlphaTwoCode);
            alphaText.setText(android.text.TextUtils.concat("Country: ",university.getAlphaTwoCode()));
        }

        if (university.getCountry()!=null) {
            TextView timeText = (TextView) view.findViewById(R.id.universityCountry);
            timeText.setText(android.text.TextUtils.concat("Country: ",university.getCountry()));
        }

        if (university.getDomains()!=null && !university.getDomains().isEmpty()) {
            TextView domainText = (TextView) view.findViewById(R.id.universityDomain);
            domainText.setText(android.text.TextUtils.concat("Domain: ",university.getDomains().get(0)));
        }

        if (university.getStateProvince()!=null) {
            TextView stateText = (TextView) view.findViewById(R.id.universityStateProvince);
            stateText.setText(android.text.TextUtils.concat("State: ",university.getStateProvince()));
        }

        if (university.getWebPages()!=null && !university.getWebPages().isEmpty()) {
            TextView webPageText = (TextView) view.findViewById(R.id.universityWebPage);
            webPageText.setText(android.text.TextUtils.concat("Web Page: ",university.getWebPages().get(0)));
        }


        final Button favouriteButton = view.findViewById(R.id.favButton);
        isUniversityFavourite = callbackFav.onUniversityFavourite(university);

        int color = Color.parseColor("#FAF9F8");
        if (isUniversityFavourite){
            color = Color.parseColor("#ff0000");
        }

        //favouriteButton.setBackgroundColor(color);
        favouriteButton.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        favouriteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int color;
                isUniversityFavourite = callbackFav.onUniversityFavourite(university);

                if (isUniversityFavourite){
                    color = Color.parseColor("#FAF9F8");
                    callback.onFavouriteClicked(university, false);
                    //favouriteButton.setBackgroundColor(color);
                    favouriteButton.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
                }
                else{
                    color = Color.parseColor("#ff0000");
                    callback.onFavouriteClicked(university, true);
                    //favouriteButton.setBackgroundColor(color);
                    favouriteButton.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
                }
                //favouriteButton.setBackgroundColor(color);
                favouriteButton.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
            }
        });

        return view;
    }

}
