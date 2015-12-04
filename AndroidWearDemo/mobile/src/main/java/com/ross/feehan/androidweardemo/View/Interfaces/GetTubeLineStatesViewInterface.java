package com.ross.feehan.androidweardemo.View.Interfaces;

import com.ross.feehan.androidweardemo.Data.Objects.TubeLine;
import com.ross.feehan.androidweardemo.Utils.NetworkErrorsInterface;

import java.util.List;

/**
 * Created by Ross Feehan on 04/12/2015.
 * Copyright Ross Feehan
 */
public interface GetTubeLineStatesViewInterface extends NetworkErrorsInterface{

    public void receiveTubeLineStates(List<TubeLine> tubeLineStates);
}
