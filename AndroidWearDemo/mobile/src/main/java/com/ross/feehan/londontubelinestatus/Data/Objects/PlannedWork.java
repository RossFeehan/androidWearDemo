package com.ross.feehan.londontubelinestatus.Data.Objects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ross Feehan on 07/01/2016.
 * Copyright Ross Feehan
 */
public class PlannedWork {

    @SerializedName("description")private String plannedWorkDescription;
    @SerializedName("additionalInfo")private String plannedWorkAddInfo;

    //CONSTRUCTOR
    public PlannedWork(String description, String addInfo){
        setPlannedWorkDescription(description);
        setPlannedWorkAddInfo(addInfo);
    }

    //SETTERS
    public void setPlannedWorkDescription(String plannedWorkDescription) {
        this.plannedWorkDescription = plannedWorkDescription;
    }

    public void setPlannedWorkAddInfo(String plannedWorkAddInfo) {
        this.plannedWorkAddInfo = plannedWorkAddInfo;
    }

    //GETTERS
    public String getPlannedWorkDescription() {
        return plannedWorkDescription;
    }

    public String getPlannedWorkAddInfo() {
        return plannedWorkAddInfo;
    }
}