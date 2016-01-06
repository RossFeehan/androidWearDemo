package com.ross.feehan.londontubelinestatus;

import android.content.Context;
import android.support.wearable.view.WearableListView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Ross Feehan on 08/12/2015.
 * Copyright Ross Feehan
 */
public class WearableListItemLayout extends LinearLayout
        implements WearableListView.OnCenterProximityListener {

    private ImageView tubeLineIV;
    private TextView tubeName;
    private TextView tubeStatus;

    private final float mFadedTextAlpha;

    public WearableListItemLayout(Context context) {
        this(context, null);
    }

    public WearableListItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WearableListItemLayout(Context context, AttributeSet attrs,
                                  int defStyle) {
        super(context, attrs, defStyle);

        mFadedTextAlpha = 10 / 100f;
    }

    // Get references to the icon and text in the item layout definition
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // These are defined in the layout file for list items
        // (see next section)
        tubeLineIV = (ImageView) findViewById(R.id.tubeLineIV);
        tubeName = (TextView) findViewById(R.id.tubeLineName);
        tubeStatus = (TextView) findViewById(R.id.tubeLineStatus);
    }

    @Override
    public void onCenterPosition(boolean animate) {
        tubeLineIV.setAlpha(1f);
        tubeName.setAlpha(1f);
        tubeStatus.setAlpha(1f);
    }

    @Override
    public void onNonCenterPosition(boolean animate) {
        tubeLineIV.setAlpha(mFadedTextAlpha);
        tubeStatus.setAlpha(mFadedTextAlpha);
        tubeName.setAlpha(mFadedTextAlpha);
    }
}
