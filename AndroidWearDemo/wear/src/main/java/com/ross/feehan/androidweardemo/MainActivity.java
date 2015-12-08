package com.ross.feehan.androidweardemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity implements WearableListView.ClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> list1 = Arrays.asList(getResources().getStringArray(R.array.list1));
        List<String> list2 = Arrays.asList(getResources().getStringArray(R.array.list2));

        WearableListView listView = (WearableListView) findViewById(R.id.wearable_list);
        listView.setAdapter(new WearableListAdapter(this, list1, list2));
    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {

    }

    @Override
    public void onTopEmptyRegionClick() {

    }
}
