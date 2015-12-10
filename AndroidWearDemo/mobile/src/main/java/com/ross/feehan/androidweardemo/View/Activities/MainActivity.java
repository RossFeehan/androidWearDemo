package com.ross.feehan.androidweardemo.View.Activities;

import com.ross.feehan.androidweardemo.Data.Objects.TubeLine;
import com.ross.feehan.androidweardemo.Utils.Interfaces.GetTubeLineStatusViewInterface;

import java.util.List;

/**
 * Created by Ross Feehan on 10/12/2015.
 * Copyright Ross Feehan
 */
public class MainActivity implements GetTubeLineStatusViewInterface{

    //INTERFACE METHODS
    //GetTubeLineStatusViewInterface interface methods
    @Override
    public void receiveTubeLineStatus(List<TubeLine> tubeLineStatus) {

    }

    @Override
    public void somethingWentWrong(String message) {

    }

    @Override
    public void noInternetConnection() {

    }
}
