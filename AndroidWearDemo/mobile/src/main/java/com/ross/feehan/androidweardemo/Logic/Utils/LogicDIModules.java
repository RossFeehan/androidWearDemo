package com.ross.feehan.androidweardemo.Logic.Utils;

import com.ross.feehan.androidweardemo.Data.Interfaces.GetTubeLineStatusInterface;
import com.ross.feehan.androidweardemo.Logic.Interfaces.GetTubeStatusLogicInterface;
import com.ross.feehan.androidweardemo.Logic.Implementations.GetTubeStatusLogicImpl;
import com.ross.feehan.androidweardemo.Utils.AndroidWearDemoApplication;
import com.ross.feehan.androidweardemo.Utils.ListenerServiceForWear;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ross Feehan on 04/12/2015.
 * Copyright Ross Feehan
 */

@Module(injects = {AndroidWearDemoApplication.class, ListenerServiceForWear.class},
        complete = false,
        library = true)
public class LogicDIModules {

    private GetTubeStatusLogicImpl getTubeStatusLogic;

    @Provides @Singleton
    public GetTubeStatusLogicInterface provideGetTubeStatusLogicImpl(GetTubeLineStatusInterface getTubeLineStatusInterface){
        return getTubeStatusLogic = new GetTubeStatusLogicImpl(getTubeLineStatusInterface);
    }
}
