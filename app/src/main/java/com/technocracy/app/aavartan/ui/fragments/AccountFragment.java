package com.technocracy.app.aavartan.ui.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
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

    private Call<ResponseAPI> callIsNumberVerified;
    private Call<GetUserData> callUserID;
    private Call<SignupData> callUserDetails;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        initView(view);
        setListeners();
        if (SessionManager.getIsLoggedIn()) {
            setProgressDialog();
            apiCallUserID();
        } else {
            loggedIn.setVisibility(View.GONE);
            loggedOut.setVisibility(View.VISIBLE);
        }
        return view;
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
        callUserID = apiServices.getUserID("Token " + SessionManager.getUserToken());

        callUserID.enqueue(new Callback<GetUserData>() {
            @Override
            public void onResponse(@NonNull Call<GetUserData> call, @NonNull Response<GetUserData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    GetUserData getUserData = new GetUserData(response.body().getUserID(), response.body().getUsername());
                    SessionManager.setUserID(getUserData.getUserID());
                    apiCallUserDetails();
                } else {
                    progressDialog.dismiss();
                    if (response.code() == 401) {
                        Toasty.warning(Objects.requireNonNull(getActivity()), "Login Again! Session Expired", Toasty.LENGTH_LONG).show();
                        SessionManager.logout();
                        Intent intent = new Intent(getActivity(), AuthActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        Toasty.error(Objects.requireNonNull(getActivity()), "Cannot Fetch Data", Toasty.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetUserData> call, @NonNull Throwable t) {
//                Log.d("LOG User ID Fail", t.toString());
                if (!callUserID.isCanceled()) {
                    progressDialog.dismiss();
                    Snackbar.make(loggedIn, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).setAction("Try Again", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            setProgressDialog();
                            apiCallUserID();
                        }
                    }).show();
                }
            }
        });

    }

    private void apiCallUserDetails() {

        int userID = SessionManager.getUserID();
        APIServices apiServices = AppClient.getInstance().createService(APIServices.class);
        callUserDetails = apiServices.getUserByID(userID);

        callUserDetails.enqueue(new Callback<SignupData>() {
            @Override
            public void onResponse(@NonNull Call<SignupData> call, @NonNull Response<SignupData> response) {
                if (response.isSuccessful() && response.body() != null) {
//                    Log.d("LOG User by ID", response.body().toString());
                    SignupData signupData = response.body();
                    setUserData(signupData);
                } else {
                    Toasty.error(Objects.requireNonNull(getActivity()), "Cannot Fetch Data", Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SignupData> call, @NonNull Throwable t) {
//                Log.d("LOG User by ID Fail", t.toString());
                if (!callUserDetails.isCanceled()) {
                    progressDialog.dismiss();
                    Snackbar.make(loggedIn, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).setAction("Try Again", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            setProgressDialog();
                            apiCallUserDetails();
                        }
                    }).show();
                }
            }
        });

    }

    private void apiCallIsNumberVerified() {

        APIServices apiServices = AppClient.getInstance().createService(APIServices.class);
        callIsNumberVerified = apiServices.eventRegister(32, "Token " + SessionManager.getUserToken());

        callIsNumberVerified.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(@NonNull Call<ResponseAPI> call, @NonNull Response<ResponseAPI> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getMessage().equals(AppConstants.EVENT_REGISTER_SUCCESS) ||
                            response.body().getMessage().equals(AppConstants.EVENT_ALREADY_REGISTERED)) {
                        SessionManager.setIsNumberVerified(true);
                        ivIsNumberVerified.setImageDrawable(getResources().getDrawable(R.drawable.acccount_number_verified));
                        tvIsNumberVerified.setVisibility(View.GONE);
                        progressDialog.dismiss();
                    } else {
                        Toasty.error(Objects.requireNonNull(getActivity()), "Cannot Fetch Data", Toasty.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseAPI> call, @NonNull Throwable t) {
//                Log.d("LOG Verified Fail", t.toString());
                if (!callIsNumberVerified.isCanceled()) {
                    progressDialog.dismiss();
                    Snackbar.make(loggedIn, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).setAction("Try Again", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            setProgressDialog();
                            apiCallIsNumberVerified();
                        }
                    });
                }
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
        apiCallIsNumberVerified();
    }

    private void setProgressDialog() {
        progressDialog = new Dialog(Objects.requireNonNull(getContext()));
        progressDialog.setContentView(R.layout.dialog_progress_bar);
        TextView tvProgressMessage = progressDialog.findViewById(R.id.tvProgressMessage);
        tvProgressMessage.setText(getString(R.string.loading_details_please_wait));
        progressDialog.show();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (callUserID != null) {
            if (callUserID.isExecuted()) {
                callUserID.cancel();
            }
        }
        if (callUserDetails != null) {
            if (callUserDetails.isExecuted()) {
                callUserDetails.cancel();
            }
        }
        if (callIsNumberVerified != null) {
            if (callIsNumberVerified.isExecuted()) {
                callIsNumberVerified.cancel();
            }
        }
    }
}