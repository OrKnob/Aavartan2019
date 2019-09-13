package com.technocracy.app.aavartan.ui.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.APIServices;
import com.technocracy.app.aavartan.api.AppClient;
import com.technocracy.app.aavartan.api.data_models.ResponseAPI;
import com.technocracy.app.aavartan.utils.AppConstants;
import com.technocracy.app.aavartan.utils.ValidationManager;

import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Button buReset;
    private LinearLayout layout;
    private TextInputEditText etEmail;
    private TextView tvEmailSent;

    private String email = "";
    private boolean isValidEmail = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initView();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            email += bundle.getString(AppConstants.FORGOT_PASSWORD_INTENT_EXTRA);
            if (ValidationManager.isEmailValid(email)) {
                isValidEmail = true;
                etEmail.setText(email);
            }
        }
        setListeners();
    }

    private void initView() {

        layout = findViewById(R.id.layout);
        etEmail = findViewById(R.id.etEmail);
        buReset = findViewById(R.id.buReset);
        tvEmailSent = findViewById(R.id.tvEmailSent);

    }

    private void setListeners() {

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

        buReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidEmail) {
                    email = Objects.requireNonNull(etEmail.getText()).toString();
                    tvEmailSent.setVisibility(View.VISIBLE);
                    apiCall();
                } else {
                    Toasty.error(ForgotPasswordActivity.this, "Email is Not Valid", Toasty.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void apiCall() {

        APIServices apiServices = AppClient.getInstance().createService(APIServices.class);
        Call<ResponseAPI> call = apiServices.resetPassword(email);

        call.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(@NonNull Call<ResponseAPI> call, @NonNull Response<ResponseAPI> response) {
                if (response.isSuccessful() && response.body() != null) {
//                    Log.d("LOG Forgot Password :", response.body().getDetail());
                    Snackbar.make(layout, "Email sent!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("RESEND", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    apiCall();
                                }
                            }).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseAPI> call, @NonNull Throwable t) {
                Toasty.error(ForgotPasswordActivity.this, "E-mail not sent", Toasty.LENGTH_SHORT).show();
                Snackbar.make(layout, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).setAction("Try Again", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        apiCall();
                    }
                }).show();
//                Log.d("LOG Forgot Fail :", t.toString());
            }
        });

    }

}
