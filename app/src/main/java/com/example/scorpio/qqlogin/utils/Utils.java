package com.example.scorpio.qqlogin.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 保存用户信息
 * Created by Scorpio on 16/1/10.
 */
public class Utils {
    public static boolean saveUserInfo(String number, String password) {
        try {
            String path = "/data/data/com.example.scorpio.qqlogin/qqlogin.txt";

            FileOutputStream fos = new FileOutputStream(path);

            //123456##123123
            String data = number + "##" + password;

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
    public static Map<String, String> getUserInfo() {
        try {
            String path = "/data/data/com.example.scorpio.qqlogin/qqlogin.txt";

            FileInputStream fis = new FileInputStream(path);

            //字符流对象
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));


            //123456##123
            String text = reader.readLine();

            if (!TextUtils.isEmpty(text)) {
                String[] split = text.split("##");

                Map<String, String> userInfoMap = new HashMap<String, String>();
                userInfoMap.put("number", split[0]);
                userInfoMap.put("password", split[1]);
                return userInfoMap;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean saveUserInfo(Context context, String number, String password) {
        try {
//            String path = "/data/data/com.example.scorpio.qqlogin/qqlogin.txt";
            
//            File filesDir = context.getFilesDir();

            File filesDir=context.getCacheDir();
            File f = new File(filesDir, "login.txt");
            FileOutputStream fos = new FileOutputStream(f);

            //123456##123123
            String data = number + "##" + password;

            fos.write(data.getBytes());
            fos.flush();
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Map<String, String> getUserInfo(Context context) {
        try {
//            String path = "/data/data/com.example.scorpio.qqlogin/qqlogin.txt";

//            File filesDir = context.getFilesDir();

            File filesDir=context.getCacheDir();
            File f = new File(filesDir, "login.txt");

            FileInputStream fis = new FileInputStream(f);

            //字符流对象
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));


            //123456##123
            String text = reader.readLine();

            if (!TextUtils.isEmpty(text)) {
                String[] split = text.split("##");

                Map<String, String> userInfoMap = new HashMap<String, String>();
                userInfoMap.put("number", split[0]);
                userInfoMap.put("password", split[1]);
                return userInfoMap;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
