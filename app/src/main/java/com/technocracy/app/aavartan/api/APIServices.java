package com.technocracy.app.aavartan.api;


import com.technocracy.app.aavartan.api.data_models.EventsData;
import com.technocracy.app.aavartan.api.data_models.EventsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIServices {

    @GET("events")
    Call<EventsList> getEvents();

    @GET("events/{id}")
    Call<EventsData> getEventWithID(@Path("id") Integer id);

}

