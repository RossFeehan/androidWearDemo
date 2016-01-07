package com.ross.feehan.londontubelinestatus.Data.Objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ross Feehan on 07/01/2016.
 * Copyright Ross Feehan
 */
public class Disruptions implements Serializable{

    @SerializedName("disruption")private PlannedWork plannedWork;

    //CONSTRUCTOR
    public Disruptions(PlannedWork plannedWork){
        setPlannedWork(plannedWork);
    }

    //SETTERS
    public void setPlannedWork(PlannedWork plannedWork) {
        this.plannedWork = plannedWork;
    }

    //GETTERS
    public PlannedWork getPlannedWork() {
        return plannedWork;
    }
}