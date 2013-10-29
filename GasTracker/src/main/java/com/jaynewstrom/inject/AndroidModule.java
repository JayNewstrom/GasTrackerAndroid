package com.jaynewstrom.inject;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jaynewstrom on 10/4/13.
 */
@Module(
        complete = false,
        library = true
)
class AndroidModule {
    @Provides @Singleton @SuppressWarnings("unused")
    public Context provideAppContext(Application application) {
        return application;
    }
}
