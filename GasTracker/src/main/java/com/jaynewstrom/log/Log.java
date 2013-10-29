package com.jaynewstrom.log;

/**
 * Created by jaynewstrom on 10/5/13.
 *
 * Idea was shamelessly taken from https://github.com/JakeWharton/timber
 */
public interface Log {
    public void v(String message);
    public void d(String message);
    public void e(String message, Throwable tr);
    public Log tag(String tag);
}
