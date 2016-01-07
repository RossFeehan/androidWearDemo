package com.ross.feehan.londontubelinestatus.Logic.Interfaces;

import com.ross.feehan.londontubelinestatus.Data.Objects.TubeLinePlannedWork;
import com.ross.feehan.londontubelinestatus.Utils.Interfaces.NetworkErrorsInterface;
import com.ross.feehan.londontubelinestatus.View.Interfaces.GetTubeLinesPlannedDisruptionsViewInterface;

import java.util.List;

/**
 * Created by Ross Feehan on 07/01/2016.
 * Copyright Ross Feehan
 */
public interface GetTubeLinesPlannedDisruptionsLogicInterface extends NetworkErrorsInterface{

    public void getTubeLinesPlannedDisruptions(GetTubeLinesPlannedDisruptionsViewInterface listenerClass);

    public void receiveTubeLinesPlannedDisruptions(List<TubeLinePlannedWork> tubeLineStates);
}
