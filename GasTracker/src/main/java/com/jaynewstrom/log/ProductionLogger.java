package com.jaynewstrom.log;

/**
 * Created by jaynewstrom on 10/5/13.
 */
class ProductionLogger implements Log {

    @Override
    public void v(String message) {

    }

    @Override
    public void d(String message) {

    }

    @Override
    public void e(String message, Throwable tr) {

    }

    @Override
    public Log tag(String tag) {
        return this;
    }
}
