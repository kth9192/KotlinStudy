package com.noname.kotlinstudy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetRetrofit {

    private static NetRetrofit tmpInstance = new NetRetrofit();
    private String url = "https://swapi.co/api/";

    private NetRetrofit() {
    }

    private static class SingletonHolder{
        private static final NetRetrofit INSTANCE = new NetRetrofit();
    }

    public static NetRetrofit getInstance(){
        return SingletonHolder.INSTANCE;
    }

    Gson gson = new GsonBuilder().setLenient().create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public APIInterface getService(){
        return retrofit.create(APIInterface.class);
    }

    public String getUrl() {
        return url;
    }
}
