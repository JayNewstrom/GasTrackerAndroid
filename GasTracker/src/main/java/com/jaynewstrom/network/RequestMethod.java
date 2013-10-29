package com.jaynewstrom.network;

import com.android.volley.Request;

/**
 * Created by jaynewstrom on 10/7/13.
 * Package level request method to keep track of what is used by volley
 */
enum RequestMethod {
    GET(Request.Method.GET),
    POST(Request.Method.POST);

    final int method;

    private RequestMethod(int method) {
        this.method = method;
    }
}
