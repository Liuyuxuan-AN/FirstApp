package com.example.firstapp.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.firstapp.R;
import com.example.firstapp.db.User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private final String TAG = "LoginActivity";

    private TextView tv_register;
    private EditText et_login_user,et_login_password;
    private Button bt_login_account,bt_login_mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置显示风格为无标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_login);

        tv_register = (TextView) findViewById(R.id.tv_register);
        bt_login_account = (Button) findViewById(R.id.login_account);
        bt_login_mobile = (Button) findViewById(R.id.login_mobile);
        et_login_user = (EditText) findViewById(R.id.et_login_user);
        et_login_password = (EditText) findViewById(R.id.et_login_password);
        tv_register.setOnClickListener(this);
        bt_login_account.setOnClickListener(this);
        bt_login_mobile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_register:
                ARouter.getInstance().build("/com/RegisterActivity").navigation();
                break;
            case R.id.login_mobile:
                break;
            case R.id.login_account:
                String user_account = et_login_user.getText().toString();
                String user_password = et_login_password.getText().toString();
                //对帐号和密码进行非空判断
                if (user_account.isEmpty() || user_password.isEmpty()) {
                    Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = new User();
                user.setUsername(user_account);
                user.setPassword(user_password);
                user.login(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bombUser, BmobException e) {
                        if(e == null){
                            Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                            //使用ARouter跳转至主页面
                            ARouter.getInstance().build("/com/MainActivity").navigation();
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this,"登陆失败",Toast.LENGTH_SHORT).show();
                            Log.e(TAG, e.getMessage());
                        }
                    }
                });
        }
    }
}