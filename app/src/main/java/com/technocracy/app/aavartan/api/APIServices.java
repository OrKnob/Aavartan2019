package com.technocracy.app.aavartan.api;

import com.technocracy.app.aavartan.api.data_models.EventsData;
import com.technocracy.app.aavartan.api.data_models.EventsList;
import com.technocracy.app.aavartan.api.data_models.LoginData;
import com.technocracy.app.aavartan.api.data_models.SignupData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIServices {

    @GET("events/")
    Call<EventsList> getEvents();

    @GET("events/{id}/")
    Call<EventsData> getEventWithID(@Path("id") Integer id);

    @FormUrlEncoded
    @POST("user/")
    Call<SignupData> createUser(@Field("password") String password,
                                @Field("name") String name,
                                @Field("email") String email,
                                @Field("contact") String contact,
                                @Field("college") String college,
                                @Field("branch") String branch,
                                @Field("course") String course,
                                @Field("sem") int sem,
                                @Field("city") String city);

    @FormUrlEncoded
    @POST("user/rest-auth/login/")
    Call<LoginData> getLogin(@Field("username") String username,
                             @Field("email") String email,
                             @Field("password") String password);

}

