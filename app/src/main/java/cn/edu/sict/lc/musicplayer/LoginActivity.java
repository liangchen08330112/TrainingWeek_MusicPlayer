package cn.edu.sict.lc.musicplayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import cn.edu.sict.lc.R;
import cn.edu.sict.lc.dao.UserAccount;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageView;
    private EditText editText_username;
    private EditText editText_password;
    private Button button_register;
    private Button button_login;
    private TextView textView;
    private CheckBox checkBox_rememberPassword;
    private ImageButton imageButton_qq,imageButton_wechat,imageButton_weibo;

    public static final int REGISTER_CODE = 0;

    int requestCode =-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(requestCode!=REGISTER_CODE){
            readSP();
        }
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.imageView);
        editText_username = (EditText) findViewById(R.id.editText_username);
        editText_password = (EditText) findViewById(R.id.editText_password);
        button_register = (Button) findViewById(R.id.button_register);
        button_login = (Button) findViewById(R.id.button_login);
        imageButton_qq = findViewById(R.id.imageButton_qq);
        imageButton_wechat = findViewById(R.id.imageButton_wechat);
        imageButton_weibo = findViewById(R.id.imageButton_weibo);
        checkBox_rememberPassword = findViewById(R.id.checkBox_rememberPassword);

        button_register.setOnClickListener(this);
        button_login.setOnClickListener(this);
        imageButton_qq.setOnClickListener(this);
        imageButton_wechat.setOnClickListener(this);
        imageButton_weibo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String nm = editText_username.getText().toString().trim();
        String pw = editText_password.getText().toString().trim();
        switch (v.getId()) {
            case R.id.button_register:
                //点击注册按钮，跳转进入注册页面
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.button_login:
                /*
                 * 逻辑：首先判断是否为空，如实为空，则自动报错
                 * 2.在此判断输入的用户是否在数据库里面，并且进行比较，如实正确，则进行登录
                 * */
                UserAccount account = new UserAccount(this);
                if (nm.equals("") || pw.equals("")) {
                    Toast.makeText(this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(this, MainActivity.class));
//                    Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String condition = "用户不存在";

                    Cursor cursor = account.getDb().rawQuery("select name,password from users where name=?", new String[]{nm});
                    while (cursor.moveToNext()) {
                        condition = cursor.getString(1);
                    }

                    cursor.close();//关门游标
                    if (condition.equals("用户不存在")) {
                        Toast.makeText(this, "用户不存在", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        if (condition.equals(pw)){
                            writeSP(nm,pw);
                            startActivity(new Intent(this, MainActivity.class));
                            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case R.id.imageButton_qq:
                Toast.makeText(this,"QQ登录",Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageButton_wechat:
                Toast.makeText(this,"微信登录",Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageButton_weibo:
                Toast.makeText(this,"微博登录",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
            }
        }

    private void writeSP(String nm, String pw) {
        SharedPreferences sp = getSharedPreferences("UserRecord",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if(checkBox_rememberPassword.isChecked()){
            editor.putBoolean("remember",true);
            editor.putString("username",nm);
            editor.putString("password",pw);
        }else {
            editor.putBoolean("remember",false);
        }
        editor.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.requestCode=-1;
        if(resultCode==RESULT_OK){
            if(data!=null){
                editText_username.setText(data.getStringExtra("username"));
                editText_password.setText(data.getStringExtra("password"));
                this.requestCode=requestCode;
            }
        }
    }
    private void readSP() {
        SharedPreferences sp = getSharedPreferences("UserRecord",MODE_PRIVATE);
        if(sp.getBoolean("remember",false)){
            String username = sp.getString("username","");
            String password = sp.getString("password","");
            editText_username.setText(username);
            editText_password.setText(password);
            checkBox_rememberPassword.setChecked(true);
        }else {
            editText_username.setText("");
            editText_password.setText("");
            checkBox_rememberPassword.setChecked(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}