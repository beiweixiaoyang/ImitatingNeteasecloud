package com.example.imitatingneteasecloud.activitys;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.imitatingneteasecloud.R;
import com.example.imitatingneteasecloud.views.PlayMusicView;

/**
 * 播放音乐界面
 */
public class PlayMusicActivity extends BaseActivity {

    private PlayMusicView playMusicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        //全屏显示播放音乐界面
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
    }

    private void initView() {
        playMusicView=findViewById(R.id.playMusicView);
        playMusicView.playMusic("http://music.163.com/song/media/outer/url?id=569200213.mp3");
    }
}