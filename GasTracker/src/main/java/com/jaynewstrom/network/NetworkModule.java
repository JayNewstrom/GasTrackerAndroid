package com.jaynewstrom.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jaynewstrom on 10/5/13.
 */
@Module(
        library = true,
        complete = false,
        injects = {
                Requester.class,
                Request.class
        }
)
public class NetworkModule {
    @Provides @Singleton @SuppressWarnings("unused")
    public RequestQueue provideRequestQueue(Context context) {
        return Volley.newRequestQueue(context);
    }
}
