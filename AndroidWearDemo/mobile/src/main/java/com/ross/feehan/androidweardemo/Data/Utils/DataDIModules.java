package com.ross.feehan.androidweardemo.Data.Utils;

import com.ross.feehan.androidweardemo.Data.Implementations.GetTubeLineStatusImpl;
import com.ross.feehan.androidweardemo.Data.Interfaces.GetTubeLineStatusInterface;
import com.ross.feehan.androidweardemo.Logic.Implementations.GetTubeStatusLogicImpl;
import com.ross.feehan.androidweardemo.Utils.AndroidWearDemoApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ross Feehan on 04/12/2015.
 * Copyright Ross Feehan
 */
@Module(injects = {AndroidWearDemoApplication.class, GetTubeStatusLogicImpl.class},
        complete = false,
        library = true)
public class DataDIModules {

    private GetTubeLineStatusImpl getTubeLineStatusImpl;

    @Provides @Singleton
    public GetTubeLineStatusInterface provideGetTubeLineStatusInterface(){
        return getTubeLineStatusImpl = new GetTubeLineStatusImpl();
    }
}
