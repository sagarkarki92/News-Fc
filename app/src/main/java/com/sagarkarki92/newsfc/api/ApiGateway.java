package com.sagarkarki92.newsfc.api;

import com.sagarkarki92.newsfc.models.News;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiGateway {


    @GET("top-headlines")
    Call<News> getNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String key
    );
}
