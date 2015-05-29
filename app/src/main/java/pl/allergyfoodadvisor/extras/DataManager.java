package pl.allergyfoodadvisor.extras;


import android.preference.PreferenceManager;

import pl.allergyfoodadvisor.main.AllergyAdvisor;


public class DataManager {
    private static DataManager mInstance;

    DataManager() {

    }

    public static DataManager getInstance() {
        if (mInstance == null) {
            mInstance = new DataManager();
        }
        return mInstance;
    }

    public DataManager put(String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(AllergyAdvisor.getInstance().getApplicationContext())
                .edit()
                .putString(key, value.toString())
                .commit();
        return this;
    }

    public String get(String key, String defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(AllergyAdvisor.getInstance().getApplicationContext())
                .getString(key, defaultValue);
    }
}