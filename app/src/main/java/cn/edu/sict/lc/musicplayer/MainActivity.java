package cn.edu.sict.lc.musicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import cn.edu.sict.lc.R;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup_title;
    private RadioButton radioButton_find,radioButton_list,radioButton_my;
    private ViewPager2 viewPager2;
    private ImageView imageView_previous,imageView_next,imageView_play,imageView_list;

    ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        initData();
        HomePagerAdapter adapter = new HomePagerAdapter(MainActivity.this);
        viewPager2.setAdapter(adapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        radioButton_my.setChecked(true);
                        break;
                    case 1:
                        radioButton_list.setChecked(true);
                        break;
                    case 2:
                        radioButton_find.setChecked(true);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        radioGroup_title.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton_my:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.radioButton_list:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.radioButton_find:
                        viewPager2.setCurrentItem(2);
                }
            }
        });
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new FindFragment());
        fragments.add(new ListFragment());
        fragments.add(new MyFragment());
    }

    private void initView() {
        radioGroup_title = findViewById(R.id.radioGroup_title);
        radioButton_list = findViewById(R.id.radioButton_list);
        radioButton_find = findViewById(R.id.radioButton_find);
        radioButton_my = findViewById(R.id.radioButton_my);
        viewPager2 = findViewById(R.id.viewPager2);
        imageView_next = findViewById(R.id.imageView_next);
        imageView_previous = findViewById(R.id.imageView_previous);
        imageView_list = findViewById(R.id.imageView_list);
        imageView_play = findViewById(R.id.imageView_play);

        radioGroup_title.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radioButton_my){
                    startActivity(new Intent(MainActivity.this, MyFragment.class));
                }
            }
        });
    }
    class HomePagerAdapter extends FragmentStateAdapter {

        public HomePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }
        public void onClick(){

        }
    }
}