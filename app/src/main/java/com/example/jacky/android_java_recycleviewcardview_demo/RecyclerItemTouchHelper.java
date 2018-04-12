package com.example.jacky.android_java_recycleviewcardview_demo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper.SimpleCallback;
import android.view.View;

/**
 * Created by Jacky on 2018/4/11.
 */

public class RecyclerItemTouchHelper extends SimpleCallback {
    private RecyclerItemTouchHelperListener listener;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs,RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
    {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        // 释放View时回调，清除背景颜色，隐藏图标
        // 默认是操作ViewHolder的itemView，这里调用ItemTouchUIUtil的clearView方法传入指定的view
        getDefaultUIUtil().clearView(((MyAdapter.ViewHolder) viewHolder).vItem);
        ((MyAdapter.ViewHolder) viewHolder).vBackground.setBackgroundColor(Color.TRANSPARENT);
        ((MyAdapter.ViewHolder) viewHolder).ivSchedule.setVisibility(View.GONE);
        ((MyAdapter.ViewHolder) viewHolder).ivDone.setVisibility(View.GONE);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        // 当viewHolder的滑动或拖拽状态改变时回调
        if (viewHolder != null) {
            // 默认是操作ViewHolder的itemView，这里调用ItemTouchUIUtil的clearView方法传入指定的view
            getDefaultUIUtil().onSelected((((MyAdapter.ViewHolder) viewHolder).vItem));
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        // ItemTouchHelper的onDraw方法会调用该方法，可以使用Canvas对象进行绘制，绘制的图案会在RecyclerView的下方
        // 默认是操作ViewHolder的itemView，这里调用ItemTouchUIUtil的clearView方法传入指定的view
        getDefaultUIUtil().onDraw(c, recyclerView, (((MyAdapter.ViewHolder) viewHolder).vItem) , dX, dY, actionState, isCurrentlyActive);
        if (dX > 0) { // 向左滑动是的提示
            ((MyAdapter.ViewHolder) viewHolder).vBackground.setBackgroundResource(R.color.colorDone);
            ((MyAdapter.ViewHolder) viewHolder).ivDone.setVisibility(View.VISIBLE);
            ((MyAdapter.ViewHolder) viewHolder).ivSchedule.setVisibility(View.GONE);
        }
        if (dX < 0) { // 向右滑动时的提示
            ((MyAdapter.ViewHolder) viewHolder).vBackground.setBackgroundResource(R.color.colorSchedule);
            ((MyAdapter.ViewHolder) viewHolder).ivSchedule.setVisibility(View.VISIBLE);
            ((MyAdapter.ViewHolder) viewHolder).ivDone.setVisibility(View.GONE);
        }
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        // ItemTouchHelper的onDrawOver方法会调用该方法，可以使用Canvas对象进行绘制，绘制的图案会在RecyclerView的上方
        // 默认是操作ViewHolder的itemView，这里调用ItemTouchUIUtil的clearView方法传入指定的view
        getDefaultUIUtil().onDrawOver(c, recyclerView, (((MyAdapter.ViewHolder) viewHolder).vItem), dX, dY, actionState, isCurrentlyActive);
    }

    public interface RecyclerItemTouchHelperListener
    {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }
}
