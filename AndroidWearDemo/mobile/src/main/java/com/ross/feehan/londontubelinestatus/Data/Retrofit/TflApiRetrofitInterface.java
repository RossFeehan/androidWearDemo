package com.ross.feehan.londontubelinestatus.Data.Retrofit;

import com.ross.feehan.londontubelinestatus.Data.Objects.TubeLine;
import com.ross.feehan.londontubelinestatus.Data.Objects.TubeLinePlannedWork;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Ross Feehan on 04/12/2015.
 * Copyright Ross Feehan
 */
public interface TflApiRetrofitInterface {

    @GET("/line/mode/tube/status")
    public void getTubeLineStatus(Callback<List<TubeLine>> callback);

    @GET("/line/mode/tube/status?detail=true")
    public void getTubeLinePlannedDisruptions(@Query("startDate") String startDate, @Query("endDate") String endDate, Callback<List<TubeLinePlannedWork>> callback);
}
