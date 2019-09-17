package com.technocracy.app.aavartan.ui.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.APIServices;
import com.technocracy.app.aavartan.api.AppClient;
import com.technocracy.app.aavartan.api.data_models.LoginData;
import com.technocracy.app.aavartan.ui.activities.ForgotPasswordActivity;
import com.technocracy.app.aavartan.ui.activities.MainActivity;
import com.technocracy.app.aavartan.utils.AppConstants;
import com.technocracy.app.aavartan.utils.SessionManager;
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
    private Dialog progressDialog;
    private TextView tvForgotPassword;
    private RelativeLayout layout;

    private boolean isValidUserName = false, isValidEmail = false, isValidPassword = false;
    private String email, password, username;

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

        layout = view.findViewById(R.id.layout);
        etUsername = view.findViewById(R.id.etUsername);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        tvForgotPassword = view.findViewById(R.id.tvForgotPassword);
        buLogin = view.findViewById(R.id.buLogin);

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
                } else if (ValidationManager.isValidMobileNumber(etUsername.getText().toString())) {
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

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ForgotPasswordActivity.class);
                if (isValidEmail) {
                    intent.putExtra(AppConstants.FORGOT_PASSWORD_INTENT_EXTRA, Objects.requireNonNull(etEmail.getText()).toString());
                }
                startActivity(intent);
            }
        });

        buLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidUserName && isValidEmail && isValidPassword) {
                    username = String.valueOf(etUsername.getText());
                    email = String.valueOf(etEmail.getText());
                    password = String.valueOf(etPassword.getText());
                    setProgressDialog();
                    SessionManager.setIsLoggedIn(true);
                    apiCall();
                } else {
                    Toasty.error(Objects.requireNonNull(getActivity()), "One or more Fields are Incorrect", Toasty.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void apiCall() {

        APIServices apiServices = AppClient.getInstance().createService(APIServices.class);
        Call<LoginData> call = apiServices.getLoginToken(username, email, password);

        call.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(@NonNull Call<LoginData> call, @NonNull Response<LoginData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (!response.body().getUserToken().equals("")) {
//                        Log.d("LOG Login Key", response.body().getUserToken());
                        SessionManager.setIsLoggedIn(true);
                        SessionManager.setUserToken(response.body().getUserToken());
                        SessionManager.setUserName(username);
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        Toasty.success(Objects.requireNonNull(getActivity()), "Logging In", Toasty.LENGTH_LONG).show();
                        progressDialog.dismiss();
                        startActivity(intent);
                        Objects.requireNonNull(getActivity()).finish();
                    } else {
                        Toasty.error(Objects.requireNonNull(getActivity()), "Cannot Fetch Data", Toasty.LENGTH_LONG).show();
                    }
                }
                else {
                    if (response.code() == 400){
                        progressDialog.dismiss();
                        Toasty.error(Objects.requireNonNull(getActivity()),"Incorrect Credentials",Toasty.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginData> call, @NonNull Throwable t) {
//                Log.d("LOG Login Fail", t.toString());
                Snackbar.make(layout, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).setAction("Try Again", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        apiCall();
                    }
                }).show();
            }
        });
    }

    private void setProgressDialog() {
        progressDialog = new Dialog(Objects.requireNonNull(getContext()));
        progressDialog.setContentView(R.layout.dialog_progress_bar);
        ProgressBar progressBar = progressDialog.findViewById(R.id.progressBar);
        progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        TextView tvProgressMessage = progressDialog.findViewById(R.id.tvProgressMessage);
        tvProgressMessage.setText(getString(R.string.logging_in_please_wait));
        progressDialog.show();
    }

}
