package com.example.imitatingneteasecloud.activitys;


import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imitatingneteasecloud.R;
import com.example.imitatingneteasecloud.adapters.ListAdapter;
import com.example.imitatingneteasecloud.models.AlbumModel;
import com.example.imitatingneteasecloud.utils.RealmUtils;

/**
 * 歌单列表界面
 */
public class AlbumListActivity extends BaseActivity {


    public static final String ALBUM_ID="albumId";
    private RecyclerView mRecyclerView;
    private String albumId;
    private AlbumModel albumModel;
    private RealmUtils realmUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        initData();
        initView();
    }

    private void initData() {
        albumId=getIntent().getStringExtra(ALBUM_ID);
        realmUtils=new RealmUtils();
        albumModel=realmUtils.getAlbum(albumId);
    }

    /**
     * 初始化view
     */
    private void initView() {
        initNavBar(true,"云音乐",true);
        mRecyclerView=findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new ListAdapter(this,mRecyclerView,albumModel.getList()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmUtils.close();
    }
}