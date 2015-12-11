package com.ross.feehan.androidweardemo.View.Activities;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.ross.feehan.androidweardemo.Data.Objects.TubeLine;
import com.ross.feehan.androidweardemo.Logic.Interfaces.GetTubeStatusLogicInterface;
import com.ross.feehan.androidweardemo.R;
import com.ross.feehan.androidweardemo.Utils.AndroidWearDemoApplication;
import com.ross.feehan.androidweardemo.View.Interfaces.GetTubeLineStatusViewInterface;
import com.ross.feehan.androidweardemo.View.Utils.TubeStatusRecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ross Feehan on 10/12/2015.
 * Copyright Ross Feehan
 */
public class MainActivity extends AppCompatActivity implements GetTubeLineStatusViewInterface{

    private Context ctx;
    @Inject GetTubeStatusLogicInterface getTubeStatus;
    @Bind(R.id.tubeRV) RecyclerView tubeRV;
    @Bind(R.id.toolbar) Toolbar toolbar;

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
    }

    @Override
    public void somethingWentWrong(String message) {

    }

    @Override
    public void noInternetConnection() {

    }
}
