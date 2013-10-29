package com.jaynewstrom.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jaynewstrom.gastracker.dao.DaoMaster;
import com.jaynewstrom.gastracker.dao.DaoSession;

/**
 * Created by jaynewstrom on 10/4/13.
 * The database object
 */
class Database {

    final DaoSession daoSession;

    Database(Context context) {
        DaoMaster.DevOpenHelper opener = new DaoMaster.DevOpenHelper(context, "gas-tracker-db", null);
        SQLiteDatabase db = opener.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
}
