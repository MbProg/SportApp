package org.tud.bp.fitup.Activity;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by M.Braei on 31.01.2017.
 */

public class FireApp extends Application {
    public boolean isInitialized;

    @Override
    public void onCreate() {

        super.onCreate();

        Firebase.setAndroidContext(this);
        isInitialized = true;

    }
}
