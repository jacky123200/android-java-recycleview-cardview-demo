package com.example.jacky.android_java_recycleviewcardview_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Jacky on 2018/4/10.
 */

public class RecycleViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int defaultSpanCount = 3;
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
                mLayoutManager = new GridLayoutManager(this,defaultSpanCount);
                break;
            case "staggeredgridlayout":
                mLayoutManager = new StaggeredGridLayoutManager(defaultSpanCount,StaggeredGridLayoutManager.VERTICAL);
                break;
        }
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private String[] mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTextView;
            public ViewHolder(TextView v) {
                super(v);
                mTextView = v;
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(String[] myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.normal_item, parent, false);
            // set the view's size, margins, paddings and layout parameters

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView.setText(mDataset[position]);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}
