package com.technocracy.app.aavartan.ui.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    private TextInputEditText etEmail;

    private String email;
    private boolean isValidEmail = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initView();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            email = "" + bundle.get(AppConstants.FORGOT_PASSWORD_INTENT_EXTRA);
            etEmail.setText(email);
        }
        setListeners();
    }

    private void initView() {

        etEmail = findViewById(R.id.etEmail);
        buReset = findViewById(R.id.buReset);

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
                    Toasty.success(ForgotPasswordActivity.this,"E-mail sent",Toasty.LENGTH_LONG).show();
//                    Log.d("LOG Forgot Password :", response.body().getDetail());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseAPI> call, @NonNull Throwable t) {
                Toasty.error(ForgotPasswordActivity.this,"E-mail not sent",Toasty.LENGTH_SHORT).show();
                Log.d("LOG Forgot Fail :", t.toString());
            }
        });

    }

}
