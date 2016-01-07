package com.ross.feehan.londontubelinestatus.Data.Objects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ross Feehan on 07/01/2016.
 * Copyright Ross Feehan
 */
public class TubeLinePlannedWork {

    @SerializedName("id") private String tubeLineID;
    @SerializedName("lineStatuses") private List<Disruptions> disruptions;

    //CONSTRUCTOR
    public TubeLinePlannedWork(String tubeLineID, List<Disruptions> disruptions){
        setTubeLineID(tubeLineID);
        setDisruptions(disruptions);
    }

    //SETTERS
    public void setTubeLineID(String tubeLineID) {
        this.tubeLineID = tubeLineID;
    }

    public void setDisruptions(List<Disruptions> disruptions) {
        this.disruptions = disruptions;
    }

    //GETTERS
    public String getTubeLineID() {
        return tubeLineID;
    }

    public List<Disruptions> getDisruptions() {
        return disruptions;
    }
}