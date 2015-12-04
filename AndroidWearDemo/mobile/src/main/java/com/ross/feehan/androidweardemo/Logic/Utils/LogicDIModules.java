package com.ross.feehan.androidweardemo.Logic.Utils;

import com.ross.feehan.androidweardemo.Data.Interfaces.GetTubeLineStatusInterface;
import com.ross.feehan.androidweardemo.Logic.Interfaces.GetTubeStatusLogicInterface;
import com.ross.feehan.androidweardemo.Logic.Implementations.GetTubeStatusLogicImpl;
import com.ross.feehan.androidweardemo.View.Activities.MainActivity;
import com.ross.feehan.androidweardemo.View.Utils.AndroidWearDemoApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ross Feehan on 04/12/2015.
 * Copyright Ross Feehan
 */

@Module(injects = {AndroidWearDemoApplication.class, MainActivity.class},
        complete = false,
        library = true)
public class LogicDIModules {

    private GetTubeStatusLogicImpl getTubeStatusLogic;

    @Provides @Singleton
    public GetTubeStatusLogicInterface provideGetTubeStatusLogicImpl(GetTubeLineStatusInterface getTubeLineStatusInterface){
        return getTubeStatusLogic = new GetTubeStatusLogicImpl(getTubeLineStatusInterface);
    }
}
