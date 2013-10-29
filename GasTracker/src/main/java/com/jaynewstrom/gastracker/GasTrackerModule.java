package com.jaynewstrom.gastracker;

import dagger.Module;

/**
 * Created by jaynewstrom on 10/7/13.
 * Module to inject our Activities
 */
@Module(
        complete = false,
        injects = {
                GasTrackerActivity.class,
                FuelGradesRetriever.class,
                FuelGradesAdapter.class
        }
)
public class GasTrackerModule {
}
