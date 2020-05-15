package com.example.androidretrofit2rejava.Retrofit;


import com.example.androidretrofit2rejava.Model.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IMYAPI {
    @GET("posts")
    Observable<List<Post>> getpost();
}
