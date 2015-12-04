package com.ross.feehan.androidweardemo.Data.Objects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ross Feehan on 04/12/2015.
 * Copyright Ross Feehan
 */
public class TubeLine {

    @SerializedName("name") private String tubeName;
    @SerializedName("lineStatuses") private List<TubeLineStatus> tubeStatus;

    //CONSTRUCTOR
    public TubeLine(String tubeName, List<TubeLineStatus> status){
        setTubeName(tubeName);
        setTubeStatus(status);
    }

    //SETTERS
    public void setTubeName(String tubeName) {
        this.tubeName = tubeName;
    }

    public void setTubeStatus(List<TubeLineStatus> tubeStatus) {
        this.tubeStatus = tubeStatus;
    }

    //GETTERS
    public String getTubeName() {
        return tubeName;
    }

    public List<TubeLineStatus> getTubeStatus() {
        return tubeStatus;
    }
}
