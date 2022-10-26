package com.peekweb.rxjavaexample.api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.peekweb.rxjavaexample.util.ConnectionClass;
import com.peekweb.rxjavaexample.util.Constatnts;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitAPI {

    private static Retrofit ourInstance;

   /* public static Retrofit getInstance() {


        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .readTimeout(68*2, TimeUnit.SECONDS)
                .connectTimeout(68*2,TimeUnit.SECONDS)
                .writeTimeout(68*2,TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (ourInstance == null)
            ourInstance = new Retrofit.Builder()
                    .baseUrl("https://chat.chatto.jp:20000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        return ourInstance;
    }

    public RetrofitAPI() {
    }*/

    public static Retrofit getInstance() {
     /*   Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://chat.chatto.jp:20000/")
                .client(getUnsafeOkHttpClient().build())
                .build();

        return retrofit;*/

        ConnectionClass connectionClass=new ConnectionClass();
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        /*final OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .readTimeout(68*2, TimeUnit.SECONDS)
                .connectTimeout(68*2,TimeUnit.SECONDS)
                .writeTimeout(68*2,TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();*/
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (ourInstance == null)
            ourInstance = new Retrofit.Builder()
                    .baseUrl("https://chat.chatto.jp:20000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(connectionClass.getUnsafeOkHttpClient())
                    .build();
        return ourInstance;
    }




}
