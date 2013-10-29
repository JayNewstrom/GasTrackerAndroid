package com.jaynewstrom.network;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.jaynewstrom.inject.Injector;
import com.jaynewstrom.log.Log;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

/**
 * Created by jaynewstrom on 10/24/13.
 */
public class Request extends StringRequest {

    JSONObject body;

    private static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    @Inject Log log;

    public Request(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);

        Injector.inject(this);
    }

    @Override
    public String getBodyContentType() {

        return PROTOCOL_CONTENT_TYPE;
    }

    @Override
    public byte[] getBody() {
        try {
            return this.body == null ? null : this.body.toString().getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException e) {
            log.e("Failed to get body", e);
            return null;
        }
    }
}
