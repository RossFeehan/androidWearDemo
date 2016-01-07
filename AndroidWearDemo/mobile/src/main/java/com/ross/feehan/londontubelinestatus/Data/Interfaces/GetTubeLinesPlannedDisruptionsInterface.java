package com.ross.feehan.londontubelinestatus.Data.Interfaces;

import com.ross.feehan.londontubelinestatus.Logic.Interfaces.GetTubeLinesPlannedDisruptionsLogicInterface;

/**
 * Created by Ross Feehan on 07/01/2016.
 * Copyright Ross Feehan
 */
public interface GetTubeLinesPlannedDisruptionsInterface {

    public void getPlannedDisruptions(GetTubeLinesPlannedDisruptionsLogicInterface listenerClass, String startDate, String endDate);
}
