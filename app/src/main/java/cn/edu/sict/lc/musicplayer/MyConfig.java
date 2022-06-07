package cn.edu.sict.lc.musicplayer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import java.util.ArrayList;

import cn.edu.sict.lc.R;
import cn.edu.sict.lc.adapter.MyConfigGridAdapter;
import cn.edu.sict.lc.bean.MyConfigGridBean;

public class MyConfig extends AppCompatActivity implements View.OnClickListener {

    private Button button_help,button_feedback,button_about,button_logout;
    private GridView gridView_config;
    private ImageButton imageButton_back;
    int[] imgSrcs_config = {R.drawable.ic_skin,R.drawable.ic_rubbish,R.drawable.ic_settings};
    String[] titles_config = {"换肤","清理缓存","设置中心"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_config);
        initView();
    }

    private void initView() {
        button_help = findViewById(R.id.button_help);
        button_feedback = findViewById(R.id.button_feedback);
        button_about = findViewById(R.id.button_about);
        button_logout = findViewById(R.id.button_logout);
        gridView_config = findViewById(R.id.gridView_config);
        imageButton_back = findViewById(R.id.imageButton_back);

        button_help.setOnClickListener(this);
        button_feedback.setOnClickListener(this);
        button_about.setOnClickListener(this);
        button_logout.setOnClickListener(this);
        imageButton_back.setOnClickListener(this);

        initGridView(gridView_config,titles_config,imgSrcs_config);
    }

    private void initGridView(GridView gridView_config, String[] titles_config, int[] imgSrcs_config) {
        ArrayList<MyConfigGridBean> list = initData(imgSrcs_config,titles_config);
        MyConfigGridAdapter adapter = new MyConfigGridAdapter(this,list);
        gridView_config.setAdapter(adapter);
    }

    private ArrayList<MyConfigGridBean> initData(int[] imgSrcs_config, String[] titles_config) {
        ArrayList<MyConfigGridBean> list = new ArrayList<>();
        for (int i=0;i<imgSrcs_config.length;i++){
            MyConfigGridBean bean = new MyConfigGridBean(imgSrcs_config[i],titles_config[i]);
            list.add(bean);
        }
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_logout:
                logout();
                break;
            case R.id.button_about:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("关于").setMessage("音乐播放器 版本V1.0").setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
                break;
            case R.id.imageButton_back:
                startActivity(new Intent(this,MainActivity.class));
                break;
            default:
                break;
        }
    }

    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_ask).setTitle("提示").setMessage("是否退出登录？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(MyConfig.this,LoginActivity.class));
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
    }
}