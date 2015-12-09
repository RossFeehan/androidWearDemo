package com.ross.feehan.androidweardemo.Utils.Interfaces;

import com.ross.feehan.androidweardemo.Data.Objects.TubeLine;

import java.util.List;

/**
 * Created by Ross Feehan on 09/12/2015.
 * Copyright Ross Feehan
 */
public interface GetTubeLineStatusViewInterface extends NetworkErrorsInterface{

    public void receiveTubeLineStatus(List<TubeLine> tubeLineStatus);


}
