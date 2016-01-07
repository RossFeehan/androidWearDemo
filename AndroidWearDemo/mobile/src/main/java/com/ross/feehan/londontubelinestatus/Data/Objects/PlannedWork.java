package com.ross.feehan.londontubelinestatus.Data.Objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ross Feehan on 07/01/2016.
 * Copyright Ross Feehan
 */
public class PlannedWork implements Serializable {

    @SerializedName("categoryDescription") String plannedWorkHeader;
    @SerializedName("description")private String plannedWorkDescription;
    @SerializedName("additionalInfo")private String plannedWorkAddInfo;

    //CONSTRUCTOR
    public PlannedWork(String header, String description, String addInfo){
        setPlannedWorkHeader(header);
        setPlannedWorkDescription(description);
        setPlannedWorkAddInfo(addInfo);
    }

    //SETTERS
    public void setPlannedWorkHeader(String header){ this.plannedWorkHeader = header; }

    public void setPlannedWorkDescription(String plannedWorkDescription) {
        this.plannedWorkDescription = plannedWorkDescription;
    }

    public void setPlannedWorkAddInfo(String plannedWorkAddInfo) {
        this.plannedWorkAddInfo = plannedWorkAddInfo;
    }

    //GETTERS
    public String getPlannedWorkHeader() { return plannedWorkHeader; }

    public String getPlannedWorkDescription() {
        return plannedWorkDescription;
    }

    public String getPlannedWorkAddInfo() {
        return plannedWorkAddInfo;
    }
}