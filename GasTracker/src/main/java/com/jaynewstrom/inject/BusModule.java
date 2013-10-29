package com.jaynewstrom.inject;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jaynewstrom on 10/5/13.
 */
@Module(
        complete = false,
        library = true
)
class BusModule {
    @Provides @Singleton @SuppressWarnings("unused")
    public Bus provideBus() {
        return new Bus();
    }
}
