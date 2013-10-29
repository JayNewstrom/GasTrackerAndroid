package com.jaynewstrom.network;

/**
 * Created by jaynewstrom on 10/17/13.
 */
public interface RequestListener {
    public void requestSuccessful(String response);
    public void requestFailed(int statusCode, String errorMessage);
}
