package com.example.kyt.arhongbao.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.kyt.arhongbao.R;
import com.example.kyt.arhongbao.TestFragment;


import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by kyt on 2017/2/22.
 */

public class RenwuActivity extends AppCompatActivity {
    private TextView back,title;
        private ArrayList<String> datas = new ArrayList<String>();
        private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_renwu);
            back = (TextView) findViewById(R.id.common_title_bar_left);
            title = (TextView) findViewById(R.id.common_title);
            title.setText("任务中心");
            //        findViewById(R.id.button).setOnClickListener(
            //                new View.OnClickListener() {
            //
            //                    @Override
            //                    public void onClick(View v) {
            //                        // TODO Auto-generated method stub
            //                        startActivity(new Intent(MainActivity.this,TestActivity.class));
            //                    }
            //                });
            //
            //    }
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                    finish();
                }
            });

        }

        protected void onResume() {
            // TODO Auto-generated method stub
            super.onResume();

            //移除之前的Fragment
            FragmentManager fm = getSupportFragmentManager();
            if (fragments.size() > 0) {
                FragmentTransaction ft = fm.beginTransaction();
                for (Fragment f : this.fragments) {
                    ft.remove(f);
                }
                ft.commit();
                ft = null;
                fm.executePendingTransactions();
            }

            datas.clear();
            fragments.clear();
            //模拟数据
            for (int i = 0; i < 3; i++) {
                String str = "第" + i + "任务";
                //new Random().nextInt(100)
                datas.add(str);
                fragments.add(TestFragment.newInstance(str));
            }

            FragmentPagerAdapter adapter = new GoogleMusicAdapter(fm, fragments);
            ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
            pager.setAdapter(adapter);

            CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
            indicator.setViewPager(pager);
        }

        class GoogleMusicAdapter extends FragmentPagerAdapter {

            private ArrayList<Fragment> fragments;

            public GoogleMusicAdapter(FragmentManager fm, ArrayList<Fragment> fragment) {
                super(fm);
                this.fragments = fragment;
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return datas.get(position).toUpperCase();
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }

        }
    }
