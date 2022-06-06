package cn.edu.sict.lc.musicplayer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import cn.edu.sict.lc.R;
import cn.edu.sict.lc.dao.UserAccount;

public class RegisterActivity extends AppCompatActivity {

    private EditText editText_username,editText_password,editText_confirm;
    private Button button_confirm;
    String name = null;
    String password = null;
    String password_confirm = null;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        db = openOrCreateDatabase("users.db",MODE_PRIVATE,null);
        db.execSQL("create table if not exists users(name varchar(12),password varchar(16),primary key(name))");
    }

    private void initView() {
        editText_username = findViewById(R.id.editText_username);
        editText_password = findViewById(R.id.editText_password);
        editText_confirm = findViewById(R.id.editText_confirm);
        button_confirm = findViewById(R.id.button_confirm);

        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmptyForText()){
                    if(isSamePassword()){
                        UserAccount account = new UserAccount(RegisterActivity.this);
                        try {
                            account.getDb().execSQL("insert into users(name,password) values(?,?)",new String[]{name,password});
                            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                            Log.i("注册页面","注册成功，数据已保存");
                            //注册成功，返回登录界面
                            Intent intent = getIntent();
                            intent.putExtra("name",name);
                            intent.putExtra("password",password);
                            setResult(RESULT_OK,intent);
                            finish();
                        }catch (Exception e){
                            Log.e("注册页面","发生异常");
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setTitle("提示").setMessage("系统异常，请稍后重试。").setIcon(R.drawable.ic_alert)
                                    .setCancelable(true).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
                        }
                    }
                }
            }
        });
    }
    //判断输入内容是否为空
    public boolean isEmptyForText(){
        //获取用户名、密码和确认密码信息。
        name = editText_username.getText().toString().trim();
        password = editText_password.getText().toString().trim();
        password_confirm = editText_confirm.getText().toString().trim();
        //
        if(name.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示").setMessage("请输入用户名").setIcon(R.drawable.ic_alert)
                    .setCancelable(true).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            return false;
        }else if (password.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示").setMessage("请输入密码").setIcon(R.drawable.ic_alert)
                    .setCancelable(true).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            return false;
        }else if (password_confirm.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示").setMessage("请确认密码").setIcon(R.drawable.ic_alert)
                    .setCancelable(true).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            return false;
        }
        return true;
    }
    //判断两次输入的密码是否相同
    public boolean isSamePassword(){
        password = editText_password.getText().toString().trim();
        password_confirm = editText_confirm.getText().toString().trim();
        if (password.equals(password_confirm)){
            return true;
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示").setMessage("两次输入的密码不一致，请检查后重新注册").setIcon(R.drawable.ic_alert)
                    .setCancelable(true).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return false;
    }
}