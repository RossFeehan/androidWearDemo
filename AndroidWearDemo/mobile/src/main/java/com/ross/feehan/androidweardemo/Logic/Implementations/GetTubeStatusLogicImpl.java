package com.ross.feehan.androidweardemo.Logic.Implementations;

import com.ross.feehan.androidweardemo.Data.Interfaces.GetTubeLineStatusInterface;
import com.ross.feehan.androidweardemo.Data.Objects.TubeLine;
import com.ross.feehan.androidweardemo.Logic.Interfaces.GetTubeStatusLogicInterface;
import com.ross.feehan.androidweardemo.Utils.Interfaces.GetTubeLineStatusViewInterface;

import java.util.List;

/**
 * Created by Ross Feehan on 04/12/2015.
 * Copyright Ross Feehan
 */
public class GetTubeStatusLogicImpl implements GetTubeStatusLogicInterface{

    private GetTubeLineStatusInterface getTubeLineStatus;
    private GetTubeLineStatusViewInterface listenerClass;

    //Constructor
    public GetTubeStatusLogicImpl(GetTubeLineStatusInterface getTubeLineStatus){
        this.getTubeLineStatus = getTubeLineStatus;
    }

    //INTERFACE METHODS
    //GetTubeStatusLogicInterface METHODS
    @Override
    public void getTubeLineStatus(GetTubeLineStatusViewInterface listenerClass) {
        this.listenerClass = listenerClass;
        this.getTubeLineStatus.getTubeStatus(this);
    }

    @Override
    public void receiveTubeLineStatus(List<TubeLine> tubeLineStates) {
        listenerClass.receiveTubeLineStatus(tubeLineStates);
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