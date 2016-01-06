package com.ross.feehan.londontubelinestatus.View.Interfaces;

import com.ross.feehan.londontubelinestatus.Data.Objects.TubeLine;
import com.ross.feehan.londontubelinestatus.Utils.Interfaces.NetworkErrorsInterface;

import java.util.List;

/**
 * Created by Ross Feehan on 09/12/2015.
 * Copyright Ross Feehan
 */
public interface GetTubeLineStatusViewInterface extends NetworkErrorsInterface {

    public void receiveTubeLineStatus(List<TubeLine> tubeLineStatus);


}
