package com.example.imitatingneteasecloud.activitys;


import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imitatingneteasecloud.R;
import com.example.imitatingneteasecloud.adapters.ListAdapter;

/**
 * 歌单列表界面
 */
public class AlbumListActivity extends BaseActivity {

    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        mRecyclerView=findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new ListAdapter(this,mRecyclerView));
    }
}