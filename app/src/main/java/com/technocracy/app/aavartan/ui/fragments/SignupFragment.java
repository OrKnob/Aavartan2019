package com.technocracy.app.aavartan.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.APIServices;
import com.technocracy.app.aavartan.api.AppClient;
import com.technocracy.app.aavartan.api.data_models.ResponseAPI;
import com.technocracy.app.aavartan.api.data_models.SignupData;
import com.technocracy.app.aavartan.ui.activities.OTPVerifyActivity;
import com.technocracy.app.aavartan.utils.AppConstants;
import com.technocracy.app.aavartan.utils.ValidationManager;

import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */

public class SignupFragment extends Fragment {

    private Button buSignup;
    private TextInputEditText etFullName, etEmail, etMobileNumber, etPassword, etConfirmPassword, etCollege, etBranch, etCourse, etSemester, etCity;

    private boolean isValidFullName = false, isValidEmail = false, isValidMobileNumber = false, isValidPassword = false, passwordsMatch = false;
    private int semester;
    private String password, name, email, mobileNumber, college, branch, course, city;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        initView(view);
        setListeners();
        return view;

    }

    private void initView(View view) {

        etFullName = view.findViewById(R.id.etFullName);
        etEmail = view.findViewById(R.id.etEmail);
        etMobileNumber = view.findViewById(R.id.etMobileNumber);
        etPassword = view.findViewById(R.id.etPassword);
        etConfirmPassword = view.findViewById(R.id.etConfirmPassword);
        etCollege = view.findViewById(R.id.etCollege);
        etBranch = view.findViewById(R.id.etBranch);
        etCourse = view.findViewById(R.id.etCourse);
        etSemester = view.findViewById(R.id.etSemester);
        etCity = view.findViewById(R.id.etCity);
        buSignup = view.findViewById(R.id.buSignup);

    }

    private void setListeners() {

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
                } else {
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
                } else isValidEmail = true;
            }
        });

        etMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                isValidMobileNumber = false;
                if (ValidationManager.isFieldEmpty(Objects.requireNonNull(etMobileNumber.getText()).toString())) {
                    etMobileNumber.setError("Field Cannot be Empty");
                } else if (!ValidationManager.isValidMobileNumber(etMobileNumber.getText().toString())) {
                    etMobileNumber.setError("Enter Valid Mobile Number");
                } else isValidMobileNumber = true;

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
                } else isValidPassword = true;
            }
        });

        etConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                passwordsMatch = false;
                if (!Objects.requireNonNull(etConfirmPassword.getText()).toString().equals(Objects.requireNonNull(etPassword.getText()).toString())) {
                    etConfirmPassword.setError("Passwords do not match");
                } else passwordsMatch = true;
            }
        });

        buSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidFullName && isValidEmail && isValidMobileNumber && isValidPassword && passwordsMatch) {
                    password = Objects.requireNonNull(etPassword.getText()).toString();
                    email = Objects.requireNonNull(etEmail.getText()).toString();
                    name = Objects.requireNonNull(etFullName.getText()).toString();
                    mobileNumber = Objects.requireNonNull(etMobileNumber.getText()).toString();
                    college = Objects.requireNonNull(etCollege.getText()).toString();
                    branch = Objects.requireNonNull(etBranch.getText()).toString();
                    course = Objects.requireNonNull(etCourse.getText()).toString();
                    semester = Integer.valueOf(Objects.requireNonNull(etSemester.getText()).toString());
                    city = Objects.requireNonNull(etCity.getText()).toString();
//                    apiCall();
                    SignupData signupData = new SignupData(password,name,email,mobileNumber,college,branch,course,semester,city);
                    Intent intent = new Intent(getActivity(), OTPVerifyActivity.class);
                    intent.putExtra(AppConstants.OTP_INTENT_EXTRA,signupData);
                    startActivity(intent);
                    Objects.requireNonNull(getActivity()).finish();
                } else {
                    Toasty.error(Objects.requireNonNull(getContext()), "One or More Fields are Incorrect", Toasty.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void apiCall() {
        APIServices apiServices = AppClient.getInstance().createService(APIServices.class);
        /*Call<SignupData> call = apiServices.createUser(password, name, email, mobileNumber, college, branch, course, semester, city);
        call.enqueue(new Callback<SignupData>() {
            @Override
            public void onResponse(@NonNull Call<SignupData> call, @NonNull Response<SignupData> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (!String.valueOf(response.body().getKey()).equals("")) {
                        Log.d("LOG Signup :", response.body().toJSONString());
                        Intent intent = new Intent(getActivity(), OTPVerifyActivity.class);
                        startActivity(intent);
                        Objects.requireNonNull(getActivity()).finish();
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<SignupData> call, @NonNull Throwable t) {
                Log.d("LOG Signup :", t.toString());

            }
        });*/

        Call<ResponseAPI> call = apiServices.sendOTP(mobileNumber,password);

        call.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(@NonNull Call<ResponseAPI> call, @NonNull Response<ResponseAPI> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SignupData signupData = new SignupData(password,name,email,mobileNumber,college,branch,course,semester,city);
                    Intent intent = new Intent(getActivity(), OTPVerifyActivity.class);
                    intent.putExtra(AppConstants.OTP_INTENT_EXTRA,signupData);
                    startActivity(intent);
                    Objects.requireNonNull(getActivity()).finish();
                    Log.d("LOG Send OTP Response",response.body().getMessage());
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseAPI> call, @NonNull Throwable t) {
                Log.d("LOG Send OTP Fail :", t.toString());

            }
        });

    }

}