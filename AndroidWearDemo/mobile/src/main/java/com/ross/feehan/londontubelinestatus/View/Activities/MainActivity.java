package com.ross.feehan.londontubelinestatus.View.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ross.feehan.londontubelinestatus.Data.Objects.TubeLine;
import com.ross.feehan.londontubelinestatus.Data.Objects.TubeLinePlannedWork;
import com.ross.feehan.londontubelinestatus.Logic.Interfaces.GetTubeLinesPlannedDisruptionsLogicInterface;
import com.ross.feehan.londontubelinestatus.Logic.Interfaces.GetTubeStatusLogicInterface;
import com.ross.feehan.londontubelinestatus.R;
import com.ross.feehan.londontubelinestatus.Utils.AndroidWearDemoApplication;
import com.ross.feehan.londontubelinestatus.View.Interfaces.GetTubeLineStatusViewInterface;
import com.ross.feehan.londontubelinestatus.View.Interfaces.GetTubeLinesPlannedDisruptionsViewInterface;
import com.ross.feehan.londontubelinestatus.View.Utils.TubeStatusRecyclerViewAdapter;
import com.splunk.mint.Mint;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ross Feehan on 10/12/2015.
 * Copyright Ross Feehan
 */
public class MainActivity extends AppCompatActivity implements GetTubeLineStatusViewInterface, GetTubeLinesPlannedDisruptionsViewInterface, SwipeRefreshLayout.OnRefreshListener {

    private Context ctx;
    private List<TubeLine> tubeLineStatusList;
    @Inject GetTubeStatusLogicInterface getTubeStatus;
    @Inject GetTubeLinesPlannedDisruptionsLogicInterface getPlannedWorks;
    @Bind(R.id.tubeRV) RecyclerView tubeRV;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.noInternetTV) TextView noInternetTV;
    @Bind(R.id.loadingLayout) RelativeLayout loadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        this.ctx = this;

        //FOR DI
        ((AndroidWearDemoApplication)getApplication()).getObjectGraph().inject(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.londonTubeStatus));

        //Crash reporting
        Mint.initAndStartSession(MainActivity.this, "0b9cb4ff");

        /*//for swipe to refresh
        swipeRefreshLayout.setOnRefreshListener(this);

        //setting the layout manager for the recycler view
        //Creating the layout of the recycler view (linearlayout creates a list view like recycler view)
        LinearLayoutManager recyclerViewLayoutManager = new LinearLayoutManager(ctx);
        tubeRV.setLayoutManager(recyclerViewLayoutManager);

        //the tube line status
        getTubeStatus.getTubeLineStatus(this);*/
    }

    //INTERFACE METHODS
    //GetTubeLineStatusViewInterface methods
    @Override
    public void receiveTubeLineStatus(List<TubeLine> tubeLineStatus) {
        noInternetTV.setVisibility(View.INVISIBLE);

        this.tubeLineStatusList = tubeLineStatus;
        //now get the planned works
        getPlannedWorks.getTubeLinesPlannedDisruptions(this);
    }

    //GetTubeLinesPlannedDisruptionsViewInterface INTERFACE METHODS
    @Override
    public void receiveTubeLinePlannedDisruptions(List<TubeLinePlannedWork> tubeLinePlannedWorks) {
        loadingLayout.setVisibility(View.INVISIBLE);

        tubeRV.setAdapter(new TubeStatusRecyclerViewAdapter(ctx, tubeLineStatusList, tubeLinePlannedWorks));

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void somethingWentWrong(String message) {
        noInternetTV.setVisibility(View.VISIBLE);
        loadingLayout.setVisibility(View.INVISIBLE);

        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void noInternetConnection() {
        noInternetTV.setVisibility(View.VISIBLE);
        loadingLayout.setVisibility(View.INVISIBLE);

        Toast.makeText(ctx, "Sorry, no internet connection at the moment", Toast.LENGTH_LONG).show();
        swipeRefreshLayout.setRefreshing(false);
    }

    //SwipeRefreshLayout.OnRefreshListener Interface methods
    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        getTubeStatus.getTubeLineStatus(this);
        noInternetTV.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onStart(){
        super.onStart();

        //for swipe to refresh
        swipeRefreshLayout.setOnRefreshListener(this);

        //setting the layout manager for the recycler view
        //Creating the layout of the recycler view (linearlayout creates a list view like recycler view)
        LinearLayoutManager recyclerViewLayoutManager = new LinearLayoutManager(ctx);
        tubeRV.setLayoutManager(recyclerViewLayoutManager);

        //the tube line status
        getTubeStatus.getTubeLineStatus(this);
    }
}
