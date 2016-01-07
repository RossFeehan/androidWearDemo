package com.ross.feehan.londontubelinestatus.Data.Objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ross Feehan on 04/12/2015.
 * Copyright Ross Feehan
 */
public class TubeLineStatus implements Serializable {

    @SerializedName("statusSeverityDescription")private String tubeLineStatus;
    @SerializedName("reason")private String statusReason;

    //CONSTRUCTOR
    public TubeLineStatus(String status, String statusReason){
        setTubeLineStatus(status);
        setStatusReason(statusReason);
    }

    //SETTERS
    public void setTubeLineStatus(String tubeLineStatus) {
        this.tubeLineStatus = tubeLineStatus;
    }

    public void setStatusReason(String reason){ this.statusReason = reason; }

    //GETTERS
    public String getTubeLineStatus() {
        return tubeLineStatus;
    }

    public String getStatusReason(){ return  statusReason; }
}
