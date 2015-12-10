package com.ross.feehan.androidweardemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,DataApi.DataListener {

    private GoogleApiClient googleApiClient;
    private Context ctx;
    private static final String CLASS_NAME = "MainActivity";
    private static final String GET_TUBE_SERVICE_STATUS_KEY = "GetTubeServiceStatusKey";
    private static final String NO_INTERNET_ACCESS_KEY = "/NoInternetAccessKey";
    private static final String SOME_THING_WENT_WRONG_KEY = "/SomeThingWentWrongKey";
    private static final String TUBE_LINE_STATUS_KEY = "/TubeLineStatusKey";
    private Node node;

    @Bind(R.id.wearable_list) WearableListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.ctx = this;
        ButterKnife.bind(this);
    }

    //CLASS METHODS
    private void createGoogleApi(){
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    private void sendMessage(){
        if(node != null && googleApiClient != null && googleApiClient.isConnected()){
            Wearable.MessageApi.sendMessage(googleApiClient, node.getId(), GET_TUBE_SERVICE_STATUS_KEY, null).setResultCallback(
                    new ResultCallback<MessageApi.SendMessageResult>() {
                        @Override
                        public void onResult(MessageApi.SendMessageResult sendMessageResult) {
                            if (sendMessageResult.getStatus().isSuccess()) {
                                Log.i("MainActivity", "message Successful");
                            }
                        }
                    }
            );
            Log.i(CLASS_NAME, "message Sent");
        }
        else{
            Toast.makeText(ctx, "Cannot fetch tube status at this time", Toast.LENGTH_LONG).show();
        }
    }

    private void resolveNode() {
        Wearable.NodeApi.getConnectedNodes(googleApiClient).setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
            @Override
            public void onResult(NodeApi.GetConnectedNodesResult nodes) {
                for (Node Nnode : nodes.getNodes()) {
                    node = Nnode;
                    sendMessage();
                }
            }
        });
    }

    //INTERFACE METHODS
    //GOOGLE API CLIENT INTERFACE METHODS
    @Override
    public void onConnected(Bundle bundle) {
        Log.i(CLASS_NAME, "Registering for Wearable Data Listener");
        Wearable.DataApi.addListener(googleApiClient, this);
        resolveNode();

    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEventBuffer) {
        Log.i(CLASS_NAME, "Received Data");
        for(DataEvent dataEvent : dataEventBuffer){
            if(dataEvent.getType() == DataEvent.TYPE_CHANGED){
                DataItem dataItem = dataEvent.getDataItem();

                if(dataItem.getUri().getPath().compareTo(NO_INTERNET_ACCESS_KEY) == 0){
                    Toast.makeText(ctx, "Sorry, no internet access at the moment", Toast.LENGTH_LONG).show();
                }
                else if(dataItem.getUri().getPath().compareTo(SOME_THING_WENT_WRONG_KEY) == 0){
                    Toast.makeText(ctx, "Sorry, something went wrong", Toast.LENGTH_LONG).show();
                }
                else if(dataItem.getUri().getPath().compareTo(TUBE_LINE_STATUS_KEY) == 0){
                    Log.i(CLASS_NAME, "Getting the DataMap List and displaying it");
                    listView.setAdapter(new WearableListAdapter(ctx, DataMapItem.fromDataItem(dataItem).getDataMap().getDataMapArrayList(TUBE_LINE_STATUS_KEY)));
                }
            }
        }
    }

    //ACTIVITY LIFECYCLE METHODS
    @Override
    protected void onStart(){
        super.onStart();
        createGoogleApi();
        googleApiClient.connect();
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(CLASS_NAME, "Removing Wearable Data Listener and Google API Client");
        Wearable.DataApi.removeListener(googleApiClient, this);
        googleApiClient.disconnect();
    }
}