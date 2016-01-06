package com.ross.feehan.androidweardemo.Data.Objects;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.android.gms.wearable.DataMap;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ross Feehan on 04/12/2015.
 * Copyright Ross Feehan
 */
public class TubeLine {

    @SerializedName("name") private String tubeName;
    @SerializedName("id") private String tubeLineID;
    @SerializedName("lineStatuses") private List<TubeLineStatus> tubeStatus;
    private static final String TUBE_LINE_NAME_KEY = "TubeLineName";
    private static final String TUBE_LINE_STATUS_KEY = "TubeLineStatus";
    private static final String TUBE_LINE_STATUS_UPDATE_TIME_KEY = "UpdateTime";

    //CONSTRUCTOR
    public TubeLine(String tubeName, String tubeLineID, List<TubeLineStatus> status){
        setTubeName(tubeName);
        setTubeStatus(status);
    }

    //SETTERS
    public void setTubeName(String tubeName) {
        this.tubeName = tubeName;
    }

    public void setTubeLineID(String id){this.tubeLineID = id; }

    public void setTubeStatus(List<TubeLineStatus> tubeStatus) {
        this.tubeStatus = tubeStatus;
    }

    //GETTERS
    public String getTubeName() {
        return tubeName;
    }

    public String getTubeLineID(){ return tubeLineID; }

    public List<TubeLineStatus> getTubeStatus() {
        return tubeStatus;
    }

    /*Method to convert a List of type TubeLine to an ArrayList of type DataMap
     *Each DataMap will contain the TubeLine name and TubeLineStatus status along with a timestamp
     * The timestamp is added for sending the data to the wearable device as without this,
     * Google Api considers the data to be the same as sometimes the tube line status does not change
     */
    public static ArrayList<DataMap> convertToDataMap(List<TubeLine> tubeLineStatus){
        Log.i("TubeLine", "Converting to DataMap ArrayList");
        ArrayList<DataMap> tubeLinesDM = new ArrayList<DataMap>();

        for(TubeLine tubeLine : tubeLineStatus){
            DataMap dm = new DataMap();
            dm.putString(TUBE_LINE_NAME_KEY, tubeLine.getTubeName());
            dm.putString(TUBE_LINE_STATUS_KEY, tubeLine.getTubeStatus().get(0).getTubeLineStatus());
            dm.putLong(TUBE_LINE_STATUS_UPDATE_TIME_KEY, new Date().getTime());

            tubeLinesDM.add(dm);
        }

        return tubeLinesDM;
    }
}
