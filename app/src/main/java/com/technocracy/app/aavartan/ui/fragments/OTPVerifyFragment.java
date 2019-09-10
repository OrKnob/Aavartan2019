package com.technocracy.app.aavartan.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.ui.activities.MainActivity;
import com.technocracy.app.aavartan.utils.OTPTextWatcher;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class OTPVerifyFragment extends Fragment {

    private Button buVerify;
    private EditText etOTPVerify1, etOTPVerify2, etOTPVerify3, etOTPVerify4, etOTPVerify5, etOTPVerify6;

    public OTPVerifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_otp_verify, container, false);
        initView(view);
        setListeners(view);
        return view;
    }

    private void initView(View view) {

        etOTPVerify1 = view.findViewById(R.id.etOTPVerify1);
        etOTPVerify2 = view.findViewById(R.id.etOTPVerify2);
        etOTPVerify3 = view.findViewById(R.id.etOTPVerify3);
        etOTPVerify4 = view.findViewById(R.id.etOTPVerify4);
        etOTPVerify5 = view.findViewById(R.id.etOTPVerify5);
        etOTPVerify6 = view.findViewById(R.id.etOTPVerify6);
        buVerify = view.findViewById(R.id.buVerify);

    }

    private void setListeners(View view) {

        etOTPVerify1.addTextChangedListener(new OTPTextWatcher(view,etOTPVerify1));
        etOTPVerify2.addTextChangedListener(new OTPTextWatcher(view,etOTPVerify2));
        etOTPVerify3.addTextChangedListener(new OTPTextWatcher(view,etOTPVerify3));
        etOTPVerify4.addTextChangedListener(new OTPTextWatcher(view,etOTPVerify4));
        etOTPVerify5.addTextChangedListener(new OTPTextWatcher(view,etOTPVerify5));
        etOTPVerify6.addTextChangedListener(new OTPTextWatcher(view,etOTPVerify6));
        buVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                Objects.requireNonNull(getActivity()).finish();
            }
        });

    }

}
