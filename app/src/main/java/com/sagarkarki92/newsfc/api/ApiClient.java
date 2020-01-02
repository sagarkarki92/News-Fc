package com.sagarkarki92.newsfc.api;

import com.sagarkarki92.newsfc.models.News;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://newsapi.org/v2/";
    public static final String API_KEY = "209fb903f3fc45f2bf02b9c467e2ca88";
    public static ApiClient instance;
    public ApiGateway gateway;

    public ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gateway = retrofit.create(ApiGateway.class);
    }

    public static ApiClient getInstance(){
        if(instance == null){
            instance = new ApiClient();
        }
        return instance;
    }

    public Call<News> getNews(){
        return gateway.getNews("gb","sports",API_KEY);
    }

}
