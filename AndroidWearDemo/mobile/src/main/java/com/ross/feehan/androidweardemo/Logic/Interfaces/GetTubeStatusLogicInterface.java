package com.ross.feehan.androidweardemo.Logic.Interfaces;

import com.ross.feehan.androidweardemo.Data.Objects.TubeLine;
import com.ross.feehan.androidweardemo.Utils.NetworkErrorsInterface;
import com.ross.feehan.androidweardemo.View.Interfaces.GetTubeLineStatesViewInterface;

import java.util.List;

/**
 * Created by Ross Feehan on 04/12/2015.
 * Copyright Ross Feehan
 */
public interface GetTubeStatusLogicInterface extends NetworkErrorsInterface{

    public void getTubeLineStatus(GetTubeLineStatesViewInterface listenerClass);

    public void receiveTubeLineStatus(List<TubeLine> tubeLineStates);

}