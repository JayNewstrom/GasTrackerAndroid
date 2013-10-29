package com.jaynewstrom.database;

import android.content.Context;

import com.jaynewstrom.gastracker.dao.DaoSession;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jaynewstrom on 10/7/13.
 * Injection module for accessing the database.
 */
@Module(
        complete = false,
        library = true
)
public class DatabaseModule {
    @Provides @Singleton @SuppressWarnings("unused")
    public DaoSession provideDaoSession(Context context) {
        Database database = new Database(context);
        return database.daoSession;
    }
}
