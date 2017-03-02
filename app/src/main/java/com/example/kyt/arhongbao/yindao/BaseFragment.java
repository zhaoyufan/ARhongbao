package com.example.kyt.arhongbao.yindao;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import rx.Subscription;


public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    public Activity context;
    public View view;
    public boolean isFirst = true;
    public Subscription subscription;
    public RelativeLayout title_bar;//顶部标题栏
    public TextView tv_left, tv_title, tv_right;
    public ImageView iv_right;
    public ProgressBar pb;
    public LinearLayoutManager layoutManager;
    public boolean isloading = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        if(view == null){
            view = LayoutInflater.from(context).inflate(getContentView(),null);
            layoutManager = new LinearLayoutManager(context);
            initView();
            initData();
            initClick();
        }
        return view;
    }

    /**
     * 设置布局文件
     * @return
     */
    public abstract int getContentView();

    /**
     * 初始化View
     */
    public abstract void initView();

    /**
     * 设置点击事件
     */
    public abstract void initClick();

    /**
     * 初始化数据，如果当前不在这个界面，那么添加假数据
     */

    /**
     * 初始化数据
     */
    public abstract void initData();

    public View $(int id) {
        return view.findViewById(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
