package com.ross.feehan.androidweardemo.Utils;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;
import com.ross.feehan.androidweardemo.Data.Objects.TubeLine;
import com.ross.feehan.androidweardemo.Logic.Interfaces.GetTubeStatusLogicInterface;
import com.ross.feehan.androidweardemo.Utils.Interfaces.GetTubeLineStatusViewInterface;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Ross Feehan on 09/12/2015.
 * Copyright Ross Feehan
 */
public class ListenerServiceForWear extends WearableListenerService implements GetTubeLineStatusViewInterface,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String GET_TUBE_SERVICE_STATUS_KEY = "GetTubeServiceStatusKey";
    @Inject GetTubeStatusLogicInterface getTubeLineStatus;
    private GoogleApiClient googleApiClient;

    //CLASS METHODS
    private void createGoogleApiClient(){
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    //INTERFACE METHODS
    //WearableListenerService INTERFACE METHODS
    @Override
    public void onMessageReceived(MessageEvent messageEvent){
        if(messageEvent.getPath().equals(GET_TUBE_SERVICE_STATUS_KEY)){
            Log.i("ListenerServiceForWear", "Received Message");

            createGoogleApiClient();
            googleApiClient.connect();
            if(googleApiClient != null){
                Log.i("ListenerServiceForWear", "Google API Client is not null");
            }
            else{
                Log.i("ListenerServiceForWear", "Google API Client IS null");
            }

            //FOR DI
            ((AndroidWearDemoApplication)getApplication()).getObjectGraph().inject(this);

            getTubeLineStatus.getTubeLineStatus(this);
        }
    }
    //GetTubeLineStatusViewInterface INTERFACE METHODS
    @Override
    public void receiveTubeLineStatus(List<TubeLine> tubeLineStatus) {
        Log.i("ListenerServiceForWear", "Received the tube line status: " + String.valueOf(tubeLineStatus.size()));
        //TODO SEND THE DATA BACK TO THE WEAR DEVICE
        //TODO DISCONNECT FROM THE GOOGLE API CLIENT HERE AS WELL
    }

    @Override
    public void somethingWentWrong(String message) {
        //TODO NOTIFY THE WEAR DEVICE SOMETHING WENT WRONG
    }

    @Override
    public void noInternetConnection() {
        //TODO NOTIFY THE WEAR DEVICE THERE IS NO INTERNET ACCESS AT THE MOMENT
    }

    //GOOGLE API CLIENT INTERFACE METHODS
    @Override
    public void onConnected(Bundle bundle) {
        Log.i("ListenerServiceForWear", "onConnected() (GoogleApiClient)");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("ListenerServiceForWear", "onConnectionFailed() (GoogleApiClient)");
    }
}
