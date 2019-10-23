package com.example.intro_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;


public class ProfileFragment extends Fragment implements View.OnClickListener{

    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View profileView = inflater.inflate(R.layout.profile_fragment, null);

        Button signOutButton = profileView.findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(this);

        //CÓDIGO SHARED_PREFERENCES
        SharedPreferences preferences = getActivity().getSharedPreferences(
                "OUR_PREFERENCES", Context.MODE_PRIVATE);
        String email = preferences.getString("email", "");
        String name = preferences.getString("name", "");
        String photo = preferences.getString("photo", "");

        TextView textView = profileView.findViewById(R.id.userEmail);
        if (!email.isEmpty()) {
            textView.setText(email);
        }

        TextView usernameTextView = profileView.findViewById(R.id.userName);
        if (!name.isEmpty()) {
            usernameTextView.setText(name);
        }
        ImageView profileImage = profileView.findViewById(R.id.profile_image);
        if (!photo.isEmpty()) {
            Picasso.get().load(photo).into(profileImage);
        }

        //CÓDIGO FIREBASE USER
        /*TextView textView = profileView.findViewById(R.id.userEmail);
        textView.setText(currentFirebaseUser.getEmail());

        TextView usernameTextView = profileView.findViewById(R.id.userName);
        usernameTextView.setText(currentFirebaseUser.getDisplayName());
        ImageView profileImage = profileView.findViewById(R.id.profile_image);
        Picasso.get().load(currentFirebaseUser.getPhotoUrl()).into(profileImage);
*/



        return profileView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signOutButton:
                signOut();
                break;
        }
    }

    private void signOut() {
        AuthUI.getInstance()
                .signOut(getActivity())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // user is now signed out

                        Intent main = new Intent(getActivity(), MainActivity.class);
                        main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(main);


                    }
                });
    }

}
