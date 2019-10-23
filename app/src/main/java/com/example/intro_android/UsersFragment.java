package com.example.intro_android;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class UsersFragment extends Fragment {

    private ListView accountsList;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        final View outputView = inflater.inflate(R.layout.output_fragment, null);

        getListFromFirebase(outputView);

        return outputView;
    }

    private void getListFromFirebase(final View outputView) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<User> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String id = (String) document.getData().get("id");
                        String email = (String) document.getData().get("email");
                        String name = (String) document.getData().get("name");
                        String photo = (String) document.getData().get("photo");
                        User user = new User(id, email, name, photo);

                        list.add(user);

                    }
                    accountsList =  outputView.findViewById( R.id.accountsList);
                    accountsList.setAdapter(new UserListAdapter(getContext(), list) );
                    Log.d(TAG, list.toString());
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }
}
