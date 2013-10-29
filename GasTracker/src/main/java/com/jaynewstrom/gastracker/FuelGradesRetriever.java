package com.jaynewstrom.gastracker;

import com.jaynewstrom.gastracker.dao.DaoSession;
import com.jaynewstrom.gastracker.dao.FuelGrade;
import com.jaynewstrom.gastracker.dao.FuelGradeDao;
import com.jaynewstrom.json.JsonHelper;
import com.jaynewstrom.log.Log;
import com.jaynewstrom.network.RequestListener;
import com.jaynewstrom.network.Requester;
import com.jaynewstrom.network.Route;

import org.json.JSONArray;
import org.json.JSONException;

import javax.inject.Inject;

/**
 * Created by jaynewstrom on 10/9/13.
 * An object that retrieves the fuel grades from our web api.
 * Once retrieved posts a {@link FuelGradesChangedEvent}
 */
class FuelGradesRetriever implements RequestListener {

    private final Log log;
    private final Requester requester;
    private final FuelGradeDao fuelGradeDao;


    @Inject FuelGradesRetriever(Log log, DaoSession daoSession) {
        this.log = log;
        this.requester = new Requester(Route.GET_FUEL_GRADES, this);
        this.fuelGradeDao = daoSession.getFuelGradeDao();
    }

    void start() {
        this.requester.start();
    }

    @Override
    public void requestSuccessful(String response) {
        try {
            JSONArray fuelGradesJsonArray = JsonHelper.getDataArray(response);
            FuelGrade.insert(fuelGradesJsonArray, this.fuelGradeDao);
        } catch (JSONException e) {
            this.log.e("Failed to parse Fuel Grades", e);
        }
    }

    @Override
    public void requestFailed(int statusCode, String errorMessage) {
        this.log.d("Fuel Grade Request Failed. Status Code: " + statusCode + " - ErrorMessage: " + errorMessage);
    }
}
