package com.english.learnfast.Models;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PreferencesApp {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public PreferencesApp(Context context) {
        this.context = context;

    }

    public void onSaveInt(String key, int dataToSave) {
        preferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        editor = preferences.edit();

        editor.putInt(key, dataToSave);
        editor.apply();
    }

    public int onGetInt(String key) {
        preferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        return preferences.getInt(key, 0);
    }

    public void onSaveLong(String key, Long dataToSave) {
        preferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        editor = preferences.edit();

        editor.putLong(key, dataToSave);
        editor.apply();
    }

    public Long onGetLong(String key) {
        preferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        return preferences.getLong(key, -1);
    }

    public void onSaveBoolean(String key, boolean dataToSave) {
        preferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        editor = preferences.edit();

        editor.putBoolean(key, dataToSave);
        editor.apply();
    }

    public boolean onGetBoolean(String key) {
        preferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    public void onSaveString(String key, String dataToSave) {
        preferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        editor = preferences.edit();

        editor.putString(key, dataToSave);
        editor.apply();
    }


    public String onGetString(String key) {
        preferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        return preferences.getString(key, "empty");
    }


    public void onSaveList(String key, ArrayList<String> dataToSave) {
        preferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        editor = preferences.edit();

        Set<String> set = new HashSet<String>(dataToSave);
        editor.putStringSet(key, set);
        editor.apply();
    }


    public ArrayList<String> onGetList(String key) {
        preferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        Set<String> set = preferences.getStringSet(key, null);
        if (set != null) {
            return new ArrayList<>(set);
        }
        return null;
    }
}