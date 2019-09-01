package com.technocracy.app.aavartan.api.data_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EventsList implements Serializable {

    @SerializedName("")
    @Expose
    private List<EventsData> eventsDataList;

    public List<EventsData> getEventsDataList() {
        return eventsDataList;
    }

    public void setEventsDataList(List<EventsData> eventsDataList) {
        this.eventsDataList = eventsDataList;
    }

}
