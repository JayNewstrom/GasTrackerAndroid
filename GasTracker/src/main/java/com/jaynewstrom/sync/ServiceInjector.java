package com.jaynewstrom.sync;

import android.content.Context;

import dagger.ObjectGraph;

/**
 * @author jaynewstrom
 * Created on 10/28/13.
 */
final class ServiceInjector {
    private static ObjectGraph objectGraph;

    public static void initAndInject(Context context) {
        objectGraph = ObjectGraph.create(new SyncModule(context));
        objectGraph.inject(context);
    }
}
