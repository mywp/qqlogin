package com.example.scorpio.qqlogin.utils;

import android.content.Context;
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
public class UtilsOfSDCard {

    public static boolean saveUserInfo(Context context, String number, String password) {

        try {
            //判断当前的手机是否有SD卡
            String stata=Environment.getExternalStorageState();
            
            if(!Environment.MEDIA_MOUNTED.equals(stata)){
                //已经挂载了SD卡
                return false;
            }
            
            File sdCardFile =Environment.getExternalStorageDirectory();

            File file =new File(sdCardFile,"login.txt");

            FileOutputStream fos =new FileOutputStream(file);

            String data =number+"##"+password;
            fos.write(data.getBytes());
            fos.flush();
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //返回用户信息
    public static Map<String, String> getUserInfo(Context context) {

        try {
            //判断当前的手机是否有SD卡
            String stata=Environment.getExternalStorageState();

            if(!Environment.MEDIA_MOUNTED.equals(stata)){
                //已经挂载了SD卡
                return null;
            }
            File sdCardFile =Environment.getExternalStorageDirectory();

            File file =new File(sdCardFile,"login.txt");

            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            
            String text =br.readLine();
            if(!TextUtils.isEmpty(text)){
                Map<String,String> userInfoMap=new HashMap<String,String>();
                String[] split=text.split("##");
                userInfoMap.put("number",split[0]);
                userInfoMap.put("password",split[1]);
                return userInfoMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
