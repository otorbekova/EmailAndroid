package com.example.my_test.tool;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    SharedPreferences preferences;

    public Prefs(Activity activity) {
        preferences = activity.getPreferences(Context.MODE_PRIVATE);
    }

    public void isShown(boolean value) {
        preferences.edit().putBoolean("isShown", value).apply();
    }

    public boolean isShown() {
        return preferences.getBoolean("isShown", false);
    }

}
