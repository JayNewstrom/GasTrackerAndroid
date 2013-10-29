package com.jaynewstrom.sync;

import android.content.Context;

import com.jaynewstrom.database.DatabaseModule;
import com.jaynewstrom.log.LogModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author jaynewstrom
 * Created on 10/28/13.
 */
@Module(
        includes = {
                LogModule.class,
                DatabaseModule.class
        },
        injects = {
                SyncService.class
        }
)
final class SyncModule {

    private final Context context;

    SyncModule(Context context) {
        this.context = context;
    }

    @Provides @Singleton @SuppressWarnings("unused")
    public Context provideAppContext() {
        return this.context;
    }
}
