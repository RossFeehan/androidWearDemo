package com.ross.feehan.londontubelinestatus.Utils;

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
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;
import com.ross.feehan.londontubelinestatus.Data.Objects.TubeLine;
import com.ross.feehan.londontubelinestatus.Logic.Interfaces.GetTubeStatusLogicInterface;
import com.ross.feehan.londontubelinestatus.View.Interfaces.GetTubeLineStatusViewInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Ross Feehan on 09/12/2015.
 * Copyright Ross Feehan
 */
public class ListenerServiceForWear extends WearableListenerService implements GetTubeLineStatusViewInterface,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String CLASS_NAME_KEY = "ListenerServiceForWear";
    private static final String GET_TUBE_SERVICE_STATUS_KEY = "GetTubeServiceStatusKey";
    private static final String NO_INTERNET_ACCESS_KEY = "/NoInternetAccessKey";
    private static final String SOME_THING_WENT_WRONG_KEY = "/SomeThingWentWrongKey";
    private static final String TUBE_LINE_STATUS_KEY = "/TubeLineStatusKey";
    @Inject GetTubeStatusLogicInterface getTubeLineStatus;
    private GoogleApiClient googleApiClient;

    //CLASS METHODS
    /*Method to create the Google Api Client that
     *will be used to communicate with the wearable device through Wearable.API
     */
    private void createGoogleApiClient(){
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    /*Method to send data to the wearable through the Google API Client
     *@Pararms String dataKey - String variable key to ID the data the on the wearable device
     *@Params ArrayList<DataMap> tubeLineStatus - A DataMap that contains the needed variables of TubeLine
     * to be displayed on the wearable device
     */
    private void sendDataToWear(String dataKey, ArrayList<DataMap> tubeLineStatus){

        PutDataMapRequest putDataMapRequest = PutDataMapRequest.create(dataKey);
        putDataMapRequest.getDataMap().putDataMapArrayList(dataKey, tubeLineStatus);

        PendingResult<DataApi.DataItemResult> pendingResult = Wearable.DataApi.putDataItem(googleApiClient, putDataMapRequest.asPutDataRequest());
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
    /*Method that is called when a message has been received from the wearable device
     *This message kicks off the whole process of downloading the tube line status and
     * sending the data to the wearable device
     *@Params MessageEvent messageEvent - The message that was sent from the wearable
     */
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
    /*Method that is called when the Tube Lines have been received and being sent back to this class
     *@Params List<TubeLine> tubeLineStatus - A list that contains the tube lines and their stats
     */
    @Override
    public void receiveTubeLineStatus(List<TubeLine> tubeLineStatus) {
        Log.i(CLASS_NAME_KEY, "Received the tube line status: " + String.valueOf(tubeLineStatus.size()));
        //Convert the List<TubeLine> to an ArrayList of type DataMap to be sent to the wearable device
        sendDataToWear(TUBE_LINE_STATUS_KEY, TubeLine.convertToDataMap(tubeLineStatus));
    }

    /*Interface method that is called when something goes wrong in one of the networking calls
     *@Params Stirng message - The message of what went wrong
     */
    @Override
    public void somethingWentWrong(String message) {
        Log.i(CLASS_NAME_KEY, "Something went wrong when getting the tube status");
        sendDataToWear(SOME_THING_WENT_WRONG_KEY, null);
    }

    /*Interface method that is called when there is no internet connection
     */
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
