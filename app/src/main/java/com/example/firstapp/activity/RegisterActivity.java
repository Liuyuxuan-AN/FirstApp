package com.example.firstapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.firstapp.R;
import com.example.firstapp.db.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

@Route(path = "/com/RegisterActivity")
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "RegisterActivity";

    private EditText et_register_account,et_register_password;
    private Button bt_register_save,bt_register_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        et_register_account= (EditText)findViewById(R.id.et_register_account);
        et_register_password= (EditText) findViewById(R.id.et_register_password);
        bt_register_save= (Button) findViewById(R.id.bt_register_save);
        bt_register_cancel= (Button) findViewById(R.id.bt_register_cancel);
        bt_register_save.setOnClickListener(this);
        bt_register_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_register_save:
                String user_account = et_register_account.getText().toString();
                String user_password = et_register_password.getText().toString();
                if(user_account.isEmpty() || user_password.isEmpty()){
                    Toast.makeText(this,"账号和密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                //使用Bmob提供的注册功能
                User myUser = new User();
                myUser.setUsername(user_account);
                myUser.setPassword(user_password);
                myUser.signUp(new SaveListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if(e == null){
                            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                            Log.e(TAG, e.getMessage());
                        }
                    }
                });
                break;
            case R.id.bt_register_cancel:
                finish();
                break;
        }
    }
}