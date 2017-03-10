package com.example.kyt.arhongbao.persenter;

import android.util.Log;

import com.example.kyt.arhongbao.api.ApiUtil;
import com.example.kyt.arhongbao.api.RxSubscribe;
import com.example.kyt.arhongbao.model.User;
import com.example.kyt.arhongbao.model.view.LoginListenter;

import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZYF on 2017/3/5.
 */
public class UserPersenter {
    public void login(Map<String,String> maps, final LoginListenter loginListenter){
        ApiUtil.createApiService().login(maps)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscribe<User>() {
                    @Override
                    protected void _onNext(User user) {
                        loginListenter.success(user);
                    }

                    @Override
                    protected void _onError(String message) {
                        Log.i("aaa",message.toString());
                        loginListenter.fail("连接服务器失败");
                    }
                });

    }
}
