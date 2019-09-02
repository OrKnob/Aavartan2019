package com.technocracy.app.aavartan.ui.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.APIServices;
import com.technocracy.app.aavartan.api.AppClient;
import com.technocracy.app.aavartan.api.data_models.EventsData;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailsActivity extends AppCompatActivity {

    Integer ID;
    private EventsData eventsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        ID = bundle.getInt("ID");
        initView();
        apiCall();
    }

    private void initView() {
        eventsData = new EventsData();
    }

    private void apiCall() {
        APIServices apiServices = AppClient.getInstance().createService(APIServices.class);
        Call<EventsData> call = apiServices.getEventWithID(ID);
        call.enqueue(new Callback<EventsData>() {
            @Override
            public void onResponse(@NonNull Call<EventsData> call, @NonNull Response<EventsData> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        eventsData.setTitle(response.body().getTitle());
                        eventsData.setVenue(response.body().getVenue());
                        eventsData.setPoster_img(response.body().getPoster_img());
                        eventsData.setThumbnail_img(response.body().getThumbnail_img());
                        eventsData.setN_rounds(response.body().getN_rounds());
                        eventsData.setDescription(response.body().getDescription());
                        eventsData.setRules(response.body().getRules());
                        eventsData.setInstructions(response.body().getInstructions());

                    }
                    else
                        Toasty.error(getApplicationContext(), "Cannot Fetch Data", Toasty.LENGTH_LONG).show();
                }
                else {
                    Toasty.error(getApplicationContext(), "No Internet Connection!", Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<EventsData> call, @NonNull Throwable t) {
                Log.d("onFailure", String.valueOf(t));
            }
        });
    }

}
