package com.example.kyt.arhongbao.api;

import android.content.Context;

import rx.Subscriber;

/**
 * Created by My love on 2016/9/19.
 */
public abstract class RxSubscribe<T> extends Subscriber<T> {
    private Context mContext;
    private String msg;

    public RxSubscribe(){

    }

    public RxSubscribe(Context context, String msg) {
        this.mContext = context;
        this.msg = msg;
    }
    public RxSubscribe(Context context){
        this(context,"请稍后...");
    }

    @Override
    public void onStart() {
        super.onStart();
        //有dialog什么的就可以在这里初始化
    }

    @Override
    public void onCompleted() {
        //正常终止
    }

    @Override
    public void onError(Throwable e) {
       _onError(e.toString());
    }

    @Override
    public void onNext(T t) {
        //下一步干什么都可以
        _onNext(t);
    }
    protected abstract void _onNext(T t);
    protected abstract void _onError(String message);
}
