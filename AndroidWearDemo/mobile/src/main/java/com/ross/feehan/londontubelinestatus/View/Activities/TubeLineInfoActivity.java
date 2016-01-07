package com.ross.feehan.londontubelinestatus.View.Activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ross.feehan.londontubelinestatus.Data.Objects.TubeLine;
import com.ross.feehan.londontubelinestatus.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ross Feehan on 06/01/2016.
 * Copyright Ross Feehan
 */
public class TubeLineInfoActivity extends AppCompatActivity {

    private Context ctx;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.tubeLineStatusCV) CardView tubeLineStatusCV;
    TextView tubeLineStatusCVHeaderTV, tubeLineStatusCVStatusTV, tubeLineStatusCVBodyTV;
    @Bind(R.id.tubeLinePlannedDisruptions) CardView tubeLineDisruptionsCV;
    TextView tubeLineDisruptionsCVHeaderTV, tubeLineDisruptionsCVStatusTV, tubeLineDisruptionsCVBodyTV, tubeLineDisruptionsCVAddInfoTV;
    @Bind(R.id.tubeLineIV) ImageView tubeLineIV;
    private static final String TUBELINEOBJECT = "TubeLineObject";
    private static final String TUBELINEIMAGE = "TubeLineImage";
    private static final String TUBELINEHEXCOLOUR = "TubeLineHexColour";
    private static final String STATUSBARHEXCOLOUR = "StatusBarHexColour";
    private String tubeLineColour;
    private String statusBarColour;
    private TubeLine tubeLine;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tube_line_info_view);

        ButterKnife.bind(this);
        this.ctx = this;

        //get all the extras from the intent sent from the recycler adapter
        tubeLine = (TubeLine) getIntent().getSerializableExtra(TUBELINEOBJECT);
        tubeLineIV.setImageResource(getIntent().getIntExtra(TUBELINEIMAGE, 0));
        tubeLineColour = getIntent().getStringExtra(TUBELINEHEXCOLOUR);
        statusBarColour = getIntent().getStringExtra(STATUSBARHEXCOLOUR);

        //set up the toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tubeLine.getTubeName());
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(tubeLineColour)));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.mipmap.ic_arrow_back_white_24dp));

        //change the colour of the status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(statusBarColour));
        }

        setCardViewViews();

        //TODO IMPLEMENT PULL DOWN TO REFRESH
    }

    private void setCardViewViews(){
        //The views within the tube line status card
        tubeLineStatusCVHeaderTV = ButterKnife.findById(tubeLineStatusCV, R.id.headingTV);
        tubeLineStatusCVHeaderTV.setText(getResources().getString(R.string.status));
        tubeLineStatusCVHeaderTV.setTextColor(Color.parseColor(tubeLineColour));

        tubeLineStatusCVStatusTV = ButterKnife.findById(tubeLineStatusCV, R.id.statusTV);
        tubeLineStatusCVStatusTV.setText(tubeLine.getTubeStatus().get(0).getTubeLineStatus());

        //display the reason for the tube line status if there is one
        tubeLineStatusCVBodyTV = ButterKnife.findById(tubeLineStatusCV, R.id.bodyTV);
        if(tubeLine.getTubeStatus().get(0).getStatusReason() != null){
            tubeLineStatusCVBodyTV.setText(tubeLine.getTubeStatus().get(0).getStatusReason());
        }

        //The views within the tube line disruptions card
        tubeLineDisruptionsCVHeaderTV = ButterKnife.findById(tubeLineDisruptionsCV, R.id.headingTV);
        tubeLineDisruptionsCVHeaderTV.setText(getResources().getString(R.string.disruptions));
        tubeLineDisruptionsCVHeaderTV.setTextColor(Color.parseColor(tubeLineColour));

        tubeLineDisruptionsCVStatusTV = ButterKnife.findById(tubeLineDisruptionsCV, R.id.statusTV);
        tubeLineDisruptionsCVStatusTV.setText(getResources().getString(R.string.noDisruptions));

        tubeLineDisruptionsCVBodyTV = ButterKnife.findById(tubeLineDisruptionsCV, R.id.bodyTV);
        tubeLineDisruptionsCVAddInfoTV = ButterKnife.findById(tubeLineDisruptionsCV, R.id.addInfoTV);

        //TODO, GET THE PLANNED DISRUPTIONS FROM TFL API AND DISPLAY HERE IF THERE ARE ANY

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
