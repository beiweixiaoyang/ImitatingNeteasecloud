package com.example.imitatingneteasecloud.views;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.imitatingneteasecloud.R;
import com.example.imitatingneteasecloud.utils.MediaPlayUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 自定义播放音乐的view
 */
public class PlayMusicView extends FrameLayout {

    private MediaPlayUtils mediaPlayUtils;
    private boolean isPlaying;
    private View mView;
    private String mPath;
    private CircleImageView circleImageView;
    private ImageView iv_needle,iv_play_music;
    private Animation play_disc,play_needle,stop_needle;


    public PlayMusicView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }
    private void initView(Context context) {
        mView= LayoutInflater.from(context).inflate(R.layout.playmusicview,this,false);
        mediaPlayUtils=MediaPlayUtils.getInstance(context);
        circleImageView=mView.findViewById(R.id.circleImageView);
        iv_play_music=mView.findViewById(R.id.iv_play_music);
        iv_needle=mView.findViewById(R.id.iv_needle);
        //通过AnimationUtil加载动画文件
        play_needle=AnimationUtils.loadAnimation(context,R.anim.needle_play);
        play_disc=AnimationUtils.loadAnimation(context,R.anim.play_disc);
        stop_needle=AnimationUtils.loadAnimation(context,R.anim.needle_stop);
        circleImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isPlaying){
                    playMusic(mPath);
                }else {
                    stopMusic();
                }
            }
        });
        addView(mView);
    }


    /**
     * 播放音乐
     */
    public void playMusic(String path){
        isPlaying=true;
        iv_play_music.setVisibility(GONE);
        circleImageView.startAnimation(play_disc);
        iv_needle.startAnimation(play_needle);
        mPath=path;
        if(mediaPlayUtils.getPath()!=null && mediaPlayUtils.getPath().equals(mPath) ){
            mediaPlayUtils.playMusic();
        }else{
            mediaPlayUtils.setPath(mPath);
            mediaPlayUtils.setOnMediaPlayerListener(new MediaPlayUtils.OnMediaPlayerListener() {
                @Override
                public void prepare(MediaPlayer mediaPlayer) {
                    mediaPlayUtils.playMusic();
                }
            });
        }
    }
    /**
     * 停止播放音乐
     */
    public void stopMusic(){
        isPlaying=false;
        iv_play_music.setVisibility(VISIBLE);
        circleImageView.clearAnimation();
        iv_needle.clearAnimation();
        iv_needle.startAnimation(stop_needle);
        mediaPlayUtils.pauseMusic();
    }
}
