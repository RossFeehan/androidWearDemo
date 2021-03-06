package com.ross.feehan.londontubelinestatus.Logic.Interfaces;

import com.ross.feehan.londontubelinestatus.Data.Objects.TubeLine;
import com.ross.feehan.londontubelinestatus.Utils.Interfaces.NetworkErrorsInterface;
import com.ross.feehan.londontubelinestatus.View.Interfaces.GetTubeLineStatusViewInterface;

import java.util.List;

/**
 * Created by Ross Feehan on 04/12/2015.
 * Copyright Ross Feehan
 */
public interface GetTubeStatusLogicInterface extends NetworkErrorsInterface{

    public void getTubeLineStatus(GetTubeLineStatusViewInterface listenerClass);

    public void receiveTubeLineStatus(List<TubeLine> tubeLineStates);

}
