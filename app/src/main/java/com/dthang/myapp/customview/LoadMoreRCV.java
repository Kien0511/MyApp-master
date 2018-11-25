package com.dthang.myapp.customview;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;


public class LoadMoreRCV extends RecyclerView.OnScrollListener {

    private static final String TAG = "LoadMoreRCV";

    private ILoadMoreRCV iLoadMoreRCV;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBar;
    private int sumItem;
    private int firstItem;
    private int itemLoad = 6;

    public LoadMoreRCV(ILoadMoreRCV iLoadMoreRCV, RecyclerView.LayoutManager layoutManager) {
        this.iLoadMoreRCV = iLoadMoreRCV;
        this.layoutManager = layoutManager;
        this.progressBar = progressBar;
        Log.e(TAG, "LoadMoreRCV: ");
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        sumItem = layoutManager.getItemCount();

        if (layoutManager instanceof GridLayoutManager) {
            firstItem = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        if (layoutManager instanceof LinearLayoutManager) {
            firstItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        Log.e(TAG, "Tổng số Item: " + sumItem);
        if (sumItem <= (itemLoad + firstItem)) {
            iLoadMoreRCV.loadMore(sumItem);
            Log.e(TAG, "Tổng Item: " + sumItem + " - Item Đã ẩn: " + firstItem);
        }

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);


    }
}
