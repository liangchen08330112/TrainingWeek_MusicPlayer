package cn.edu.sict.lc.musicplayer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

import cn.edu.sict.lc.R;
import cn.edu.sict.lc.adapter.MyGridAdapter;
import cn.edu.sict.lc.bean.Music;
import cn.edu.sict.lc.bean.MyGridBean;
import cn.edu.sict.lc.util.MusicUtil;

public class MyFragment extends Fragment implements View.OnClickListener {

    private Button button_logout, button_local;
    private GridView gridView_functions,gridView_label;
    int[] imgSrcs_function = {R.drawable.ic_playhistory,R.drawable.ic_download,R.drawable.ic_cloud,
                              R.drawable.ic_purchase,R.drawable.ic_friends,R.drawable.ic_bookmarks,
                              R.drawable.ic_blog,R.drawable.ic_box};
    String[] titles_function = {"最近播放","本地/下载","云盘","已购","我的好友","收藏和赞","我的播客","音乐罐子"};
    int[] imgSrcs_label = {R.drawable.ic_run,R.drawable.ic_study,R.drawable.ic_car,
                           R.drawable.ic_travel,R.drawable.ic_juhui,R.drawable.ic_tea,
                           R.drawable.ic_sleep,R.drawable.ic_book,R.drawable.ic_think};
    String[] titles_label = {"跑步","学习","开车","旅行","聚会","下午茶","睡眠","阅读","沉思"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        button_logout = view.findViewById(R.id.button_logout);
        button_local = view.findViewById(R.id.button_local);
        gridView_functions = view.findViewById(R.id.gridView_functions);
        gridView_label = view.findViewById(R.id.gridView_label);

        button_logout.setOnClickListener(this);
        button_local.setOnClickListener(this);

        //实现两个宫格
        initGridView(gridView_functions,imgSrcs_function,titles_function);
        initGridView(gridView_label,imgSrcs_label,titles_label);

        ArrayList<Music> list = MusicUtil.scan(getActivity());
        button_local.setText("本地音乐：共"+list.size()+"首");
    }

    private void initGridView(GridView gridView, int[] imgSrcs, String[] titles) {
        ArrayList<MyGridBean> list = initData(imgSrcs,titles);
        MyGridAdapter adapter = new MyGridAdapter(getActivity(),list);
        gridView.setAdapter(adapter);
    }

    private ArrayList<MyGridBean> initData(int[] imgSrcs, String[] titles) {
        ArrayList<MyGridBean> list = new ArrayList<>();
        for(int i=0;i<imgSrcs.length;i++){
            MyGridBean bean = new MyGridBean(imgSrcs[i],titles[i]);
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
            case R.id.button_local:
                startActivity(new Intent(getActivity(),MusicListActivity.class));
                break;
            default:
                break;
        }
    }
    //logout()方法退出登录
    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.ic_ask).setTitle("提示").setMessage("是否退出登录？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(getActivity(),LoginActivity.class));
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