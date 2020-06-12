package dz.islem.tvcovid.data.local;

import android.content.SharedPreferences;

import dz.islem.tvcovid.App;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesHelper {

    private static SharedPreferencesHelper mInstance;
    private SharedPreferences sharedPreferences;
    private static final String COUNTRY_KEY = "county";
    private static final String MY_PREFS_NAME = "covid_pref";

    private SharedPreferencesHelper(){
        sharedPreferences = App.getInstance().getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
    }

    public static synchronized SharedPreferencesHelper getInstance(){
        return mInstance == null ? mInstance = new SharedPreferencesHelper() : mInstance;
    }

    public void setDefaultCountry(String country){
        sharedPreferences.edit()
                .putString(COUNTRY_KEY, country)
                .apply();
    }

    public String getDefaultCountry(){
        return sharedPreferences.getString(COUNTRY_KEY, null);
    }


}
