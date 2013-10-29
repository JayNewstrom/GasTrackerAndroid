package com.jaynewstrom.gastracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.jaynewstrom.gastracker.dao.DaoSession;
import com.jaynewstrom.gastracker.dao.FuelGrade;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jaynewstrom on 10/9/13.
 */
class FuelGradesAdapter extends BaseAdapter implements SpinnerAdapter {

    private final LayoutInflater layoutInflater;
    private final DaoSession daoSession;
    private List<FuelGrade> fuelGrades;

    @Inject FuelGradesAdapter(Context context, DaoSession daoSession) {
        this.layoutInflater = LayoutInflater.from(context);
        this.daoSession = daoSession;
        this.fuelGrades = this.daoSession.getFuelGradeDao().loadAll();
    }

    @Override
    public void notifyDataSetChanged() {
        this.fuelGrades = this.daoSession.getFuelGradeDao().loadAll();
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.fuelGrades.size();
    }

    @Override
    public FuelGrade getItem(int position) {
        return this.fuelGrades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = (TextView) convertView;
        if (tv == null) {
            tv = (TextView) this.layoutInflater.inflate(R.layout.spinner_fuel_grade, null);
        }

        FuelGrade fuelGrade = this.getItem(position);

        tv.setText(fuelGrade.getName());

        return tv;
    }
}
