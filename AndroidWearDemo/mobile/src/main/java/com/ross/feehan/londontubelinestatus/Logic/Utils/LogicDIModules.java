package com.ross.feehan.londontubelinestatus.Logic.Utils;

import com.ross.feehan.londontubelinestatus.Data.Interfaces.GetTubeLineStatusInterface;
import com.ross.feehan.londontubelinestatus.Data.Interfaces.GetTubeLinesPlannedDisruptionsInterface;
import com.ross.feehan.londontubelinestatus.Logic.Implementations.GetTubeLinesPlannedDisruptionsLogicImpl;
import com.ross.feehan.londontubelinestatus.Logic.Interfaces.GetTubeLinesPlannedDisruptionsLogicInterface;
import com.ross.feehan.londontubelinestatus.Logic.Interfaces.GetTubeStatusLogicInterface;
import com.ross.feehan.londontubelinestatus.Logic.Implementations.GetTubeStatusLogicImpl;
import com.ross.feehan.londontubelinestatus.Utils.AndroidWearDemoApplication;
import com.ross.feehan.londontubelinestatus.Utils.ListenerServiceForWear;
import com.ross.feehan.londontubelinestatus.View.Activities.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ross Feehan on 04/12/2015.
 * Copyright Ross Feehan
 */

@Module(injects = {AndroidWearDemoApplication.class, ListenerServiceForWear.class, MainActivity.class},
        complete = false,
        library = true)
public class LogicDIModules {

    private GetTubeStatusLogicImpl getTubeStatusLogic;
    private GetTubeLinesPlannedDisruptionsLogicImpl getPlannedDisruptionsLogic;

    @Provides @Singleton
    public GetTubeStatusLogicInterface provideGetTubeStatusLogicImpl(GetTubeLineStatusInterface getTubeLineStatusInterface){
        return getTubeStatusLogic = new GetTubeStatusLogicImpl(getTubeLineStatusInterface);
    }

    @Provides @Singleton
    public GetTubeLinesPlannedDisruptionsLogicInterface providesGetTubeLinesPlannedDisruptionsLogicInterface(GetTubeLinesPlannedDisruptionsInterface getPlannedDisruptionsInterface){
        return getPlannedDisruptionsLogic = new GetTubeLinesPlannedDisruptionsLogicImpl(getPlannedDisruptionsInterface);
    }
}
