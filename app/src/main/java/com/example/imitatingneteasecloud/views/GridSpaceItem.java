package com.example.imitatingneteasecloud.views;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridSpaceItem extends RecyclerView.ItemDecoration {

    private int mSpace;
    private RecyclerView mRecyclerView;

    public GridSpaceItem(int mSpace, RecyclerView mRecyclerView) {
        this.mSpace = mSpace;
        this.mRecyclerView = mRecyclerView;
        getRecyclerViewOffsets(mRecyclerView);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //设置item左边的偏移量
        outRect.left=mSpace;
    }

    /**
     * View的margin值
     * 为正：view会距离边界产生一个距离
     * 为负：view会超出边界产生一个距离
     */
    private void getRecyclerViewOffsets(RecyclerView recyclerView){
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) recyclerView.getLayoutParams();
        layoutParams.leftMargin=-mSpace;
        recyclerView.setLayoutParams(layoutParams);
    }
}
