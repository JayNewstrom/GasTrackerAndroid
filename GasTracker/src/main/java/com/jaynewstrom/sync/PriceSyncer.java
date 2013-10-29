package com.jaynewstrom.sync;

import com.jaynewstrom.gastracker.dao.Price;
import com.jaynewstrom.json.JsonHelper;
import com.jaynewstrom.log.Log;
import com.jaynewstrom.network.RequestListener;
import com.jaynewstrom.network.Requester;
import com.jaynewstrom.network.Route;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jaynewstrom on 10/21/13.
 */
public class PriceSyncer implements RequestListener {

    private final Log log;
    private final Requester requester;
    private final Price price;

    private static final String JSON_KEY_PRICE = "price";

    public PriceSyncer(Log log, Price price) throws JSONException {
        this.log = log;
        this.price = price;

        JSONObject json = new JSONObject();
        json.put(JSON_KEY_PRICE, this.price.toJson());

        this.requester = new Requester(Route.POST_GAS_PRICE, this, json);
    }

    public void start() {
        this.log.d("Starting upload for price id: " + this.price.getId());

        this.price.setUploadStarted(true);
        this.price.update();
        this.requester.start();
    }

    @Override
    public void requestSuccessful(String response) {
        if (JsonHelper.getSuccessful(response)) {
            this.log.d("Upload Complete.");
            this.price.setUploadCompleted(true);
        } else {
            this.log.d("Server responded, but didn't accept the price.");
            this.price.setUploadStarted(false);
        }

        this.price.update();
    }

    @Override
    public void requestFailed(int statusCode, String errorMessage) {
        this.log.d("Failed to upload price.");
        this.price.setUploadStarted(false);
        this.price.update();
    }
}
