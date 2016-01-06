package com.ross.feehan.androidweardemo.Data.Retrofit;

import com.ross.feehan.androidweardemo.Data.Objects.TubeLine;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Ross Feehan on 04/12/2015.
 * Copyright Ross Feehan
 */
public interface TflApiRetrofitInterface {

    @GET("/line/mode/tube/status")
    public void getTubeLineStatus(Callback<List<TubeLine>> callback);

    @GET("/line/{ids}/disruption")
    public void getTubeLinePlannedDisruptions(@Path("ids") String tubeLineID);
}
