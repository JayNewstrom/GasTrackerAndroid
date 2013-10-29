package com.jaynewstrom.sync;

import com.jaynewstrom.gastracker.dao.DaoSession;
import com.jaynewstrom.gastracker.dao.Price;
import com.jaynewstrom.log.Log;

import org.json.JSONException;

import java.util.List;
import java.util.TimerTask;

/**
 * Created by jaynewstrom on 10/28/13.
 */
class SyncTask extends TimerTask {

    private final DaoSession daoSession;
    private final Log log;

    SyncTask(DaoSession daoSession, Log log) {
        this.daoSession = daoSession;
        this.log = log;
    }

    @Override
    public void run() {
        List<Price> prices = Price.readyForUpload(this.daoSession.getPriceDao());
        for (Price price : prices) {
            try {
                PriceSyncer syncer = new PriceSyncer(this.log, price);
                syncer.start();
            } catch (JSONException e) {
                this.log.e("Failed to create JSON when syncing price", e);
            }
        }
    }
}
