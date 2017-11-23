package com.volodia.megogo_test.network;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit service generator. Create service whenever you need
 * to send some request to server API.
 *
 */
public class ServiceGenerator {

    private static final int TIMEOUT = 60;
    public static final String BASE_URL = "https://api.themoviedb.org/";
    public static final String API_KEY = "1d562aa78d676354df516d2c0858bc5e";
    public static final String LANGUAGE = "en-US";

    public static String RES_BASE_URL = "http://image.tmdb.org/t/p/";
    public static String BACKDROP_SIZE_W300 = "w300";
    public static String BACKDROP_SIZE_W1280 = "w1280";

    public static <S> S createService(Class<S> serviceClass, String url) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(getLoggingInterceptor())
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(serviceClass);
    }


    private static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logger;
    }


}
