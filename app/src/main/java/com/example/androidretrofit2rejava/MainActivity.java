package com.example.androidretrofit2rejava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.androidretrofit2rejava.Adapter.PostAdapter;
import com.example.androidretrofit2rejava.Model.Post;
import com.example.androidretrofit2rejava.Retrofit.IMYAPI;
import com.example.androidretrofit2rejava.Retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    IMYAPI imyapi;
    RecyclerView recycler_post;
    CompositeDisposable compositeDisposable =new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init API
        Retrofit retrofit= RetrofitClient.getInstance();
        imyapi=retrofit.create(IMYAPI.class);

        //View
        recycler_post=findViewById(R.id.recycler_post);
        recycler_post.setHasFixedSize(true);
        recycler_post.setLayoutManager(new LinearLayoutManager(this));

        fetchdata();
    }

    private void fetchdata() {
        compositeDisposable.add(imyapi.getpost()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Post>>() {
                    @Override
                    public void accept(List<Post> posts) throws Exception {
                        displaydata(posts);
                    }
                }));

    }

    private void displaydata(List<Post> posts) {
        PostAdapter adapter=new PostAdapter(this,posts);
        recycler_post.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
