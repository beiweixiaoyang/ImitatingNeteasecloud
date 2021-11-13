package com.example.imitatingneteasecloud.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.example.imitatingneteasecloud.R;
import com.example.imitatingneteasecloud.activitys.WelcomeActivity;
import com.example.imitatingneteasecloud.models.MusicModel;
import com.example.imitatingneteasecloud.utils.MediaPlayUtils;
import com.example.imitatingneteasecloud.utils.RealmUtils;

/**
 * 通过service播放音乐
 * 绑定MediaPlayUtils和PlayMusicView
 * 并且设置为前台服务
 */
public class MusicService extends Service {

    private MediaPlayUtils mediaPlayUtils;
    private MusicModel mMusicModel;
    private Notification notification;
    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayUtils=MediaPlayUtils.getInstance(this);
        startForeground();

    }

    public class MusicBinder extends Binder{

        /**
         * 设置音乐
         */
        public void setMusic(MusicModel musicModel){
            mMusicModel=musicModel;
        }
        /**
         * 播放音乐
         * 判断当前是否正在播放音乐
         * 通过判断当前音乐的path和item的path是否相同进行播放音乐或者重新设置path
         */
        public void playMusic(){
            if(mediaPlayUtils.getPath()!=null && mediaPlayUtils.getPath().equals(mMusicModel.getPath()) ){
                mediaPlayUtils.playMusic();
            }else{
                mediaPlayUtils.setPath(mMusicModel.getPath());
                mediaPlayUtils.setOnMediaPlayerListener(new MediaPlayUtils.OnMediaPlayerListener() {
                    @Override
                    public void prepare(MediaPlayer mediaPlayer) {
                        mediaPlayUtils.playMusic();
                    }
                });
            }
        }
        /**
         * 停止播放
         */
        public void stopMusic(){
            mediaPlayUtils.pauseMusic();
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    /**
     * 将服务设置为前台服务
     */
    private void startForeground(){
        PendingIntent pendingIntent=
                PendingIntent.getActivity(this,
                        0,
                        new Intent(this, WelcomeActivity.class),
                        PendingIntent.FLAG_CANCEL_CURRENT);
        String channelId="myMusic";
        String channelName="MyMusicService";
        String Description = "MyMusic";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel=new NotificationChannel(channelId,channelName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(Description);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            channel.setShowBadge(false);
            notification=new Notification.Builder(this, channel.getId())
                    .setContentTitle(mMusicModel.getName())
                    .setContentText(mMusicModel.getAuthor())
                    .setSmallIcon(R.mipmap.logo)
                    .setLargeIcon(BitmapFactory.decodeFile(mMusicModel.getPoster()))
                    .setContentIntent(pendingIntent)
                    .build();
            NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
        }else{
            notification = new Notification.Builder(this)
                    .setContentTitle(mMusicModel.getName())
                    .setContentText(mMusicModel.getAuthor())
                    .setSmallIcon(R.mipmap.logo)
                    .setContentIntent(pendingIntent)
                    .build();
        }
        startForeground(1,notification);
    }
}