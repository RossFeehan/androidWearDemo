package com.ross.feehan.londontubelinestatus.Data.Utils;

import com.ross.feehan.londontubelinestatus.Data.Implementations.GetTubeLineStatusImpl;
import com.ross.feehan.londontubelinestatus.Data.Implementations.GetTubeLinesPlannedDisruptionsImpl;
import com.ross.feehan.londontubelinestatus.Data.Interfaces.GetTubeLineStatusInterface;
import com.ross.feehan.londontubelinestatus.Data.Interfaces.GetTubeLinesPlannedDisruptionsInterface;
import com.ross.feehan.londontubelinestatus.Logic.Implementations.GetTubeLinesPlannedDisruptionsLogicImpl;
import com.ross.feehan.londontubelinestatus.Logic.Implementations.GetTubeStatusLogicImpl;
import com.ross.feehan.londontubelinestatus.Utils.AndroidWearDemoApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ross Feehan on 04/12/2015.
 * Copyright Ross Feehan
 */
@Module(injects = {AndroidWearDemoApplication.class, GetTubeStatusLogicImpl.class, GetTubeLinesPlannedDisruptionsLogicImpl.class},
        complete = false,
        library = true)
public class DataDIModules {

    private GetTubeLineStatusImpl getTubeLineStatusImpl;
    private GetTubeLinesPlannedDisruptionsImpl getPlannedDisruptions;

    @Provides @Singleton
    public GetTubeLineStatusInterface provideGetTubeLineStatusInterface(){
        return getTubeLineStatusImpl = new GetTubeLineStatusImpl();
    }

    @Provides @Singleton
    public GetTubeLinesPlannedDisruptionsInterface perovideGetTubeLinesPlannedDisruptionsInterface(){
        return getPlannedDisruptions = new GetTubeLinesPlannedDisruptionsImpl();
    }
}
