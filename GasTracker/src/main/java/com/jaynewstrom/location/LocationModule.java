package com.jaynewstrom.location;

import com.jaynewstrom.inject.Injector;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jaynewstrom on 10/5/13.
 */
@Module(
        complete = false,
        library = true,
        injects = {
                GpsLocationFinder.class
        }
)
public class LocationModule {
    @Provides @SuppressWarnings("unused")
    public LocationFinder provideLocationFinder() {
        return Injector.get(GpsLocationFinder.class);
    }
}
