package cn.edu.sict.lc.musicplayer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import cn.edu.sict.lc.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_username,editText_password;
    private Button button_register,button_login;
    private CheckBox checkBox_rememberPassword;

    //定义常量，用于作为requestCode，确认是否由注册界面返回
    public static final int REGISTER_CODE = 0;
    //定义标志，用于帮助“记住密码”功能，判断本页是否从注册页面返回并接收注册页面的回传数据（此时不能执行记住密码功能）
    int requestCode = -1;

    //数据库
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        db = openOrCreateDatabase("MyUsers.db",MODE_PRIVATE,null);
        db.execSQL("create table if not exists users(name varchar(12),password varchar(16),primary key(name))");
    }
    private void initView() {
        editText_password = findViewById(R.id.editText_password);
        editText_username = findViewById(R.id.editText_username);
        button_register = findViewById(R.id.button_register);
        button_login = findViewById(R.id.button_login);
        checkBox_rememberPassword = findViewById(R.id.checkBox_rememberPassword);

        button_register.setOnClickListener(this);
        button_login.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_register:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivityForResult(intent,REGISTER_CODE);
                break;
            case R.id.button_login:
                //自定义一个登录方法login()
                login();
                break;
            default:
                break;
        }
    }
    //自定义的登录方法login()
    private void login() {
        String name = editText_username.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
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
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
        }
        String password = editText_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)){
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
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
        }
        try {
            Cursor cursor = db.rawQuery("select password from users where name=?",new String[]{name});
            String checkPassword = null;
            if (cursor.getCount()==1){
                cursor.moveToFirst();
                checkPassword = cursor.getString(0);
                if(checkPassword.equals(password)){
                    writeSP(name,password);
                    startActivity(new Intent(this,MainActivity.class));
                }else{
                    Toast.makeText(this,"用户名或密码错误",Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this,"该用户不存在",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示").setMessage("系统异常，请稍后再试").setIcon(R.drawable.ic_alert)
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.requestCode=-1;
        if (resultCode==RESULT_OK){
            if(data!=null){
                editText_username.setText(data.getStringExtra("name"));
                editText_password.setText(data.getStringExtra("password"));
                this.requestCode=requestCode;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(requestCode!=REGISTER_CODE){
            readSP();
        }
    }

    private void readSP() {
        SharedPreferences sp = getSharedPreferences("UserRecord",MODE_PRIVATE);
        if(sp.getBoolean("remember",false)){
            String name = sp.getString("name","");
            String password = sp.getString("password","");
            editText_username.setText(name);
            editText_password.setText(password);
            checkBox_rememberPassword.setChecked(true);
        }else {
            editText_username.setText("");
            editText_password.setText("");
            checkBox_rememberPassword.setChecked(false);
        }
    }
    //定义记住密码的写入方法
    private void writeSP(String name, String password) {
        SharedPreferences sp = getSharedPreferences("UserRecord", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (checkBox_rememberPassword.isChecked()){
            editor.putBoolean("remember",true);
            editor.putString("name",name);
            editor.putString("password",password);
        }else {
            editor.putBoolean("remember",false);
        }
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}