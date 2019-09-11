package com.technocracy.app.aavartan.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

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

        viewPager = view.findViewById(R.id.viewPager);

    }

    private void setUpViewPager() {

//        Log.d("LOG ViewPager",eventsDataList.toString());
        EventsAdapter eventsAdapter = new EventsAdapter(eventsDataList, this.getActivity());
        viewPager.setAdapter(eventsAdapter);

    }

    private void apiCall() {
        APIServices apiServices = AppClient.getInstance().createService(APIServices.class);
        Call<List<EventsData>> call = apiServices.getEvents();
        call.enqueue(new Callback<List<EventsData>>() {
            @Override
            public void onResponse(@NonNull Call<List<EventsData>> call, @NonNull Response<List<EventsData>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        eventsDataList = response.body();
                        for (int i=0;i<response.body().size();i++){
                            String category = response.body().get(i).getThumbnail_img().substring(35,response.body().get(i).getThumbnail_img().lastIndexOf('/'));
//                            Log.d("LOG Category" , response.body().get(i).getTitle() + "  " + category);
                            eventsDataList.get(i).setCategory(category);
                        }
                        setUpViewPager();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<EventsData>> call, @NonNull Throwable t) {
                Log.d("LOG APICall ",t.toString());
            }
        });
    }

}





