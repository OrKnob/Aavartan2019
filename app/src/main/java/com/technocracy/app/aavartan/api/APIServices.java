package com.technocracy.app.aavartan.api;

import com.technocracy.app.aavartan.api.data_models.EventsData;
import com.technocracy.app.aavartan.api.data_models.GetUserData;
import com.technocracy.app.aavartan.api.data_models.LoginData;
import com.technocracy.app.aavartan.api.data_models.ResponseAPI;
import com.technocracy.app.aavartan.api.data_models.SignupData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIServices {

    //    Checked
    @GET("events/")
    Call<List<EventsData>> getEvents();

    //    Checked
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

    //    Checked
    @GET("user/get/")
    Call<GetUserData> getUserID(@Header("Authorization") String token);

    //    Checked
    @FormUrlEncoded
    @POST("user/rest-auth/login/")
    Call<LoginData> getLoginToken(@Field("username") String username,
                                  @Field("email") String email,
                                  @Field("password") String password);

    //    Checked
    @GET("user/{id}")
    Call<SignupData> getUserByID(@Path("id") int id);

    //    Checked
    @GET("user/verify/")
    Call<ResponseAPI> verifyOTP(@Query("otp") String otp, @Header("Authorization") String token);

    //    Checked
    @GET("user/register/")
    Call<ResponseAPI> eventRegister(@Query("event_id") String eventID,
                                    @Header("Authorization") String token);

    //    Checked
    @FormUrlEncoded
    @POST("user/rest-auth/password/reset/")
    Call<ResponseAPI> resetPassword(@Field("email") String email);

}

