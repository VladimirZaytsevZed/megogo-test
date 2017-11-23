package com.volodia.megogo_test;

import android.app.Application;
import android.content.Context;

import com.volodia.megogo_test.network.RetrofitApi;
import com.volodia.megogo_test.network.ServiceGenerator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Volodia on 12.11.2017.
 */

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }


    @Provides
    @Singleton
    RetrofitApi provideRetrofitSubmissionClient() {
        return ServiceGenerator.createService(RetrofitApi.class,
                ServiceGenerator.BASE_URL);
    }
}
