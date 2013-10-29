package com.jaynewstrom.inject;

import android.app.Application;

import com.jaynewstrom.database.DatabaseModule;
import com.jaynewstrom.gastracker.GasTrackerModule;
import com.jaynewstrom.location.LocationModule;
import com.jaynewstrom.log.LogModule;
import com.jaynewstrom.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jaynewstrom on 10/4/13.
 * The root module for injection.
 * All other modules should be in the includes of this module
 */
@Module(
        includes = {
                AndroidModule.class,
                BusModule.class,
                LocationModule.class,
                LogModule.class,
                NetworkModule.class,
                DatabaseModule.class,
                GasTrackerModule.class
        }
)
class RootModule {

    private final Application application;

    RootModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton @SuppressWarnings("unused")
    public Application provideApplication() {
        return this.application;
    }
}
