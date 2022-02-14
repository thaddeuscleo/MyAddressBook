package com.cleo.myaddressbook.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APICaller {
    private final Retrofit retrofit;
    private static APICaller caller;

    private APICaller() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://u73olh7vwg.execute-api.ap-northeast-2.amazonaws.com/stage2/people/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static APICaller getInstance() {
        if (caller == null) {
            caller = new APICaller();
        }
        return caller;
    }

    public <T> T create(Class<T> type){
        return retrofit.create(type);
    }

}
