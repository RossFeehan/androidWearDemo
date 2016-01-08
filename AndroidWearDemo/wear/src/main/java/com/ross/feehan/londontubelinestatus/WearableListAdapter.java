package com.ross.feehan.londontubelinestatus;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.wearable.view.WearableListView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.wearable.DataMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ross Feehan on 08/12/2015.
 * Copyright Ross Feehan
 */
public class WearableListAdapter extends WearableListView.Adapter{

    private static Context ctx;
    private LayoutInflater layoutInflater;
    private static ArrayList<DataMap> tubeLinesStatusDM;
    private static TypedArray tubeLineImages;
    private static List<String> tubeLineColours;
    private static final String TUBE_LINE_NAME_KEY = "TubeLineName";
    private static final String TUBE_LINE_STATUS_KEY = "TubeLineStatus";
    private static final String TUBE_LINE_STATUS_REASON_KEY = "TubeLineStatusReason";
    private static final String TUBE_LINE_IMAGE = "TubeLineImage";
    private static final String TUBE_LINE_COLOUR = "TubeLineColour";

    //Constructor
    public WearableListAdapter(Context ctx, ArrayList<DataMap> statusDataMap){
        this.ctx = ctx;
        this.layoutInflater = LayoutInflater.from(ctx);
        this.tubeLinesStatusDM = statusDataMap;
        this.tubeLineImages = ctx.getResources().obtainTypedArray(R.array.tubeLineImages);
        this.tubeLineColours = Arrays.asList(ctx.getResources().getStringArray(R.array.tubeLineColours));
        Log.i("WearableListAdapter", "Displaying List View Items");
    }

    public static void onRowClicked(int position){
        Intent intent = new Intent(ctx, TubeLineInfoActivity.class);
        intent.putExtra(TUBE_LINE_STATUS_KEY, tubeLinesStatusDM.get(position).getString(TUBE_LINE_STATUS_KEY));
        intent.putExtra(TUBE_LINE_STATUS_REASON_KEY, tubeLinesStatusDM.get(position).getString(TUBE_LINE_STATUS_REASON_KEY));
        intent.putExtra(TUBE_LINE_IMAGE, tubeLineImages.getResourceId(position, 0));
        intent.putExtra(TUBE_LINE_COLOUR, tubeLineColours.get(position));
        ctx.startActivity(intent);
    }

    @Override
    public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TubeLineHolder(layoutInflater.inflate(R.layout.custom_list_view, null));
    }

    @Override
    public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {

        TubeLineHolder tubeLineHolder = (TubeLineHolder) holder;

        tubeLineHolder.tubeLineIV.setImageDrawable(tubeLineImages.getDrawable(position));
        tubeLineHolder.tubeLineName.setText(tubeLinesStatusDM.get(position).getString(TUBE_LINE_NAME_KEY));
        tubeLineHolder.tubeLineStatus.setText(tubeLinesStatusDM.get(position).getString(TUBE_LINE_STATUS_KEY));
        tubeLineHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return tubeLinesStatusDM.size();
    }

    public static class TubeLineHolder extends WearableListView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.tubeLineIV) ImageView tubeLineIV;
        @Bind(R.id.tubeLineName) TextView tubeLineName;
        @Bind(R.id.tubeLineStatus) TextView tubeLineStatus;

        public TubeLineHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onRowClicked(getAdapterPosition());
        }
    }
}