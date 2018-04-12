package com.example.jacky.android_java_recycleviewcardview_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;

/**
 * Created by Jacky on 2018/4/10.
 */

public class RecycleViewActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int defaultSpanCount = 3;
    private Boolean isStagger=false;
    private Boolean isCardView=false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_my_recycleview);

        // decide layout manager
        Intent intent = getIntent();
        String layoutManagerType = intent.getStringExtra("layoutmanager");
        isCardView = intent.getBooleanExtra("isCardView",false);

        switch (layoutManagerType)
        {
            case "linearlayout":
                mLayoutManager = new LinearLayoutManager(this);
                break;
            case "gridlayout":
                mLayoutManager = new GridLayoutManager(this,defaultSpanCount);
                break;
            case "staggeredgridlayout":
                isStagger=true;
                mLayoutManager = new StaggeredGridLayoutManager(defaultSpanCount,StaggeredGridLayoutManager.VERTICAL);
                break;
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
        //End decide layout manager

        final ArrayList<String> myDataset = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            myDataset.add(Integer.toString(i));
        }

        mAdapter = new MyAdapter(this, myDataset, isCardView, isStagger);
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

    }
}
