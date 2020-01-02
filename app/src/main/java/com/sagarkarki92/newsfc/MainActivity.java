package com.sagarkarki92.newsfc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sagarkarki92.newsfc.api.ApiClient;
import com.sagarkarki92.newsfc.models.Article;
import com.sagarkarki92.newsfc.models.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    List<Article> articles;
    NewsAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        getData();
    }

    public void getData(){
        String country = Util.getCountry();
        Log.d("before", "onResponse: before call " );
        ApiClient.getInstance().getNews().enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(!response.isSuccessful() && response.body().getArticles() == null){
                    Toast.makeText(MainActivity.this, "Something went wrong !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                articles = response.body().getArticles();
                adaptor = new NewsAdaptor(articles,MainActivity.this);
                recyclerView.setAdapter(adaptor);
                adaptor.notifyDataSetChanged();
                Log.d("after", "onResponse: " + articles.size());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error:" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
