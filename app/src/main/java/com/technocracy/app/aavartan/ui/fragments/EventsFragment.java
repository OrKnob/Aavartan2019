package com.technocracy.app.aavartan.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.APIServices;
import com.technocracy.app.aavartan.api.AppClient;
import com.technocracy.app.aavartan.api.data_models.EventsData;
import com.technocracy.app.aavartan.ui.adapters.EventsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EventsFragment extends Fragment {

    private RelativeLayout layout;
    private ViewPager viewPager;
    private List<EventsData> eventsDataList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_events, container, false);
        initView(view);
        apiCall();
        return view;
    }

    private void initView(View view) {

        layout = view.findViewById(R.id.layout);
        viewPager = view.findViewById(R.id.viewPager);

    }

    private void setUpViewPager() {

        EventsAdapter eventsAdapter = new EventsAdapter(eventsDataList, this.getActivity());
        viewPager.setAdapter(eventsAdapter);

    }

    private void apiCall() {
        APIServices apiServices = AppClient.getInstance().createService(APIServices.class);
        Call<List<EventsData>> call = apiServices.getEvents();
        call.enqueue(new Callback<List<EventsData>>() {
            @Override
            public void onResponse(@NonNull Call<List<EventsData>> call, @NonNull Response<List<EventsData>> response) {
                if (response.isSuccessful() && response.body() != null) {
//                    Log.d("LOG Events", response.body().toString());
                    response.body().remove(27);
                    eventsDataList = response.body();
                    for (int i = 0; i < response.body().size(); i++) {
                        String category = response.body().get(i).getThumbnail_img().substring(35, response.body().get(i).getThumbnail_img().lastIndexOf('/'));
                        eventsDataList.get(i).setCategory(category);
                    }
                    setUpViewPager();

                }
            }

            @Override
            public void onFailure(@NonNull Call<List<EventsData>> call, @NonNull Throwable t) {
//                Log.d("LOG Events Fail ", t.toString());
                Snackbar.make(layout, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).setAction("Try Again", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        apiCall();
                    }
                }).show();
            }
        });
    }

}





