package com.ross.feehan.androidweardemo;

import android.content.Context;
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

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ross Feehan on 08/12/2015.
 * Copyright Ross Feehan
 */
public class WearableListAdapter extends WearableListView.Adapter{

    private Context ctx;
    private LayoutInflater layoutInflater;
    private ArrayList<DataMap> tubeLinesStatusDM;
    private TypedArray tubeLineImages;
    private static final String TUBE_LINE_NAME_KEY = "TubeLineName";
    private static final String TUBE_LINE_STATUS_KEY = "TubeLineStatus";

    //Constructor
    public WearableListAdapter(Context ctx, ArrayList<DataMap> dataMap){
        this.ctx = ctx;
        this.layoutInflater = LayoutInflater.from(ctx);
        this.tubeLinesStatusDM = dataMap;
        this.tubeLineImages = ctx.getResources().obtainTypedArray(R.array.tubeLineImages);
        Log.i("WearableListAdapter", "Displaying List View Items");
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

    public static class TubeLineHolder extends WearableListView.ViewHolder{

        @Bind(R.id.tubeLineIV) ImageView tubeLineIV;
        @Bind(R.id.tubeLineName) TextView tubeLineName;
        @Bind(R.id.tubeLineStatus) TextView tubeLineStatus;

        public TubeLineHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}