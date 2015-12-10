package com.ross.feehan.androidweardemo.Utils;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;
import com.ross.feehan.androidweardemo.Data.Objects.TubeLine;
import com.ross.feehan.androidweardemo.Logic.Interfaces.GetTubeStatusLogicInterface;
import com.ross.feehan.androidweardemo.Utils.Interfaces.GetTubeLineStatusViewInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Ross Feehan on 09/12/2015.
 * Copyright Ross Feehan
 */
public class ListenerServiceForWear extends WearableListenerService implements GetTubeLineStatusViewInterface,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String CLASS_NAME_KEY = "ListenerServiceForWear";
    private static final String GET_TUBE_SERVICE_STATUS_KEY = "GetTubeServiceStatusKey";
    private static final String NO_INTERNET_ACCESS_KEY = "/NoInternetAccessKey";
    private static final String SOME_THING_WENT_WRONG_KEY = "/SomeThingWentWrongKey";
    private static final String TUBE_LINE_STATUS_KEY = "/TubeLineStatusKey";
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

    private void sendDataToWear(String dataKey, ArrayList<DataMap> tubeLineStatus){

        PutDataMapRequest putDataMapRequest = PutDataMapRequest.create(dataKey);
        putDataMapRequest.getDataMap().putDataMapArrayList(dataKey, tubeLineStatus);
        PutDataRequest putDataRequest = putDataMapRequest.asPutDataRequest();

        PendingResult<DataApi.DataItemResult> pendingResult = Wearable.DataApi.putDataItem(googleApiClient, putDataRequest);
        pendingResult.setResultCallback(new ResultCallback<DataApi.DataItemResult>() {
            @Override
            public void onResult(DataApi.DataItemResult dataItemResult) {
                if(dataItemResult.getStatus().isSuccess()){
                    Log.i(CLASS_NAME_KEY, "Data sent successfully");
                }
                else if(!dataItemResult.getStatus().isSuccess()){
                    Log.i(CLASS_NAME_KEY, "Data NOT sent successfully");
                }
            }
        });
        Log.i(CLASS_NAME_KEY, "Sending data to device");

        Log.i(CLASS_NAME_KEY, "Disconnecting from Google Api Client");
        googleApiClient.disconnect();
    }

    //INTERFACE METHODS
    //WearableListenerService INTERFACE METHODS
    @Override
    public void onMessageReceived(MessageEvent messageEvent){
        if(messageEvent.getPath().equals(GET_TUBE_SERVICE_STATUS_KEY)){
            Log.i(CLASS_NAME_KEY, "Received Message");

            createGoogleApiClient();
            googleApiClient.connect();
            if(googleApiClient != null){
                Log.i(CLASS_NAME_KEY, "Google API Client is not null");
            }
            else{
                Log.i(CLASS_NAME_KEY, "Google API Client IS null");
            }

            //FOR DI
            ((AndroidWearDemoApplication)getApplication()).getObjectGraph().inject(this);

            getTubeLineStatus.getTubeLineStatus(this);
        }
    }
    //GetTubeLineStatusViewInterface INTERFACE METHODS
    @Override
    public void receiveTubeLineStatus(List<TubeLine> tubeLineStatus) {
        Log.i(CLASS_NAME_KEY, "Received the tube line status: " + String.valueOf(tubeLineStatus.size()));

        ArrayList<DataMap> dataMapList = TubeLine.convertToDataMap(tubeLineStatus);
        sendDataToWear(TUBE_LINE_STATUS_KEY, dataMapList);
    }

    @Override
    public void somethingWentWrong(String message) {
        Log.i(CLASS_NAME_KEY, "Something went wrong when getting the tube status");
        sendDataToWear(SOME_THING_WENT_WRONG_KEY, null);
    }

    @Override
    public void noInternetConnection() {
        Log.i(CLASS_NAME_KEY, "No Internet Connection");
        sendDataToWear(NO_INTERNET_ACCESS_KEY, null);
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
}
