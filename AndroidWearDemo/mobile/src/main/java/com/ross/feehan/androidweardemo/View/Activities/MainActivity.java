package com.ross.feehan.androidweardemo.View.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ross.feehan.androidweardemo.Data.Objects.TubeLine;
import com.ross.feehan.androidweardemo.Logic.Interfaces.GetTubeStatusLogicInterface;
import com.ross.feehan.androidweardemo.R;
import com.ross.feehan.androidweardemo.View.Interfaces.GetTubeLineStatesViewInterface;
import com.ross.feehan.androidweardemo.View.Utils.AndroidWearDemoApplication;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements GetTubeLineStatesViewInterface{

    //DI
    @Inject GetTubeStatusLogicInterface getTubeStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FOR DEPENDENCY INJECTION
        ((AndroidWearDemoApplication)getApplication()).getObjectGraph().inject(this);

        getTubeStatus.getTubeLineStatus(this);
    }

    //INTERFACE METHODS
    //GetTubeLineStatesViewInterface METHODS
    @Override
    public void receiveTubeLineStates(List<TubeLine> tubeLineStates) {
        //TODO SEND THE TUBE LINES STATES TO THE WEAR DEVICE
    }

    @Override
    public void somethingWentWrong(String message) {
        //TODO HANDLE
    }

    @Override
    public void noInternetConnection() {
        //TODO HANDLE
    }
}
