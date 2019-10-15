package com.example.intro_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

public class InputFragment extends Fragment implements View.OnClickListener
{
    Toast toast;

    private Button button;

    private TextInputLayout textInputLayout;

    private EditText textInputEditText;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inputView = inflater.inflate(R.layout.input_fragment, null);

        toast = Toast.makeText(getActivity(),
                "Tiny form!", Toast.LENGTH_SHORT);
        toast.show();


        textInputLayout = (TextInputLayout) inputView.findViewById(R.id.textInputLayout);

        textInputEditText = (EditText) inputView.findViewById(R.id.input_field);

        button = (Button) inputView.findViewById(R.id.btn);

        button.setOnClickListener(this);

        // Button listeners
        inputView.findViewById(R.id.btn).setOnClickListener(this);

        return inputView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                ((MainActivity)getActivity()).showOutput(textInputEditText.getText().toString());
                break;
        }
    }
}
