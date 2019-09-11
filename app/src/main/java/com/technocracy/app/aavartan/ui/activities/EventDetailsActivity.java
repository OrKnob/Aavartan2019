package com.technocracy.app.aavartan.ui.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.data_models.EventsData;
import com.technocracy.app.aavartan.utils.AppConstants;

import es.dmoral.toasty.Toasty;

public class EventDetailsActivity extends AppCompatActivity {

    private Button buEventRegister;
    ImageView ivPoster;
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

        ivPoster = findViewById(R.id.ivPoster);
        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        tvVenue = findViewById(R.id.tvVenue);
        tvRounds = findViewById(R.id.tvRounds);
        tvRules = findViewById(R.id.tvRules);
        tvInstructions = findViewById(R.id.tvInstructions);

        buEventRegister = findViewById(R.id.buEventRegister);

    }

    private void setData(){
        Glide.with(EventDetailsActivity.this).load(eventsData.getPoster_img()).into(ivPoster);
        tvTitle.setText(eventsData.getTitle());
        tvDescription.setText(eventsData.getDescription());
        tvVenue.setText(eventsData.getVenue());
        tvRounds.setText(String.valueOf(eventsData.getN_rounds()));
        tvRules.setText(eventsData.getRules());
        tvInstructions.setText(eventsData.getInstructions());
    }

    private void setListeners(){

        buEventRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toasty.success(EventDetailsActivity.this,"Registered",Toasty.LENGTH_LONG).show();
            }
        });

    }

}
