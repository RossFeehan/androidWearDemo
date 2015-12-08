package com.ross.feehan.androidweardemo;

import android.content.Context;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ross Feehan on 08/12/2015.
 * Copyright Ross Feehan
 */
public class WearableListAdapter extends WearableListView.Adapter{

    private Context ctx;
    private LayoutInflater layoutInflater;
    private List<String> firstList;
    private List<String> secondList;

    //Constructor
    public WearableListAdapter(Context ctx, List<String> firstList, List<String> secondList){
        this.ctx = ctx;
        this.layoutInflater = LayoutInflater.from(ctx);
        this.firstList = firstList;
        this.secondList = secondList;
    }

    @Override
    public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TubeLineHolder(layoutInflater.inflate(R.layout.custom_list_view, null));
    }

    @Override
    public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {
        TubeLineHolder tubeLineHolder = (TubeLineHolder) holder;

        tubeLineHolder.tubeLineName.setText(firstList.get(position));
        tubeLineHolder.tubeLineStatus.setText(secondList.get(position));
        tubeLineHolder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return firstList.size();
    }

    private static class TubeLineHolder extends WearableListView.ViewHolder{

        TextView tubeLineName, tubeLineStatus;

        public TubeLineHolder(View itemView) {
            super(itemView);
            tubeLineName = (TextView) itemView.findViewById(R.id.tubeLineName);
            tubeLineStatus = (TextView) itemView.findViewById(R.id.tubeLineStatus);
        }
    }
}
