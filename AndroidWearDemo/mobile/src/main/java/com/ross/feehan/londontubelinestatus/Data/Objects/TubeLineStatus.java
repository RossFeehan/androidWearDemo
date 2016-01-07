package com.ross.feehan.londontubelinestatus.Data.Objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ross Feehan on 04/12/2015.
 * Copyright Ross Feehan
 */
public class TubeLineStatus implements Serializable {

    @SerializedName("statusSeverityDescription")private String tubeLineStatus;

    //CONSTRUCTOR
    public TubeLineStatus(String status){
        setTubeLineStatus(status);
    }

    //SETTERS
    public void setTubeLineStatus(String tubeLineStatus) {
        this.tubeLineStatus = tubeLineStatus;
    }

    //GETTERS
    public String getTubeLineStatus() {
        return tubeLineStatus;
    }
}
