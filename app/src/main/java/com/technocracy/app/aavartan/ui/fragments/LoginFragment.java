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
import com.technocracy.app.aavartan.api.data_models.LoginData;
import com.technocracy.app.aavartan.ui.activities.MainActivity;
import com.technocracy.app.aavartan.utils.UserPreferences;
import com.technocracy.app.aavartan.utils.ValidationManager;

import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private Button buLogin;
    private TextInputEditText etUsername, etEmail, etPassword;

    private boolean isValidUserName = false, isValidEmail = false, isValidPassword = false;
    private UserPreferences userPreferences;

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

        etUsername = view.findViewById(R.id.etUsername);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        buLogin = view.findViewById(R.id.buLogin);
        userPreferences = new UserPreferences(getActivity());

    }

    private void setListeners() {

        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (ValidationManager.isFieldEmpty(Objects.requireNonNull(etUsername.getText()).toString())) {
                    etUsername.setError("Field Cannot be Empty");
                    isValidUserName = false;
                } else if (!ValidationManager.isValidMobileNumber(etUsername.getText().toString())) {
                    etUsername.setError("Enter Valid Mobile Number");
                    isValidUserName = false;
                } else isValidUserName = true;
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
                } else isValidPassword = true;
            }
        });

        buLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidUserName && isValidEmail && isValidPassword) {
                    apiCall();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    Objects.requireNonNull(getActivity()).finish();
                } else {
                    Toasty.error(Objects.requireNonNull(getActivity()), "One or more Fields are Incorrect", Toasty.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void apiCall() {

        /*String jsonString = signupData.toJsonString();

        Log.d("Json String",jsonString);
        *//*APIServices apiServices = AppClient.getInstance().createService(APIServices.class);
        Call<JSONObject> call = apiServices.createUser(jsonString);*//*

        JSONObject jsonObject = signupData.toJSONList();

        Log.d("Json Object",jsonObject.toString());

        APIServices apiServices = AppClient.getInstance().createService(APIServices.class);
        Call<JSONObject> call = apiServices.createUser(jsonObject);

        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(@NonNull Call<JSONObject> call, @NonNull Response<JSONObject> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        Toasty.success(Objects.requireNonNull(getContext()),"User Created",Toasty.LENGTH_LONG).show();

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<JSONObject> call, @NonNull Throwable t) {

            }
        });*/
        APIServices apiServices = AppClient.getInstance().createService(APIServices.class);
        final String mobileNumber = "+91" + etUsername.getText();
        final String password = String.valueOf(etPassword.getText());
        final String email = String.valueOf(etEmail.getText());
        Call<LoginData> call = apiServices.getLogin(mobileNumber, email, password);
        call.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(@NonNull Call<LoginData> call, @NonNull Response<LoginData> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (!String.valueOf(response.body().getKey()).equals("")) {
                        Log.d("Login :", response.body().toJSONString());
                        userPreferences.setUsername(mobileNumber);
                        userPreferences.setPassword(password);
                        userPreferences.setEmail(email);
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        Objects.requireNonNull(getActivity()).finish();
                        Log.d("Login : ", "Username: " + userPreferences.getUsername() + ",Email: " + userPreferences.getEmail());
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<LoginData> call, @NonNull Throwable t) {
                Log.d("Login :", t.toString());

            }
        });

    }

}
