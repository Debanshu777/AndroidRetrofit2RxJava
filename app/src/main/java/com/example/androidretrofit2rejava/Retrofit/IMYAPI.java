package com.example.androidretrofit2rejava.Retrofit;

import android.database.Observable;

import com.example.androidretrofit2rejava.Model.Post;

import java.util.List;

import retrofit2.http.GET;

public interface IMYAPI {
    @GET("posts")
    Observable<List<Post>> getpost();
}
