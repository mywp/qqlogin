package com.example.scorpio.qqlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scorpio.qq.R;
import com.example.scorpio.qqlogin.utils.Utils;
import com.example.scorpio.qqlogin.utils.UtilsOfSDCard;
import com.example.scorpio.qqlogin.utils.UtilsOfSharedPreference;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private EditText etNumber;
    private EditText etPassword;
    private CheckBox cbRememberPWD;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // /data/data/包名/files

        this.getFilesDir();

        //ctrl =2 停顿一下L
        etNumber = (EditText) findViewById(R.id.et_number);
        etPassword = (EditText) findViewById(R.id.et_password);
        cbRememberPWD = (CheckBox) findViewById(R.id.cb_remerber_pwd);
        Button btnlogin = (Button) findViewById(R.id.btn_login);

        btnlogin.setOnClickListener(this);

        //回显数据
        Map<String,String> userInfoMap= UtilsOfSharedPreference.getUserInfo(this);
        if(userInfoMap!=null){
            etNumber.setText(userInfoMap.get("number"));
            etPassword.setText(userInfoMap.get("password"));
        }
    }

    @Override
    public void onClick(View v) {
        //执行登陆的操作
        
        //1.取出号码和密码
        String number =etNumber.getText().toString();
        String password=etPassword.getText().toString();
        
        if(TextUtils.isEmpty(number)||TextUtils.isEmpty(password)){
            //弹出吐司
            Toast.makeText(this,"请正确输入",Toast.LENGTH_SHORT).show();
            return;
        }
        
        //2.判断记住密码是否被选中
        if(cbRememberPWD.isChecked()){
            //当前需要记住密码
            Log.i(TAG, "记住密码" + number + "，" + password);
            
            boolean isSuccess = UtilsOfSharedPreference.saveUserInfo(this, number, password);
            
            if(isSuccess){
                Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();
            }
        }
        
        //3.登陆成功
        Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();
    }
}
