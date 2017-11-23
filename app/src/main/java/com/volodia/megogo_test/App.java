package com.volodia.megogo_test;

import android.app.Application;

/**
 * Created by Volodia on 12.11.2017.
 */

public class App extends Application {

    static App instance;
    private AppComponent appComponent;

    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
