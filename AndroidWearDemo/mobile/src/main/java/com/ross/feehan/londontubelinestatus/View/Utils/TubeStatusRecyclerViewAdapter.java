package com.ross.feehan.londontubelinestatus.View.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ross.feehan.londontubelinestatus.Data.Objects.TubeLine;
import com.ross.feehan.londontubelinestatus.R;
import com.ross.feehan.londontubelinestatus.View.Activities.TubeLineInfoActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ross Feehan on 10/12/2015.
 * Copyright Ross Feehan
 */
public class TubeStatusRecyclerViewAdapter extends RecyclerView.Adapter<TubeStatusRecyclerViewAdapter.TubeViewHolder>{

    private static Context ctx;
    private static List<TubeLine> tubeLineList;
    private static TypedArray tubeLineImages;
    private static List<String> tubeLineHexColours;
    private static List<String> statusBarColours;
    private static final String TUBELINEOBJECT = "TubeLineObject";
    private static final String TUBELINEIMAGE = "TubeLineImage";
    private static final String TUBELINEHEXCOLOUR = "TubeLineHexColour";
    private static final String STATUSBARHEXCOLOUR = "StatusBarHexColour";

    //Constructor
    public TubeStatusRecyclerViewAdapter(Context ctx, List<TubeLine> tubeLines){
        this.ctx = ctx;
        this.tubeLineList = tubeLines;
        this.tubeLineImages = ctx.getResources().obtainTypedArray(R.array.tubeLineImages);
        this.tubeLineHexColours = Arrays.asList(ctx.getResources().getStringArray(R.array.tubeLineColours));
        this.statusBarColours = Arrays.asList(ctx.getResources().getStringArray(R.array.statusBarColours));
    }

    public static void onRowClicked(int position){

        Intent intent = new Intent(ctx, TubeLineInfoActivity.class);
        intent.putExtra(TUBELINEOBJECT, tubeLineList.get(position));
        intent.putExtra(TUBELINEIMAGE, tubeLineImages.getResourceId(position, 0));
        intent.putExtra(TUBELINEHEXCOLOUR, tubeLineHexColours.get(position));
        intent.putExtra(STATUSBARHEXCOLOUR, statusBarColours.get(position));
        ctx.startActivity(intent);
    }

    @Override
    public TubeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TubeViewHolder tubeViewHolder = new TubeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tube_line_status_card_layout, parent, false));
        return tubeViewHolder;
    }

    @Override
    public void onBindViewHolder(TubeViewHolder holder, int position) {
        holder.tubeLineIV.setImageDrawable(tubeLineImages.getDrawable(position));
        holder.tubeNameTV.setText(tubeLineList.get(position).getTubeName());
        holder.tubeLineStatus.setText(tubeLineList.get(position).getTubeStatus().get(0).getTubeLineStatus());
    }

    @Override
    public int getItemCount() {
        return tubeLineList.size();
    }

    /*Private class that holds the layout for the card view
     *Uses the view holder pattern
     */
    public static class TubeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.tubeLineIV) ImageView tubeLineIV;
        @Bind(R.id.tubeNameTV) TextView tubeNameTV;
        @Bind(R.id.tubeStatusTV) TextView tubeLineStatus;

        public TubeViewHolder(View itemView) {
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
