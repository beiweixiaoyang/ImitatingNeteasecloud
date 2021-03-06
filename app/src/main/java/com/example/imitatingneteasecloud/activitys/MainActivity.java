package com.example.imitatingneteasecloud.activitys;


import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imitatingneteasecloud.R;
import com.example.imitatingneteasecloud.adapters.GridAdapter;
import com.example.imitatingneteasecloud.adapters.ListAdapter;
import com.example.imitatingneteasecloud.models.MusicSourceModel;
import com.example.imitatingneteasecloud.utils.RealmUtils;
import com.example.imitatingneteasecloud.views.GridSpaceItem;

/**
 * APP主界面
 */
public class MainActivity extends BaseActivity {

    private RecyclerView mRv_list,mRv_grid;
    private MusicSourceModel musicSourceModel;
    private RealmUtils realmUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realmUtils=new RealmUtils();
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        initNavBar(false,"云音乐",true);
        musicSourceModel=realmUtils.getMusicSource();
        mRv_grid=findViewById(R.id.rv_grid);
        mRv_list=findViewById(R.id.rv_list);
        //为recyclerView设置布局管理器
        mRv_grid.setLayoutManager(new GridLayoutManager(this,3));
        mRv_list.setLayoutManager(new LinearLayoutManager(this));
        //设置adapter
        mRv_grid.setAdapter(new GridAdapter(this,musicSourceModel.getAlbum()));
        mRv_list.setAdapter(new ListAdapter(this,mRv_list,musicSourceModel.getHot()));
        //实现横向item之间的间距  addItemDecoration(添加item的偏移量)
        mRv_grid.addItemDecoration(new GridSpaceItem(5,mRv_grid));
        //取消recyclerView本身的滑动
        mRv_grid.setNestedScrollingEnabled(false);
        mRv_list.setNestedScrollingEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在当前Activity销毁时关闭Realm
        realmUtils.close();
    }
}