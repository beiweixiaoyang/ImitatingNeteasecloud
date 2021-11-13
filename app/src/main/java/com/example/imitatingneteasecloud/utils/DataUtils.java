package com.example.imitatingneteasecloud.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 读取json文件中的资源
 * 1.通过AssetsManager的open方法打开资源文件，返回InpurStream对象
 * 2.InputStreamReader（将字节转换为字符）
 * 3.BufferReader存储读取的字符信息
 * 4.通过readLine方法逐行读取，添加到stringBuilder中
 */
public class DataUtils {

    public static String getDataResource(Context context,String fileName){
        StringBuilder stringBuilder=new StringBuilder();
        AssetManager assets = context.getAssets();
        try {
            InputStream open = assets.open(fileName);
            InputStreamReader inputStreamReader=new InputStreamReader(open);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String line=null;
            while (((line=bufferedReader.readLine())!=null)){
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
