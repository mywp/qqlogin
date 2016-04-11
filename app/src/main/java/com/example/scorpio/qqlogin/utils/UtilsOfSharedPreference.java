package com.example.scorpio.qqlogin.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 保存用户信息到SD卡
 * Created by Scorpio on 16/1/11.
 */
public class UtilsOfSharedPreference {

    public static boolean saveUserInfo(Context context, String number, String password) {

        try {
            // /data/data/包名/share_prefs/login
            SharedPreferences sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);

            //获得一个编译对象
            SharedPreferences.Editor edit = sp.edit();

            //存数据
            edit.putString("number", number);
            edit.putString("paddword", password);

            //提交,数据真正存储起来
            edit.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //返回用户信息
    public static Map<String, String> getUserInfo(Context context) {

        SharedPreferences sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);

        String number = sp.getString("number", null);
        String password = sp.getString("password", null);
        
        if(!TextUtils.isEmpty(number)&&!TextUtils.isEmpty(password)){
            Map<String, String> userInfoMap=new HashMap<String,String>();
            userInfoMap.put("number",number);
            userInfoMap.put("password",password);
            return userInfoMap;
        }
        return null;
    }
}
