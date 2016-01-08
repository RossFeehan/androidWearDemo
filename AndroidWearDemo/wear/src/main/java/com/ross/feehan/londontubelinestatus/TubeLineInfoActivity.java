package com.ross.feehan.londontubelinestatus;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ross Feehan on 08/01/2016.
 * Copyright Ross Feehan
 */
public class TubeLineInfoActivity extends Activity{

    private Context ctx;
    @Bind(R.id.tubeLineIV)ImageView tubeLineIV;
    @Bind(R.id.statusTV)TextView tubeLineStatusTV;
    @Bind(R.id.reasonTV) TextView tubeLineReasonTV;
    private static final String TUBE_LINE_NAME_KEY = "TubeLineName";
    private static final String TUBE_LINE_STATUS_KEY = "TubeLineStatus";
    private static final String TUBE_LINE_STATUS_REASON_KEY = "TubeLineStatusReason";
    private static final String TUBE_LINE_IMAGE = "TubeLineImage";
    private static final String TUBE_LINE_COLOUR = "TubeLineColour";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tube_line_info);

        this.ctx = this;
        ButterKnife.bind(this);

        displayTubeLineInfo(getIntent().getStringExtra(TUBE_LINE_STATUS_KEY),
                getIntent().getStringExtra(TUBE_LINE_STATUS_REASON_KEY), getIntent().getIntExtra(TUBE_LINE_IMAGE, 0),
                getIntent().getStringExtra(TUBE_LINE_COLOUR));

    }

    /*Method that displays the tube line information in the correct views
     *@Params String status - The status of the tube line
     * @Params String reason - The reason for the tube lines status
     * @Params int imageID - The ID of the image of the tube line to display
     * @Params String colourValue - The hex colour value of the tube line
     */
    private void displayTubeLineInfo(String status, String reason, int imageID, String colourValue){
        tubeLineIV.setImageResource(imageID);
        tubeLineStatusTV.setText(status);
        tubeLineStatusTV.setTextColor(Color.parseColor(colourValue));
        tubeLineReasonTV.setText(reason);
    }
}
