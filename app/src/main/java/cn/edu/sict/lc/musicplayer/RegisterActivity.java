package cn.edu.sict.lc.musicplayer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.sict.lc.R;
import cn.edu.sict.lc.userinfo_saving.UserAccount;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText_username;
    private EditText editText_password;
    private EditText editText_confirm;
    private Button button_confirm;
    private ImageButton imageButton_back;
    String name=null;
    String password=null;
    String password_confirm=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        editText_username = (EditText) findViewById(R.id.editText_username);
        editText_password = (EditText) findViewById(R.id.editText_password);
        editText_confirm = (EditText) findViewById(R.id.editText_confirm);
        button_confirm = (Button) findViewById(R.id.button_confirm);
        imageButton_back = (ImageButton) findViewById(R.id.imageButton_back);
        button_confirm.setOnClickListener(this);
        imageButton_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_confirm:
                if(isEmptyForText()){
                    if(isSamePassword()){
                        //将注册信息存入数据库
                        UserAccount account =new UserAccount(this);
                        try {
                            account.getDb().execSQL("insert into users(name,password)values(?,?)", new String[]{name,password});
                            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                            Intent intent = getIntent();
                            intent.putExtra("name",name);
                            intent.putExtra("password",password);
                            setResult(RESULT_OK,intent);
                        } catch (Exception e){
                            Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                        //注册成功，返回登录页面
                        startActivity(new Intent(this,LoginActivity.class));
                    }
                }
                break;
            case R.id.imageButton_back:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            default:
                break;
        }
    }

    //判断输入内容是否为空
    public boolean isEmptyForText(){
        name= editText_username.getText().toString().trim();
        password= editText_password.getText().toString().trim();
        password_confirm= editText_confirm.getText().toString().trim();
//        if(name.isEmpty()|password.isEmpty()|password_confirm.isEmpty()){
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setIcon(R.drawable.ic_alert).setTitle("提示").setMessage("输入内容为空！");
//            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.dismiss();
//                }
//            });
//            AlertDialog dialog = builder.create();
//            dialog.show();
//            return false;
//        }
        if(name.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.ic_alert).setTitle("提示").setMessage("用户名不得为空！");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            return false;
        }else if(password.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.ic_alert).setTitle("提示").setMessage("密码不得为空！");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            return false;
        }else if(password_confirm.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.ic_alert).setTitle("提示").setMessage("您未确认密码！");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
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
        password= editText_password.getText().toString().trim();
        password_confirm= editText_confirm.getText().toString().trim();
        if(password.equals(password_confirm)){
            return true;
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.ic_alert).setTitle("提示").setMessage("两次输入的密码不一致，请重新输入。");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return false;
    }
}