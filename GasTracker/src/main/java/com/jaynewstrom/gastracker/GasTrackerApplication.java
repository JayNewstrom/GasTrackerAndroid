package com.jaynewstrom.gastracker;

import android.app.Application;

import com.jaynewstrom.inject.Injector;

/**
 * Created by jaynewstrom on 10/4/13.
 */
public class GasTrackerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Injector.init(this);
    }

}
