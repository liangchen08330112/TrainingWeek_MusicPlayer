package cn.edu.sict.lc.musicplayer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import cn.edu.sict.lc.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView_find,textView_list,textView_my;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();
    }

    private void initView() {
        textView_find = findViewById(R.id.textView_find);
        textView_list = findViewById(R.id.textView_list);
        textView_my = findViewById(R.id.textView_my);

        textView_my.setOnClickListener(this);
        textView_list.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textView_my:
                startActivity(new Intent(this,MyActivity.class));
                break;
            case R.id.textView_list:
                startActivity(new Intent(this,MusicListActivity.class));
                break;
        }
    }
}