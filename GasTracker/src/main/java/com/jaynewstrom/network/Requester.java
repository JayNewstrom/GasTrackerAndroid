package com.jaynewstrom.network;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jaynewstrom.inject.Injector;

import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by jaynewstrom on 10/5/13.
 *
 */
public class Requester implements Response.Listener<String>, Response.ErrorListener {

    @Inject RequestQueue requestQueue;

    private final RequestListener requestListener;
    private final Request request;

    private static final String API_PATH = "http://gas.jaynewstrom.com/api/";

    public Requester(Route route, RequestListener requestListener) {
        this.requestListener = requestListener;
        Injector.inject(this);
        String fullRoute = API_PATH + route.route;
        this.request = new Request(route.requestMethod.method, fullRoute, this, this);
        this.request.setRetryPolicy(route.retryPolicy);
    }

    public Requester(Route route, RequestListener requestListener, JSONObject payload) {
        this.requestListener = requestListener;
        Injector.inject(this);
        String fullRoute = API_PATH + route.route;
        this.request = new Request(route.requestMethod.method, fullRoute, this, this);
        this.request.setRetryPolicy(route.retryPolicy);
        this.request.body = payload;
    }

    public void start() {
        this.requestQueue.add(this.request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        int statusCode = this.getStatusCode(error);
        String errorMessage = this.getErrorMessage(error);
        this.requestListener.requestFailed(statusCode, errorMessage);
    }

    @Override
    public void onResponse(String response) {
        this.requestListener.requestSuccessful(response);
    }

    private String getErrorMessage(VolleyError error) {
        if (error != null) {
            String message = error.getMessage();
            if (message != null) {
                return message;
            }
        }

        return "";
    }

    private int getStatusCode(VolleyError error) {
        if (error != null) {
            if (error.networkResponse != null) {
                return error.networkResponse.statusCode;
            }
        }

        return -1;
    }
}
