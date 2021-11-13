package com.example.imitatingneteasecloud.views;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.imitatingneteasecloud.R;
import com.example.imitatingneteasecloud.models.MusicModel;
import com.example.imitatingneteasecloud.services.MusicService;
import com.example.imitatingneteasecloud.utils.MediaPlayUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 自定义播放音乐的view
 */
public class PlayMusicView extends FrameLayout {

    private Context mContext;
    private boolean isPlaying,isBindService;
    private View mView;
    private MusicService.MusicBinder musicBinder;
    private MusicModel mMusicModel;
    private Intent mServiceIntent;
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
        mContext=context;
        mView= LayoutInflater.from(context).inflate(R.layout.playmusicview,this,false);
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
                    playMusic();
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
    public void playMusic(){
        isPlaying=true;
        iv_play_music.setVisibility(GONE);
        circleImageView.startAnimation(play_disc);
        iv_needle.startAnimation(play_needle);
        startMusicService();
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
        musicBinder.stopMusic();
    }
    public void setMusicModel(MusicModel musicModel){
        mMusicModel=musicModel;
        setCircleImageView();
    }
    /**
     * 设置光盘中的图片
     */
    public void setCircleImageView(){
        Glide.with(mContext)
                .load(mMusicModel.getPoster())
                .into(circleImageView);
    }
    /**
     * 启动服务
     */
    public void startMusicService(){
        if(mServiceIntent ==null){
            mServiceIntent=new Intent(mContext,MusicService.class);
            mContext.startService(mServiceIntent);
        }else{
            musicBinder.playMusic();
        }
        if(!isBindService){
            isBindService=true;
            mContext.bindService(mServiceIntent,connection,Context.BIND_AUTO_CREATE);
        }
    }
    ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicBinder= (MusicService.MusicBinder) service;
            musicBinder.setMusic(mMusicModel);
            musicBinder.playMusic();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    /**
     * 如果已经绑定服务，则解绑服务,在播放音乐的activity销毁的时候调用
     */
    public void destroy(){
        if(isBindService){
            isBindService=false;
            mContext.unbindService(connection);
        }
    }
}
