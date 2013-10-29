package com.jaynewstrom.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.jaynewstrom.gastracker.dao.DaoSession;
import com.jaynewstrom.gastracker.dao.Price;
import com.jaynewstrom.log.Log;

import java.util.Timer;

import javax.inject.Inject;

/**
 * Created by jaynewstrom on 10/21/13.
 */
public class SyncService extends Service {

    @Inject Log log;
    @Inject DaoSession daoSession;

    private static final Timer timer = new Timer();

    private static final long SYNC_PERIOD = 18000000l; // 5 hours in milliseconds
    private static final long SYNC_INITIAL_DELAY = 3600000l; // 1 hour in milliseconds

    @Override
    public void onCreate() {
        super.onCreate();

        ServiceInjector.initAndInject(this);

        Price.cleanupIncompleteUploads(this.daoSession.getPriceDao());

        SyncTask syncTask = new SyncTask(this.daoSession, this.log);
        timer.schedule(syncTask, SYNC_INITIAL_DELAY, SYNC_PERIOD);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
