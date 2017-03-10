package com.example.kyt.arhongbao.model.view;

import com.example.kyt.arhongbao.model.User;

/**
 * Created by ZYF on 2017/3/5.
 */
public interface LoginListenter {
    void success(User user);
    void fail(String message);
}
