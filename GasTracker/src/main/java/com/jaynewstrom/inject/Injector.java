package com.jaynewstrom.inject;

import android.app.Application;

import dagger.ObjectGraph;

/**
 * Created by jaynewstrom on 10/4/13.
 */
public final class Injector {
    private static ObjectGraph objectGraph;

    public static void init(Application application) {
        objectGraph = ObjectGraph.create(new RootModule(application));
        objectGraph.injectStatics();
    }

    public static void inject(final Object target) {
        objectGraph.inject(target);
    }

    public static <T> T get(Class<T> type) {
        return objectGraph.get(type);
    }
}
