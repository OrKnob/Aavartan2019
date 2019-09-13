package com.technocracy.app.aavartan.api.data_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EventsData implements Serializable {

    public EventsData(String title, String venue, String poster_img, String thumbnail_img, Integer n_rounds, String description, String rules, String instructions) {
        this.title = title;
        this.venue = venue;
        this.poster_img = poster_img;
        this.thumbnail_img = thumbnail_img;
        this.n_rounds = n_rounds;
        this.description = description;
        this.rules = rules;
        this.instructions = instructions;
    }

    private String category;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("venue")
    @Expose
    private String venue;

    @SerializedName("poster_img")
    @Expose
    private String poster_img;

    @SerializedName("thumbnail_img")
    @Expose
    private String thumbnail_img;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

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

    public String getPoster_img() {
        return poster_img;
    }

    public void setPoster_img(String poster_img) {
        this.poster_img = poster_img;
    }

    public String getThumbnail_img() {
        return thumbnail_img;
    }

    public void setThumbnail_img(String thumbnail_img) {
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
