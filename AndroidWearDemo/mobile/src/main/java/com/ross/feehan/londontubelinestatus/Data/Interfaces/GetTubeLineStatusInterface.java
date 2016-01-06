package com.ross.feehan.londontubelinestatus.Data.Interfaces;

import com.ross.feehan.londontubelinestatus.Logic.Interfaces.GetTubeStatusLogicInterface;

/**
 * Created by Ross Feehan on 04/12/2015.
 * Copyright Ross Feehan
 */
public interface GetTubeLineStatusInterface {

    public void getTubeStatus(GetTubeStatusLogicInterface listenerClass);
}
