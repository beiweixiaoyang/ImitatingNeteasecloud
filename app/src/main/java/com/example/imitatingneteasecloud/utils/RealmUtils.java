package com.example.imitatingneteasecloud.utils;

import android.content.Context;

import com.example.imitatingneteasecloud.migration.Migration;
import com.example.imitatingneteasecloud.models.AlbumModel;
import com.example.imitatingneteasecloud.models.MusicModel;
import com.example.imitatingneteasecloud.models.MusicSourceModel;
import com.example.imitatingneteasecloud.models.UserModel;

import java.io.FileNotFoundException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Realm数据库帮助类
 */
public class RealmUtils {

    private Realm mRealm;

    public RealmUtils() {
        mRealm=Realm.getDefaultInstance();
    }
    /**
     * 关闭Realm对象
     */
    public void close(){
        mRealm.close();
    }
    /**
     * 保存用户信息
     */
    public void saveUser(UserModel userModel){
        mRealm.beginTransaction();
        mRealm.insert(userModel);
        mRealm.commitTransaction();
    }
    /**
     * 返回所有用户的信息
     */
    public List<UserModel> getAllUser(){
        //加载UserModel模型类
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        RealmResults<UserModel> results = query.findAll();
        return results;
    }
    /**
     * 验证正在登录的用户信息
     */

    public boolean verifyUser(String phone,String password){
        boolean result=false;
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        UserModel userModel =
                query.equalTo("phone", phone)
                        .equalTo("password", password).findFirst();
        if(userModel!=null){
            result=true;
        }
        return  result;
    }

    /**
     * 当Realm数据库发生结构性变化（模型或者模型中的属性发生增删改）
     * 需要进行数据库迁移（升级）
     */
    public static void migration(){
        RealmConfiguration configuration=new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(new Migration())
                .build();
        //为realm设置最新的配置
        Realm.setDefaultConfiguration(configuration);
        //通知数据库需要进行升级
        try {
            Realm.migrateRealm(configuration);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过DateUtils拿到资源文件中的数据
     * 用户登录时，自动获取资源数据
     */
    public void setMusicSource(Context context){
        String dataSource=DataUtils.getDataResource(context,"DataSource.json");
        mRealm.beginTransaction();
        mRealm.createObjectFromJson(MusicSourceModel.class,dataSource);
        mRealm.commitTransaction();
    }
    /**
     * 用户退出登录，删除资源文件
     */

    public void removeMusicSource(){
        mRealm.beginTransaction();
        mRealm.delete(MusicSourceModel.class);
        mRealm.delete(MusicModel.class);
        mRealm.delete(AlbumModel.class);
        mRealm.commitTransaction();
    }
    /**
     * 修改密码
     */
    public void changePassword(String newPassword){
        mRealm.beginTransaction();
        //获取当前用户的信息,通过用户单例中保存的手机号进行匹配
        RealmQuery<UserModel> currentUser = mRealm.where(UserModel.class);
        UserModel userModel =
                currentUser.equalTo("phone", UserInstance.getInstance().getPhone()).findFirst();
        if(userModel!=null){
            userModel.setPassword(newPassword);
        }
        mRealm.commitTransaction();
    }
    public UserModel getCurrentUser(){
        RealmQuery<UserModel> currentUser = mRealm.where(UserModel.class);
        UserModel userModel =
                currentUser.equalTo("phone", UserInstance.getInstance().getPhone()).findFirst();
        return userModel;
    }
    /**
     * 返回音乐源数据
     */
    public MusicSourceModel getMusicSource(){
        return mRealm.where(MusicSourceModel.class).findFirst();
    }
    /**
     * 返回歌单数据
     */
    public AlbumModel getAlbum(String albumId){
        return mRealm.where(AlbumModel.class)
                .equalTo("albumId",albumId)
                .findFirst();
    }

    /**
     * 返回音乐数据
     */
    public MusicModel getMusic(String musicId){
        return mRealm.where(MusicModel.class)
                .equalTo("musicId",musicId)
                .findFirst();
    }
}
