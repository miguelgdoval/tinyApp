package com.example.intro_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OutputFragment extends Fragment {

    private TextView mTextMessage;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        final View outputView = inflater.inflate(R.layout.output_fragment, null);

        Bundle bundle = getArguments();
        String text = "";
        if (bundle != null) {
            text = bundle.getString("value");
        }
        mTextMessage = (TextView) outputView.findViewById(R.id.value);

        mTextMessage.setText(text);

        return outputView;
    }
}
