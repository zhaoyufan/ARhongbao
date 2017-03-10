package com.example.kyt.arhongbao.api;


import com.example.kyt.arhongbao.model.User;
import com.squareup.okhttp.RequestBody;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface ApiService {

    String API_ROOT = "http://maneng.uicp.io/api/";

    /**
     * 登录
     *
     * @return
     */
    @GET("users")
    Observable<User> login(@QueryMap Map<String, String> maps);

    @Multipart()
    @POST("upload")
    Observable<User> uploadfile(@Part("uploadingFiles\";filename=\"abc.jpg") RequestBody p);

    @Multipart()
    @POST("upload")
    Observable<User> upload(@PartMap Map<String, RequestBody> params);

}
