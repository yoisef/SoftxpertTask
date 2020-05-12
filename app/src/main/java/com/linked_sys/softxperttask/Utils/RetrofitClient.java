package com.linked_sys.softxperttask.Utils;

import android.content.Context;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public final class RetrofitClient {

    Context context;



    //create an object of SingleObject
    private static Retrofit retrofit;

    //make the constructor private so that this class cannot be
    //instantiated
    private RetrofitClient(){



    }

    //Get the only object available


    public static Retrofit getRetrofit(){


        String url=PublicKeys.Base_Url;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                .readTimeout(5, TimeUnit.MINUTES); // read timeout
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);





        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)

                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();


/*
      Retrofit  retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(builderr.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

 */
        return retrofit;
    }
}