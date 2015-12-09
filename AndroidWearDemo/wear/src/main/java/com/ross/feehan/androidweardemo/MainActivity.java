package com.ross.feehan.androidweardemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Wearable;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity{

    private GoogleApiClient googleApiClient;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.ctx = this;

        List<String> list1 = Arrays.asList(getResources().getStringArray(R.array.list1));
        List<String> list2 = Arrays.asList(getResources().getStringArray(R.array.list2));

        WearableListView listView = (WearableListView) findViewById(R.id.wearable_list);
        listView.setAdapter(new WearableListAdapter(this, list1, list2));

    }

    private void createGoogleApi(){
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {
                        Toast.makeText(ctx, "GoogleAPI Connected", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        Toast.makeText(ctx, "GoogleAPI Connection Suspended", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {
                        Toast.makeText(ctx, "Connection to GoogleAPI failed", Toast.LENGTH_LONG).show();
                    }
                })
                .addApi(Wearable.API)
                .build();
    }

    @Override
    protected void onStart(){
        super.onStart();
        createGoogleApi();
        googleApiClient.connect();
    }

    @Override
    protected void onStop(){
        super.onStop();
        googleApiClient.disconnect();
    }
}
