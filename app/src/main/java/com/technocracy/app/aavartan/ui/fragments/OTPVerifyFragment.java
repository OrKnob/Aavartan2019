package com.technocracy.app.aavartan.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.APIServices;
import com.technocracy.app.aavartan.api.AppClient;
import com.technocracy.app.aavartan.api.data_models.ResponseAPI;
import com.technocracy.app.aavartan.api.data_models.SignupData;
import com.technocracy.app.aavartan.utils.AppConstants;
import com.technocracy.app.aavartan.utils.OTPTextWatcher;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OTPVerifyFragment extends Fragment {

    private Button buVerify;
    private EditText etOTPVerify1, etOTPVerify2, etOTPVerify3, etOTPVerify4, etOTPVerify5, etOTPVerify6;

    private SignupData signupData;

    public OTPVerifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_otp_verify, container, false);
        initView(view);
        Bundle bundle = Objects.requireNonNull(getActivity()).getIntent().getExtras();
        assert bundle != null;
        signupData = (SignupData) bundle.get(AppConstants.OTP_INTENT_EXTRA);
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

        etOTPVerify1.addTextChangedListener(new OTPTextWatcher(view, etOTPVerify1));
        etOTPVerify2.addTextChangedListener(new OTPTextWatcher(view, etOTPVerify2));
        etOTPVerify3.addTextChangedListener(new OTPTextWatcher(view, etOTPVerify3));
        etOTPVerify4.addTextChangedListener(new OTPTextWatcher(view, etOTPVerify4));
        etOTPVerify5.addTextChangedListener(new OTPTextWatcher(view, etOTPVerify5));
        etOTPVerify6.addTextChangedListener(new OTPTextWatcher(view, etOTPVerify6));
        buVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String OTP = etOTPVerify1.getText().toString() +
                        etOTPVerify2.getText().toString() +
                        etOTPVerify3.getText().toString() +
                        etOTPVerify4.getText().toString() +
                        etOTPVerify5.getText().toString() +
                        etOTPVerify6.getText().toString();
                Log.d("LOG OTP", OTP);
                apiCall(OTP);
                /*Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                Objects.requireNonNull(getActivity()).finish();*/
            }
        });

    }

    private void apiCall(String OTP) {

        APIServices apiServices = AppClient.getInstance().createService(APIServices.class);
        Call<ResponseAPI> call = apiServices.verifyOTP(signupData.getContact(), OTP);

        call.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(@NonNull Call<ResponseAPI> call, @NonNull Response<ResponseAPI> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d("LOG OTP Verify : ", response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseAPI> call, @NonNull Throwable t) {
                Log.d("LOG OTP Verify Fail", t.toString());
            }
        });

    }

}
