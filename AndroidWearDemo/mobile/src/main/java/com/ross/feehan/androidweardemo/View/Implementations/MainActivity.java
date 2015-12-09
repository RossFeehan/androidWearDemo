package com.ross.feehan.androidweardemo.View.Implementations;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Wearable;
import com.ross.feehan.androidweardemo.Data.Objects.TubeLine;
import com.ross.feehan.androidweardemo.Logic.Interfaces.GetTubeStatusLogicInterface;
import com.ross.feehan.androidweardemo.Utils.Interfaces.GetTubeLineStatusViewInterface;
import com.ross.feehan.androidweardemo.Utils.AndroidWearDemoApplication;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Ross Feehan on 09/12/2015.
 * Copyright Ross Feehan
 */
public class MainActivity extends Activity implements GetTubeLineStatusViewInterface, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient googleApiClient;
    private static final String GET_TUBE_SERVICE_STATUS_KEY = "GetTubeServiceStatusKey";

    @Inject protected GetTubeStatusLogicInterface getTubeStatusLogicInterface;

    //CLASS METHODS
    private void createGoogleApiClient(){
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    //GOOGLE API CLIENT INTERFACE METHODS
    @Override
    public void onConnected(Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    //GetTubeLineStatusViewInterface INTERFACE METHODS
    @Override
    public void receiveTubeLineStatus(List<TubeLine> tubeLineStatus) {
        Toast.makeText(this, "Tube States received", Toast.LENGTH_SHORT).show();
        //TODO SEND DATA BACK TO WEAR DEIVCE
    }

    @Override
    public void somethingWentWrong(String message) {
        //TODO NOTIFY USER SOMETHING WENT WRONG
    }

    @Override
    public void noInternetConnection() {
        //NOTIFY USER THAT THERE IS NO INTERNET CONNECTION
    }

    //ACITIVTY LIFECYCLE METHODS
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Toast.makeText(this, "Activity started, getting tube states now", Toast.LENGTH_SHORT).show();

        //FOR DI
        ((AndroidWearDemoApplication)getApplication()).getObjectGraph().inject(this);

        getTubeStatusLogicInterface.getTubeLineStatus(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        createGoogleApiClient();
        googleApiClient.connect();
    }

    @Override
    protected void onStop(){
        super.onStop();
        googleApiClient.disconnect();
    }
}
