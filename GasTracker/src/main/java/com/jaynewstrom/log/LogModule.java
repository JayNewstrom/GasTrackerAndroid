package com.jaynewstrom.log;

import com.jaynewstrom.gastracker.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jaynewstrom on 10/5/13.
 */
@Module(
        library = true
)
public class LogModule {

    @Provides @Singleton @SuppressWarnings("unused")
    public Log provideLogger() {
        if (BuildConfig.DEBUG) {
            return new DebugLogger();
        } else {
            return new ProductionLogger();
        }
    }
}
