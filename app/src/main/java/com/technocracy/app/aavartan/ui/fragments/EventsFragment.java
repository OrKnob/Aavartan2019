package com.technocracy.app.aavartan.ui.fragments;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
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
import com.technocracy.app.aavartan.api.data_models.EventsList;
import com.technocracy.app.aavartan.ui.adapters.EventsAdapter;
import com.technocracy.app.aavartan.ui.modals.EventsViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EventsFragment extends Fragment {

    private ViewPager viewPager;
    private EventsAdapter eventsAdapter;
    private List<EventsViewModel> models = new ArrayList<>();
    private Integer[] colors = null;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private List<EventsData> eventsDataList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_events, container, false);
        initView(view);
        mockModelsData();
        setUpViewPager();
        apiCall();
        return view;
    }

    private void initView(View view) {

        eventsAdapter = new EventsAdapter(models, this.getActivity());
        viewPager = view.findViewById(R.id.viewPager);

    }

    private void mockModelsData() {

        models.add(new EventsViewModel(R.drawable.icon_gallery, "Technical Events", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));
        models.add(new EventsViewModel(R.drawable.icon_contacts, "Fun Events", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side"));
        models.add(new EventsViewModel(R.drawable.icon_about_us, "Managerial Events", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface."));
        models.add(new EventsViewModel(R.drawable.icon_app_team, "Robotics", "Business cards are cards bearing business information about a company or individual."));

        //    Dummy

        colors = new Integer[]{
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };

    }

    private void setUpViewPager() {

        viewPager.setAdapter(eventsAdapter);
        viewPager.setPadding(130, 0, 130, 0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position < (eventsAdapter.getCount() - 1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                } else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void apiCall() {
        APIServices apiServices = AppClient.getInstance().createService(APIServices.class);
        Call<EventsList> call = apiServices.getEvents();
        call.enqueue(new Callback<EventsList>() {
            @Override
            public void onResponse(@NonNull Call<EventsList> call, @NonNull Response<EventsList> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        eventsDataList.addAll(response.body().getEventsDataList());

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<EventsList> call, @NonNull Throwable t) {

            }
        });
    }

}





