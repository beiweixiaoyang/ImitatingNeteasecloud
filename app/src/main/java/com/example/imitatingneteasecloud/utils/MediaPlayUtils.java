package com.example.imitatingneteasecloud.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/**
 * 音乐播放的工具类
 */
public class MediaPlayUtils {
    private Context mContext;
    private MediaPlayer mMediaPlayer;
    private String mPath;
    private OnMediaPlayerListener onMediaPlayerListener;
    private static MediaPlayUtils mediaPlayUtils;
    public MediaPlayUtils(Context context) {
        this.mContext = context;
        mMediaPlayer=new MediaPlayer();
    }

    public static MediaPlayUtils getInstance(Context context){
        if(mediaPlayUtils == null){
            synchronized (MediaPlayUtils.class){
                if(mediaPlayUtils == null){
                    mediaPlayUtils=new MediaPlayUtils(context);
                }
            }
        }
        return mediaPlayUtils;
    }

    public String getPath(){
        return mPath;
    }

    public void setOnMediaPlayerListener(OnMediaPlayerListener onMediaPlayerListener) {
        this.onMediaPlayerListener = onMediaPlayerListener;
    }

    /**
     * 设置当前播放音乐的资源
     * 1.当前正在播放音乐或者
     */

    public void setPath(String path){
        if(mMediaPlayer.isPlaying() || !path.equals(mPath) ){
            mMediaPlayer.reset();
        }
        mPath=path;
        //设置音乐的path
        try {
            mMediaPlayer.setDataSource(mContext, Uri.parse(mPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //在playMusicView中异步执行prepare
        mMediaPlayer.prepareAsync();
        //监听音乐准备完成
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                if(onMediaPlayerListener!=null){
                    onMediaPlayerListener.prepare(mediaPlayer);
                }
            }
        });
    }

    /**
     * 播放音乐
     */

    public void playMusic(){
        if(mMediaPlayer.isPlaying()) return;
        mMediaPlayer.start();
    }
    /**
     * 停止播放
     */
    public void pauseMusic(){
        mMediaPlayer.pause();
    }

    /**
     * 定义一个接口
     */
    public interface OnMediaPlayerListener{
        void prepare(MediaPlayer mediaPlayer);
    }
}
