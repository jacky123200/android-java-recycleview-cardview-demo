package com.example.jacky.android_java_recycleviewcardview_demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

/**
 * Created by Jacky on 2018/4/11.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    //Adapter class用於設定自身的adapter(customer view)

    private Context context;
    private List<String> mDataset;  //資料集
    private Boolean isCardView;
    private Boolean isStagger;

    //constructors
    public MyAdapter(Context context, List<String> myDataset, Boolean isCardView, Boolean isStagger)
    {
        this.context = context;
        this.mDataset = myDataset;
        this.isCardView = isCardView;
        this.isStagger = isStagger;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        View vBackground; // 背景
        View vItem;
        TextView tvItem; // 滑动的view
        ImageView ivDone;
        ImageView ivSchedule;

        public ViewHolder(View itemView) {
            super(itemView);
//            vBackground = itemView.findViewById(R.id.rl_background);
//            vItem = itemView.findViewById(R.id.ll_item);
//            tvItem = (TextView) itemView.findViewById(R.id.tv_item);
//            ivDone = (ImageView) itemView.findViewById(R.id.iv_done);
//            ivSchedule = (ImageView) itemView.findViewById(R.id.iv_schedule);
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = null;
        if(isCardView)
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        else
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.normal_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position)
    {
        //當物件顯示於畫面時被調用，可利用此方法更新該物件之內容。
        holder.tvItem.setText(mDataset.get(position));
        if(isStagger)
            holder.tvItem.setHeight(random(800,80));

        //item onClick event
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(context,"Item "+mDataset.get(position)+" click ",Toast.LENGTH_SHORT).show();
            }
        });

        //item onLongClick event
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view)
            {
                Toast.makeText(context,"Item "+mDataset.get(position)+" long click ",Toast.LENGTH_SHORT).show();
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

    public void removeItem(int position)
    {
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(String s,int position)
    {
        mDataset.add(position,s);
        notifyItemInserted(position);
    }
}