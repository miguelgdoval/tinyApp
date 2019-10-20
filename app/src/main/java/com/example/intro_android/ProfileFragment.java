package com.example.intro_android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ProfileFragment extends Fragment {

    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View profileView = inflater.inflate(R.layout.profile_fragment, null);




        TextView textView = profileView.findViewById(R.id.userEmail);
        textView.setText(currentFirebaseUser.getEmail());

        TextView usernameTextView = profileView.findViewById(R.id.userName);
        usernameTextView.setText(currentFirebaseUser.getDisplayName());
        ImageView profileImage = profileView.findViewById(R.id.profile_image);
        Picasso.get().load(currentFirebaseUser.getPhotoUrl()).into(profileImage);




        return profileView;
    }
}
