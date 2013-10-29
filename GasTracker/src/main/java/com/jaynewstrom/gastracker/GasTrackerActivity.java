package com.jaynewstrom.gastracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jaynewstrom.gastracker.dao.DaoSession;
import com.jaynewstrom.gastracker.dao.Price;
import com.jaynewstrom.inject.Injector;
import com.jaynewstrom.keyboard.KeyboardHelper;
import com.jaynewstrom.location.GpsDisabledEvent;
import com.jaynewstrom.location.LocationEvent;
import com.jaynewstrom.location.LocationFinder;
import com.jaynewstrom.log.Log;
import com.jaynewstrom.sync.SyncService;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Views;

public class GasTrackerActivity extends Activity {

    @InjectView(R.id.et_price) EditText priceEditText;
    @InjectView(R.id.dd_fuel_grade) Spinner fuelGradeDropDown;

    @Inject Bus bus;
    @Inject LocationFinder locationFinder;
    @Inject DaoSession daoSession;
    @Inject Log log;

    private FuelGradesAdapter fuelGradesAdapter;
    private LocationEvent locationEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_gas_tracker);

        Injector.inject(this);
        Views.inject(this);

        this.bus.register(this);

        this.setupFuelGradeSpinner();

        if (this.daoSession.getFuelGradeDao().count() == 0l) {
            FuelGradesRetriever retriever = Injector.get(FuelGradesRetriever.class);
            retriever.start();
        }

        this.startService(new Intent(this, SyncService.class));
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.locationFinder.start();
    }

    @Override
    protected void onStop() {
        super.onStop();

        this.locationFinder.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.bus.unregister(this);
        this.locationFinder.stop();
    }

    @Subscribe
    @SuppressWarnings("unused")
    public void locationAvailable(LocationEvent event) {
        this.locationEvent = event;
    }

    @Subscribe
    @SuppressWarnings("unused")
    public void gpsHasBeenDisabled(GpsDisabledEvent event) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.gps_disabled);
        builder.show();
    }

    @Subscribe
    @SuppressWarnings("unused")
    public void fuelGradesChanged(FuelGradesChangedEvent event) {
        this.fuelGradesAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_save)
    @SuppressWarnings("unused")
    void saveButtonClicked() {
        String priceValue = this.priceEditText.getText().toString();
        long fuelGradeId = this.fuelGradeDropDown.getSelectedItemId();

        Pattern pricePattern = Pattern.compile("(\\d)(\\.|,)?(\\d\\d)");
        Matcher matcher = pricePattern.matcher(priceValue);

        if (!matcher.matches()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.invalid_price_format);
            builder.show();
            return;
        }

        if (this.locationEvent == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.location_not_found_try_later);
            builder.show();
            return;
        }

        // change the price into the format the service expects.
        priceValue = matcher.group(1) + "." + matcher.group(3);

        Price price = new Price(priceValue, fuelGradeId, this.locationEvent.longitude, this.locationEvent.latitude);
        this.daoSession.getPriceDao().insert(price);

        this.priceEditText.setText("");
        KeyboardHelper.hide(this.priceEditText);
        Toast toast = Toast.makeText(this, R.string.price_saved, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void setupFuelGradeSpinner() {
        this.fuelGradesAdapter = Injector.get(FuelGradesAdapter.class);
        this.fuelGradeDropDown.setAdapter(this.fuelGradesAdapter);
    }
}
