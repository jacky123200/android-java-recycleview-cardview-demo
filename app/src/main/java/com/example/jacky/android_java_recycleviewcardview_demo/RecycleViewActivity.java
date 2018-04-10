package com.example.jacky.android_java_recycleviewcardview_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        //itemTouchHelper
        ItemTouchHelper.Callback mCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.START|ItemTouchHelper.END) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                mAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(getDragDirs(recyclerView, viewHolder), getSwipeDirs(recyclerView, viewHolder));
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                myDataset.remove(position);
                mAdapter.notifyItemRemoved(position);
            }
        };

        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(mCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
    {
        //Adapter class用於設定自身的adapter(customer view)
        private List<String> mDataset;  //資料集

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            //ViewHolder class 主要是用於連接layout (每行item)
            public TextView mTextView;
            public ViewHolder(View v)
            {
                super(v);
                mTextView = (TextView)v.findViewById(R.id.textView);
            }
        }

        public MyAdapter(List<String> myDataset)
        {
            //constructors
            mDataset = myDataset;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View v = null;
            if(isCardView)
            {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
            }
            else
            {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.normal_item, parent, false);
            }
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder,final int position)
        {
            //當物件顯示於畫面時被調用，可利用此方法更新該物件之內容。
            holder.mTextView.setText(mDataset.get(position));
            if(isStagger)
                holder.mTextView.setHeight(random(800,80));

            //item onClick event
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Toast.makeText(RecycleViewActivity.this,"Item "+mDataset.get(position)+" click ",Toast.LENGTH_SHORT).show();
                }
            });

            //item onLongClick event
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View view)
                {
                    Toast.makeText(RecycleViewActivity.this,"Item "+mDataset.get(position)+" long click ",Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }

        @Override
        public int getItemCount()
        {
            return mDataset.size();
        }

        private int random(int max, int min)
        {
            Random r = new Random();
            int i1 = r.nextInt((max-min)+min)+min;
            return i1;
        }
    }
}
