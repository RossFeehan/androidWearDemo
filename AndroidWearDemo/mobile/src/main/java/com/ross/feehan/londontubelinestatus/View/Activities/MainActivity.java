package com.ross.feehan.londontubelinestatus.View.Activities;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.ross.feehan.londontubelinestatus.Data.Objects.TubeLine;
import com.ross.feehan.londontubelinestatus.Logic.Interfaces.GetTubeStatusLogicInterface;
import com.ross.feehan.londontubelinestatus.R;
import com.ross.feehan.londontubelinestatus.Utils.AndroidWearDemoApplication;
import com.ross.feehan.londontubelinestatus.View.Interfaces.GetTubeLineStatusViewInterface;
import com.ross.feehan.londontubelinestatus.View.Utils.TubeStatusRecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ross Feehan on 10/12/2015.
 * Copyright Ross Feehan
 */
public class MainActivity extends AppCompatActivity implements GetTubeLineStatusViewInterface, SwipeRefreshLayout.OnRefreshListener {

    private Context ctx;
    @Inject GetTubeStatusLogicInterface getTubeStatus;
    @Bind(R.id.tubeRV) RecyclerView tubeRV;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;

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

        //for swipe to refresh
        swipeRefreshLayout.setOnRefreshListener(this);

        //the tube line status
        getTubeStatus.getTubeLineStatus(this);
    }

    //INTERFACE METHODS
    //GetTubeLineStatusViewInterface methods
    @Override
    public void receiveTubeLineStatus(List<TubeLine> tubeLineStatus) {
        //Creating the layout of the recycler view (linearlayout creates a list view like recycler view)
        LinearLayoutManager recyclerViewLayoutManager = new LinearLayoutManager(ctx);
        tubeRV.setLayoutManager(recyclerViewLayoutManager);

        tubeRV.setAdapter(new TubeStatusRecyclerViewAdapter(ctx, tubeLineStatus));

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void somethingWentWrong(String message) {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void noInternetConnection() {
        Toast.makeText(ctx, "Sorry, no internet connection at the moment", Toast.LENGTH_LONG).show();
        swipeRefreshLayout.setRefreshing(false);
    }

    //SwipeRefreshLayout.OnRefreshListener Interface methods
    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        getTubeStatus.getTubeLineStatus(this);
    }
}
