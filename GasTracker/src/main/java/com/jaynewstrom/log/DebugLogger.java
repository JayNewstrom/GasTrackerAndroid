package com.jaynewstrom.log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jaynewstrom on 10/5/13.
 */
class DebugLogger implements Log {

    private final Pattern anonymousClass = Pattern.compile("\\$\\d+$");
    private String nextTag;

    @Override
    public void v(String message) {
        android.util.Log.v(this.getTag(), message);
    }

    @Override
    public void d(String message) {
        android.util.Log.d(this.getTag(), message);
    }

    @Override
    public void e(String message, Throwable tr) {
        android.util.Log.e(this.getTag(), message, tr);
    }

    @Override
    public Log tag(String tag) {
        this.nextTag = tag;
        return this;
    }

    private String getTag() {
        String tag = this.nextTag;
        if (tag != null) {
            this.nextTag = null;
            return tag;
        }

        tag = new Throwable().getStackTrace()[3].getClassName();
        Matcher m = this.anonymousClass.matcher(tag);
        if (m != null && m.find()) {
            tag = m.replaceAll("");
        }
        return tag.substring(tag.lastIndexOf('.') + 1);
    }
}
