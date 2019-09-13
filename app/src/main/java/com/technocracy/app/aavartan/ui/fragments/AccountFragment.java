package com.technocracy.app.aavartan.ui.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.APIServices;
import com.technocracy.app.aavartan.api.AppClient;
import com.technocracy.app.aavartan.api.data_models.GetUserData;
import com.technocracy.app.aavartan.api.data_models.ResponseAPI;
import com.technocracy.app.aavartan.api.data_models.SignupData;
import com.technocracy.app.aavartan.ui.activities.AuthActivity;
import com.technocracy.app.aavartan.ui.activities.OTPVerifyActivity;
import com.technocracy.app.aavartan.utils.AppConstants;
import com.technocracy.app.aavartan.utils.SessionManager;

import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {

    private Button buLogin, buSignup, buLogout;
    private ImageView ivIsNumberVerified;
    private LinearLayout loggedIn, loggedOut;
    private Dialog progressDialog;
    private TextInputEditText etUsername, etFullName, etEmail, etCollege, etBranch, etCourse, etSemester, etCity;
    private TextView tvIsNumberVerified;

    private boolean isLoggedIn = false, isNumberVerified = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        isLoggedIn = SessionManager.getIsLoggedIn();
        Log.d("LOG isLoggedIn", String.valueOf(isLoggedIn));
        apiCallIsNumberVerified();
        isNumberVerified = SessionManager.getIsNumberVerified();
        initView(view);
        if (isLoggedIn) {
            setProgressDialog();
            apiCallUserID();
            setVerified(isNumberVerified);
        } else {
            loggedIn.setVisibility(View.GONE);
            loggedOut.setVisibility(View.VISIBLE);
        }
        setListeners();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        isLoggedIn = SessionManager.getIsLoggedIn();
        Log.d("LOG isLoggedIn", String.valueOf(isLoggedIn));
        apiCallIsNumberVerified();
        isNumberVerified = SessionManager.getIsNumberVerified();
        if (isLoggedIn) {
            apiCallUserID();
            setVerified(isNumberVerified);
        } else {
            loggedIn.setVisibility(View.GONE);
            loggedOut.setVisibility(View.VISIBLE);
        }
    }

    private void initView(View view) {

        loggedIn = view.findViewById(R.id.fragment_account_logged_in);
        loggedOut = view.findViewById(R.id.fragment_account_not_logged_in);
        etUsername = view.findViewById(R.id.etUsername);
        etFullName = view.findViewById(R.id.etFullName);
        etEmail = view.findViewById(R.id.etEmail);
        etCollege = view.findViewById(R.id.etCollege);
        etBranch = view.findViewById(R.id.etBranch);
        etCourse = view.findViewById(R.id.etCourse);
        etSemester = view.findViewById(R.id.etSemester);
        etCity = view.findViewById(R.id.etCity);
        buLogin = view.findViewById(R.id.buLogin);
        buSignup = view.findViewById(R.id.buSignup);
        buLogout = view.findViewById(R.id.buLogout);
        ivIsNumberVerified = view.findViewById(R.id.ivIsNumberVerified);
        tvIsNumberVerified = view.findViewById(R.id.tvIsNumberVerified);

    }

    private void setListeners() {

        buLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AuthActivity.class);
                intent.putExtra(AppConstants.AUTH_INTENT_EXTRA, AppConstants.AUTH_INTENT_LOGIN);
                startActivity(intent);
                Objects.requireNonNull(getActivity()).finish();
            }
        });

        buSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AuthActivity.class);
                intent.putExtra(AppConstants.AUTH_INTENT_EXTRA, AppConstants.AUTH_INTENT_SIGNUP);
                startActivity(intent);
                Objects.requireNonNull(getActivity()).finish();
            }
        });

        buLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManager.logout();
                Intent intent = new Intent(getActivity(), AuthActivity.class);
                intent.putExtra(AppConstants.AUTH_INTENT_EXTRA, AppConstants.AUTH_INTENT_LOGOUT);
                startActivity(intent);
                Toasty.success(Objects.requireNonNull(getActivity()), "Successfully Logged Out", Toasty.LENGTH_LONG).show();
                getActivity().finish();
            }
        });

        tvIsNumberVerified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OTPVerifyActivity.class);
                startActivity(intent);
            }
        });

    }

    private void apiCallUserID() {

        APIServices apiServices = AppClient.getInstance().createService(APIServices.class);
        Call<GetUserData> callUserID = apiServices.getUserID("Token " + SessionManager.getUserToken());

        callUserID.enqueue(new Callback<GetUserData>() {
            @Override
            public void onResponse(@NonNull Call<GetUserData> call, @NonNull Response<GetUserData> response) {
                if (response.isSuccessful() && response.body() != null) {
//                    Log.d("LOG User ID", response.body().getUsername() + " " + response.body().getUserID());
                    GetUserData getUserData = new GetUserData(response.body().getUserID(), response.body().getUsername());
                    SessionManager.setUserID(getUserData.getUserID());
                    apiCallUserDetails();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetUserData> call, @NonNull Throwable t) {
                Log.d("LOG User ID Fail", t.toString());
            }
        });

    }

    private void apiCallUserDetails() {

        int userID = SessionManager.getUserID();
        APIServices apiServices = AppClient.getInstance().createService(APIServices.class);
        Call<SignupData> callUserDetails = apiServices.getUserByID(userID);

        callUserDetails.enqueue(new Callback<SignupData>() {
            @Override
            public void onResponse(@NonNull Call<SignupData> call, @NonNull Response<SignupData> response) {
                if (response.isSuccessful() && response.body() != null) {
//                    Log.d("LOG User by ID", response.body().toString());
                    SignupData signupData = response.body();
                    setUserData(signupData);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SignupData> call, @NonNull Throwable t) {
                Log.d("LOG User by ID Fail", t.toString());
            }
        });

    }

    private void apiCallIsNumberVerified(){

        APIServices apiServices = AppClient.getInstance().createService(APIServices.class);
        Call<ResponseAPI> call = apiServices.eventRegister(32, "Token " + SessionManager.getUserToken());

        call.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(@NonNull Call<ResponseAPI> call, @NonNull Response<ResponseAPI> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SessionManager.setIsNumberVerified(response.body().getMessage().equals(AppConstants.EVENT_REGISTER_SUCCESS) ||
                            response.body().getMessage().equals(AppConstants.EVENT_ALREADY_REGISTERED));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseAPI> call, @NonNull Throwable t) {
                Log.d("LOG Register Fail", t.toString());
            }
        });

    }

    private void setUserData(SignupData signupData) {

        etUsername.setText(signupData.getContact());
        etFullName.setText(signupData.getName());
        etEmail.setText(signupData.getEmail());
        etCollege.setText(signupData.getCollege());
        etCity.setText(signupData.getCity());
        etCourse.setText(signupData.getCourse());
        etBranch.setText(signupData.getBranch());
        etSemester.setText(String.valueOf(signupData.getSem()));
        progressDialog.dismiss();
    }

    private void setVerified(boolean isNumberVerified) {
        if (isNumberVerified){
            ivIsNumberVerified.setImageDrawable(getResources().getDrawable(R.drawable.acccount_number_verified));
            tvIsNumberVerified.setVisibility(View.GONE);
        }
        else {
            ivIsNumberVerified.setImageDrawable(getResources().getDrawable(R.drawable.account_number_not_verified));
            tvIsNumberVerified.setVisibility(View.VISIBLE);
        }
    }

    private void setProgressDialog() {
        progressDialog = new Dialog(Objects.requireNonNull(getContext()));
        progressDialog.setContentView(R.layout.dialog_progress_bar);
        progressDialog.show();
    }

}