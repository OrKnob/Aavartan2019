package com.technocracy.app.aavartan.ui.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.data_models.EventsData;
import com.technocracy.app.aavartan.utils.AppConstants;

import java.util.Objects;

public class EventDetailsActivity extends AppCompatActivity {

    private EventsData eventsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        eventsData = (EventsData) bundle.get(AppConstants.EVENT_EXTRA);
        Log.d("LOG Event Details Data", Objects.requireNonNull(eventsData).toString());

        initView();
    }

    private void initView() {
    }


}
