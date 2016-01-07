package com.ross.feehan.londontubelinestatus.Logic.Implementations;

import com.ross.feehan.londontubelinestatus.Data.Interfaces.GetTubeLinesPlannedDisruptionsInterface;
import com.ross.feehan.londontubelinestatus.Data.Objects.TubeLinePlannedWork;
import com.ross.feehan.londontubelinestatus.Logic.Interfaces.GetTubeLinesPlannedDisruptionsLogicInterface;
import com.ross.feehan.londontubelinestatus.View.Interfaces.GetTubeLinesPlannedDisruptionsViewInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Ross Feehan on 07/01/2016.
 * Copyright Ross Feehan
 */
public class GetTubeLinesPlannedDisruptionsLogicImpl implements GetTubeLinesPlannedDisruptionsLogicInterface {

    private GetTubeLinesPlannedDisruptionsInterface getPlannedDisruptions;
    private GetTubeLinesPlannedDisruptionsViewInterface listenerClass;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //CONSTRUCTOR
    public GetTubeLinesPlannedDisruptionsLogicImpl(GetTubeLinesPlannedDisruptionsInterface getPlannedDisruptions){
        this.getPlannedDisruptions = getPlannedDisruptions;
    }

    //INTERFACE METHODS
    //GetTubeLinesPlannedDisruptionsLogicInterface interface methods
    @Override
    public void getTubeLinesPlannedDisruptions(GetTubeLinesPlannedDisruptionsViewInterface listenerClass) {
        this.listenerClass = listenerClass;
        getPlannedDisruptions.getPlannedDisruptions(this, getTodaysDate(), getNextWeeksDate());
    }

    private String getTodaysDate(){
        Calendar c = Calendar.getInstance();
        return dateFormat.format(c.getTime());
    }

    private String getNextWeeksDate(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, +7);
        return dateFormat.format(c.getTime());
    }

    @Override
    public void receiveTubeLinesPlannedDisruptions(List<TubeLinePlannedWork> tubeLineStates) {
        listenerClass.receiveTubeLinePlannedDisruptions(tubeLineStates);
    }

    @Override
    public void somethingWentWrong(String message) {
        listenerClass.somethingWentWrong(message);
    }

    @Override
    public void noInternetConnection() {
        listenerClass.noInternetConnection();
    }
}
