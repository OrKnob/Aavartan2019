package com.technocracy.app.aavartan.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.ui.activities.MainActivity;
import com.technocracy.app.aavartan.utils.ValidationManager;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private Button buLogin;
    private TextInputEditText etFullName,etEmail,etPassword;

    private boolean isValidFullName = false,isValidEmail = false, isValidPassword = false;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initView(view);
        setListeners();
        return view;
    }

    private void initView(View view) {

        etFullName = view.findViewById(R.id.etFullName);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        buLogin = view.findViewById(R.id.buLogin);

    }

    private void setListeners(){

        etFullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (ValidationManager.isFieldEmpty(Objects.requireNonNull(etFullName.getText()).toString())) {
                    etFullName.setError("Field Cannot be Empty");
                    isValidFullName = false;
                }
                else {
                    isValidFullName = true;
                }
            }
        });

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                isValidEmail = false;
                if (ValidationManager.isFieldEmpty(Objects.requireNonNull(etEmail.getText()).toString())) {
                    etEmail.setError("Field Cannot be Empty");
                } else if (ValidationManager.isEmailValid(etEmail.getText().toString())) {
                    etEmail.setError("Enter Valid Email");
                }
                else isValidEmail = true;
            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                isValidPassword = false;
                if (ValidationManager.isFieldEmpty(Objects.requireNonNull(etPassword.getText()).toString())) {
                    etPassword.setError("Field Cannot be Empty");
                } else if (ValidationManager.isValidPassword(etPassword.getText().toString())) {
                    etPassword.setError("Password must contain a letter and a number with length between 8-16 characters");
                }
                else isValidPassword = true;
            }
        });

        buLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidFullName && isValidEmail && isValidPassword){
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                    Objects.requireNonNull(getActivity()).finish();
                }
            }
        });

    }

}
