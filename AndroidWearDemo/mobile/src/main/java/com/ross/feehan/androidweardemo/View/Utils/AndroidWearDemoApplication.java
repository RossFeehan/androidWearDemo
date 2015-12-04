package com.ross.feehan.androidweardemo.View.Utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.ross.feehan.androidweardemo.Data.Utils.DataDIModules;
import com.ross.feehan.androidweardemo.Logic.Utils.LogicDIModules;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by Ross Feehan on 04/12/2015.
 * Copyright Ross Feehan
 */
public class AndroidWearDemoApplication extends Application {

    private ObjectGraph objectGraph;
    private Context ctx;

    @Override
    public void onCreate(){
        super.onCreate();

        this.ctx = this;

        objectGraph = ObjectGraph.create(getModules().toArray());
        objectGraph.inject(this);
    }

    private List<Object> getModules(){
        return Arrays.<Object>asList(new DataDIModules(), new LogicDIModules());
    }

    public ObjectGraph getObjectGraph(){
        return objectGraph;
    }

    //APPLICATION LIFECYCLE METHODS
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
    }

    @Override
    public void onTerminate(){
        super.onTerminate();
    }
}
