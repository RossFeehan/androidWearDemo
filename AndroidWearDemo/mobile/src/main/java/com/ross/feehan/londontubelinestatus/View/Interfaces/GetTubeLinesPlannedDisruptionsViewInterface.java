package com.ross.feehan.londontubelinestatus.View.Interfaces;

import com.ross.feehan.londontubelinestatus.Data.Objects.TubeLinePlannedWork;
import com.ross.feehan.londontubelinestatus.Utils.Interfaces.NetworkErrorsInterface;

import java.util.List;

/**
 * Created by Ross Feehan on 07/01/2016.
 * Copyright Ross Feehan
 */
public interface GetTubeLinesPlannedDisruptionsViewInterface extends NetworkErrorsInterface{

    public void receiveTubeLinePlannedDisruptions(List<TubeLinePlannedWork> tubeLinePlannedWorks);
}
