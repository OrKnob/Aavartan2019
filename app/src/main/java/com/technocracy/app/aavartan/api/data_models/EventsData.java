package com.technocracy.app.aavartan.api.data_models;

import android.net.Uri;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EventsData implements Serializable {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("venue")
    @Expose
    private String venue;

    @SerializedName("poster_img")
    @Expose
    private Uri poster_img;

    @SerializedName("thumbnail_img")
    @Expose
    private Uri thumbnail_img;

    @SerializedName("n_rounds")
    @Expose
    private Integer n_rounds;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("rules")
    @Expose
    private String rules;

    @SerializedName("instructions")
    @Expose
    private String instructions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Uri getPoster_img() {
        return poster_img;
    }

    public void setPoster_img(Uri poster_img) {
        this.poster_img = poster_img;
    }

    public Uri getThumbnail_img() {
        return thumbnail_img;
    }

    public void setThumbnail_img(Uri thumbnail_img) {
        this.thumbnail_img = thumbnail_img;
    }

    public Integer getN_rounds() {
        return n_rounds;
    }

    public void setN_rounds(Integer n_rounds) {
        this.n_rounds = n_rounds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
