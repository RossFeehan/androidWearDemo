package com.ross.feehan.androidweardemo.View.Utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ross.feehan.androidweardemo.Data.Objects.TubeLine;
import com.ross.feehan.androidweardemo.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ross Feehan on 10/12/2015.
 * Copyright Ross Feehan
 */
public class TubeStatusRecyclerViewAdapter extends RecyclerView.Adapter<TubeStatusRecyclerViewAdapter.TubeViewHolder>{

    private Context ctx;
    private List<TubeLine> tubeLineList;
    private TypedArray tubeLineImages;

    //Constructor
    public TubeStatusRecyclerViewAdapter(Context ctx, List<TubeLine> tubeLines){
        this.ctx = ctx;
        this.tubeLineList = tubeLines;
        this.tubeLineImages = ctx.getResources().obtainTypedArray(R.array.tubeLineImages);
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
    public static class TubeViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.tubeLineIV) ImageView tubeLineIV;
        @Bind(R.id.tubeNameTV) TextView tubeNameTV;
        @Bind(R.id.tubeStatusTV) TextView tubeLineStatus;

        public TubeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
