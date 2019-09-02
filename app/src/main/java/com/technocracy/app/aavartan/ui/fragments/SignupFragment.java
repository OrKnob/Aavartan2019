package com.technocracy.app.aavartan.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.technocracy.app.aavartan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    private Button buSignup;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        initView(view);
        return view;

    }

    private void initView(View view){

        /*buSignup = view.findViewById(R.id.buSignup);
        buSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }


        });*/

    }


}
