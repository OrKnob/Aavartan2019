package com.technocracy.app.aavartan.ui.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.APIServices;
import com.technocracy.app.aavartan.api.AppClient;
import com.technocracy.app.aavartan.api.data_models.ResponseAPI;
import com.technocracy.app.aavartan.ui.activities.MainActivity;
import com.technocracy.app.aavartan.utils.AppConstants;
import com.technocracy.app.aavartan.utils.OTPTextWatcher;
import com.technocracy.app.aavartan.utils.SessionManager;

import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OTPVerifyFragment extends Fragment {

    private Button buVerify;
    private EditText etOTPVerify1, etOTPVerify2, etOTPVerify3, etOTPVerify4, etOTPVerify5, etOTPVerify6;
    private Dialog progressDialog;
    private RelativeLayout layout;

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

        layout = view.findViewById(R.id.layout);
        etOTPVerify1 = view.findViewById(R.id.etOTPVerify1);
        etOTPVerify2 = view.findViewById(R.id.etOTPVerify2);
        etOTPVerify3 = view.findViewById(R.id.etOTPVerify3);
        etOTPVerify4 = view.findViewById(R.id.etOTPVerify4);
        etOTPVerify5 = view.findViewById(R.id.etOTPVerify5);
        etOTPVerify6 = view.findViewById(R.id.etOTPVerify6);
        buVerify = view.findViewById(R.id.buVerify);

    }

    private void setListeners(View view) {

        etOTPVerify1.addTextChangedListener(new OTPTextWatcher(view, etOTPVerify1));
        etOTPVerify2.addTextChangedListener(new OTPTextWatcher(view, etOTPVerify2));
        etOTPVerify3.addTextChangedListener(new OTPTextWatcher(view, etOTPVerify3));
        etOTPVerify4.addTextChangedListener(new OTPTextWatcher(view, etOTPVerify4));
        etOTPVerify5.addTextChangedListener(new OTPTextWatcher(view, etOTPVerify5));
        etOTPVerify6.addTextChangedListener(new OTPTextWatcher(view, etOTPVerify6));
        buVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setProgressDialog();
                String OTP = etOTPVerify1.getText().toString() +
                        etOTPVerify2.getText().toString() +
                        etOTPVerify3.getText().toString() +
                        etOTPVerify4.getText().toString() +
                        etOTPVerify5.getText().toString() +
                        etOTPVerify6.getText().toString();
                apiCall(OTP);
            }
        });

    }

    private void apiCall(final String OTP) {

        APIServices apiServices = AppClient.getInstance().createService(APIServices.class);
        Call<ResponseAPI> call = apiServices.verifyOTP(OTP, "Token " + SessionManager.getUserToken());

        call.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(@NonNull Call<ResponseAPI> call, @NonNull Response<ResponseAPI> response) {
                if (response.isSuccessful() && response.body() != null) {
//                    Log.d("LOG OTP Verify ", response.body().getMessage());
                    if (response.body().getMessage().equals(AppConstants.OTP_VERIFY_SUCCESS)) {
                        SessionManager.setIsNumberVerified(true);
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        progressDialog.dismiss();
                        startActivity(intent);
                        Toasty.success(Objects.requireNonNull(getActivity()), "OTP Verification Successful", Toasty.LENGTH_SHORT).show();
                        getActivity().finish();
                    } else {
                        progressDialog.dismiss();
                        Toasty.error(Objects.requireNonNull(getActivity()), "OTP Not Verified! Try Again", Toasty.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseAPI> call, @NonNull Throwable t) {
//                Log.d("LOG OTP Verify Fail", t.toString());
                progressDialog.dismiss();
                Snackbar.make(layout, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).setAction("Try Again", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        apiCall(OTP);
                    }
                }).show();
            }
        });

    }

    private void setProgressDialog() {
        progressDialog = new Dialog(Objects.requireNonNull(getContext()));
        progressDialog.setContentView(R.layout.dialog_progress_bar);
        TextView tvProgressMessage = progressDialog.findViewById(R.id.tvProgressMessage);
        tvProgressMessage.setText(getString(R.string.logging_in_please_wait));
        progressDialog.show();
    }

}
