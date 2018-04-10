package com.example.jacky.android_java_recycleviewcardview_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by Jacky on 2018/4/10.
 */

public class RecycleViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_my_recycleview);

        // decide layout manager
        Intent intent = getIntent();
        String layoutManagerType = intent.getStringExtra("layoutmanager");
        switch (layoutManagerType)
        {
            case "linearlayout":
                mLayoutManager = new LinearLayoutManager(this);
                break;
            case "gridlayout":
                mLayoutManager = new GridLayoutManager(this,);
                break;
            case "staggeredgridlayout":
                mLayoutManager = new StaggeredGridLayoutManager();
                break;
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
    }
}
