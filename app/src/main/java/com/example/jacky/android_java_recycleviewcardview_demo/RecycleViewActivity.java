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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jacky on 2018/4/10.
 */

public class RecycleViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int defaultSpanCount = 3;
    private boolean isStagger=false;

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
                isStagger=true;
                mLayoutManager = new StaggeredGridLayoutManager(defaultSpanCount,StaggeredGridLayoutManager.VERTICAL);
                break;
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
        //End decide layout manager

        ArrayList<String> myDataset = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            myDataset.add(Integer.toString(i));
        }

        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        //Adapter class用於設定自身的adapter(customer view)
        private List<String> mDataset;  //資料集

        public class ViewHolder extends RecyclerView.ViewHolder {
            //ViewHolder class 主要是用於連接layout (每行item)
            public TextView mTextView;
            public ViewHolder(View v) {
                super(v);
                mTextView = (TextView)v.findViewById(R.id.textView);
            }
        }

        public MyAdapter(List<String> myDataset) {
            //constructors
            mDataset = myDataset;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.normal_item, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //當物件顯示於畫面時被調用，可利用此方法更新該物件之內容。
            holder.mTextView.setText(mDataset.get(position));
            if(isStagger)
                holder.mTextView.setHeight(random(800,80));
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }

        private int random(int max, int min){
            Random r = new Random();
            int i1 = r.nextInt((max-min)+min)+min;
            return i1;
        }
    }
}
