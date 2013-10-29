package com.jaynewstrom.network;

import com.android.volley.DefaultRetryPolicy;

/**
 * Created by jaynewstrom on 10/7/13.
 * package constants for holding our retry policies
 */
class RetryPolicy extends DefaultRetryPolicy {

    private static final int TIMEOUT_MS = 60000;

    private RetryPolicy(int initialTimeoutMs, int maxNumRetries, float backoffMultiplier) {
        super(initialTimeoutMs, maxNumRetries, backoffMultiplier);
    }

    static final RetryPolicy NONE = new RetryPolicy(
            TIMEOUT_MS,
            1,
            1f);

    static final RetryPolicy MAX = new RetryPolicy(
            TIMEOUT_MS,
            10,
            1.5f);
}
