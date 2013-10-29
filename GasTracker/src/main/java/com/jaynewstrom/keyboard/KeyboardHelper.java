package com.jaynewstrom.keyboard;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by jaynewstrom on 10/17/13.
 */
public class KeyboardHelper {
    private KeyboardHelper() {
        // no instances
    }

    public static void hide(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) view.getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            // failed to hide the keyboard, oh well.
        }
    }
}
