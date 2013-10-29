package com.jaynewstrom.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jaynewstrom on 10/10/13.
 */
public class JsonHelper {

    private static final String JSON_KEY_DATA = "data";
    private static final String JSON_KEY_SUCCESS = "success";

    private JsonHelper() {
        // no instances
    }

    public static JSONArray getDataArray(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        return jsonObject.getJSONArray(JSON_KEY_DATA);
    }

    public static boolean getSuccessful(String response) {
        boolean successful = false;

        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getBoolean(JSON_KEY_SUCCESS);
        } catch (JSONException e) {
            // ignored, the response was not successful
        }

        return successful;
    }
}
