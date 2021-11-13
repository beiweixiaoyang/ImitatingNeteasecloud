package com.example.imitatingneteasecloud.activitys;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.imitatingneteasecloud.R;
import com.example.imitatingneteasecloud.models.MusicModel;
import com.example.imitatingneteasecloud.utils.RealmUtils;
import com.example.imitatingneteasecloud.views.PlayMusicView;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * 播放音乐界面
 */
public class PlayMusicActivity extends BaseActivity {


    public static final String MUSIC_ID="musicId";
    private PlayMusicView playMusicView;
    private ImageView iv_background,iv_back;
    private TextView tv_name,tv_author;
    private String musicId;
    private MusicModel musicModel;
    private RealmUtils realmUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        //全屏显示播放音乐界面
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initData();
        initView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        musicId=getIntent().getStringExtra(MUSIC_ID);
        realmUtils=new RealmUtils();
        musicModel=realmUtils.getMusic(musicId);
    }

    /**
     * 初始化view
     */
    private void initView() {
        playMusicView=findViewById(R.id.playMusicView);
        iv_background=findViewById(R.id.iv_background);
        iv_back=findViewById(R.id.iv_back);
        tv_author=findViewById(R.id.tv_author);
        tv_name=findViewById(R.id.tv_name);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_name.setText(musicModel.getName());
        tv_author.setText(musicModel.getAuthor());
        //对界面的背景图片进行模糊处理
        Glide.with(this)
                .load(musicModel.getPoster())
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25,10)))
                .into(iv_background);
        playMusicView.setMusicModel(musicModel);
        playMusicView.playMusic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmUtils.close();
    }
}