package com.technocracy.app.aavartan.ui.activities;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.APIServices;
import com.technocracy.app.aavartan.api.AppClient;
import com.technocracy.app.aavartan.api.data_models.EventsData;
import com.technocracy.app.aavartan.api.data_models.ResponseAPI;
import com.technocracy.app.aavartan.utils.AppConstants;
import com.technocracy.app.aavartan.utils.SessionManager;

import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailsActivity extends AppCompatActivity {

    private Button buEventRegister;
    private ImageView ivPoster;
    private RelativeLayout layout;
    private Dialog progressDialog;
    private TextView tvTitle, tvDescription, tvVenue, tvRounds, tvRules, tvInstructions;

    private EventsData eventsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        eventsData = (EventsData) bundle.get(AppConstants.EVENT_INTENT_EXTRA);
//        Log.d("LOG Event Details Data", Objects.requireNonNull(eventsData).toString());
        initView();
        setData();
        setListeners();
    }

    private void initView() {

        layout = findViewById(R.id.layout);
        ivPoster = findViewById(R.id.ivPoster);
        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        tvVenue = findViewById(R.id.tvVenue);
        tvRounds = findViewById(R.id.tvRounds);
        tvRules = findViewById(R.id.tvRules);
        tvInstructions = findViewById(R.id.tvInstructions);

        buEventRegister = findViewById(R.id.buEventRegister);

    }

    private void setData() {
        Glide.with(EventDetailsActivity.this).load(eventsData.getPoster_img()).into(ivPoster);
        tvTitle.setText(eventsData.getTitle());
        tvDescription.setText(eventsData.getDescription());
        tvVenue.setText(eventsData.getVenue());
        tvRounds.setText(String.valueOf(eventsData.getN_rounds()));
        tvRules.setText(eventsData.getRules());
        tvInstructions.setText(eventsData.getInstructions());
    }

    private void setListeners() {

        buEventRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SessionManager.getIsNumberVerified()){
                    setProgressDialog();
                    apiCall();
                }
                else {
                    Toasty.error(EventDetailsActivity.this, "Verify Mobile Number First", Toasty.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void apiCall() {

        APIServices apiServices = AppClient.getInstance().createService(APIServices.class);
        Call<ResponseAPI> call = apiServices.eventRegister(eventsData.getId(), "Token " + SessionManager.getUserToken());

        call.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(@NonNull Call<ResponseAPI> call, @NonNull Response<ResponseAPI> response) {
                if (response.isSuccessful() && response.body() != null) {
                    switch (response.body().getMessage()) {
                        case AppConstants.EVENT_REGISTER_SUCCESS:
                            Toasty.success(EventDetailsActivity.this, "Registered", Toasty.LENGTH_LONG).show();
                            break;
                        case AppConstants.EVENT_REGISTER_VERIFY:
                            Toasty.warning(EventDetailsActivity.this, "Verify Mobile Number First", Toasty.LENGTH_LONG).show();
                            break;
                        case AppConstants.EVENT_ALREADY_REGISTERED:
                            Toasty.success(EventDetailsActivity.this, "You are already registered", Toasty.LENGTH_LONG).show();
                            break;
                        default:
                            Toasty.error(EventDetailsActivity.this, "Cannot Register", Toasty.LENGTH_LONG).show();
                            break;
                    }
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseAPI> call, @NonNull Throwable t) {
//                Log.d("LOG Register Fail", t.toString());
                progressDialog.dismiss();
                Snackbar.make(layout, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).show();
            }
        });
    }

    private void setProgressDialog() {
        progressDialog = new Dialog(Objects.requireNonNull(EventDetailsActivity.this));
        progressDialog.setContentView(R.layout.dialog_progress_bar);
        TextView tvProgressMessage = progressDialog.findViewById(R.id.tvProgressMessage);
        tvProgressMessage.setText(getString(R.string.registering_please_wait));
        progressDialog.show();
    }

}
