package com.ross.feehan.androidweardemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEventBuffer;
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
    private static final String GET_TUBE_SERVICE_STATUS_KEY = "GetTubeServiceStatusKey";
    private Node node;

    @Bind(R.id.wearable_list) WearableListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.ctx = this;
        ButterKnife.bind(this);

        /*List<String> list1 = Arrays.asList(getResources().getStringArray(R.array.list1));
        List<String> list2 = Arrays.asList(getResources().getStringArray(R.array.list2));
        listView.setAdapter(new WearableListAdapter(this, list1, list2));*/
    }

    //CLASS METHODS
    private void createGoogleApi(){
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    /*private void requestTubeLineStatus(){
        PutDataMapRequest putDataMapRequest = PutDataMapRequest.create("/getTubeLineStatus");
        putDataMapRequest.getDataMap().putString(GET_TUBE_SERVICE_STATUS_KEY, "GetTubeStatus");
        PutDataRequest putDataRequest = putDataMapRequest.asPutDataRequest();
        PendingResult<DataApi.DataItemResult> pendingResult = Wearable.DataApi.putDataItem(googleApiClient, putDataRequest);
    }*/

    private void sendMessage(){
        if(node != null && googleApiClient != null && googleApiClient.isConnected()){
            Toast.makeText(ctx, "Sending Message", Toast.LENGTH_LONG).show();
           Wearable.MessageApi.sendMessage(googleApiClient, node.getId(), GET_TUBE_SERVICE_STATUS_KEY, null).setResultCallback(

                   new ResultCallback<MessageApi.SendMessageResult>() {
                       @Override
                       public void onResult(MessageApi.SendMessageResult sendMessageResult) {
                           if(sendMessageResult.getStatus().isSuccess()){
                               Toast.makeText(ctx, "message successful", Toast.LENGTH_LONG).show();
                           }
                            if(!sendMessageResult.getStatus().isSuccess()){
                                Toast.makeText(ctx, "Cannot fetch tube status at this time", Toast.LENGTH_LONG).show();
                            }
                       }
                   }
           );
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
        resolveNode();
        Wearable.DataApi.addListener(googleApiClient, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEventBuffer) {
        Toast.makeText(ctx, "Got new Data", Toast.LENGTH_LONG).show();
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
        Wearable.DataApi.removeListener(googleApiClient, this);
        googleApiClient.disconnect();
    }
}