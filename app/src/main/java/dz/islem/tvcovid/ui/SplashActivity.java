package dz.islem.tvcovid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import dz.islem.tvcovid.R;
import dz.islem.tvcovid.data.local.SharedPreferencesHelper;
import dz.islem.tvcovid.data.model.Location;
import dz.islem.tvcovid.data.network.LocationService;
import dz.islem.tvcovid.ui.main.MainActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity  extends AppCompatActivity {
    private final String TAG = "SplashActivity";
    private static final int DELAY = 3000;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Log.e(TAG, "onCreate: " +SharedPreferencesHelper.getInstance().getDefaultCountry());
        if (SharedPreferencesHelper.getInstance().getDefaultCountry() == null) {
            disposable = LocationService.getInstance().getLocation()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(Location::getCountry)
                    .subscribe(country -> SharedPreferencesHelper.getInstance().setDefaultCountry(country),
                            error -> Log.e(TAG, "onCreate: " + error ));
        }

        nextScreen();
    }

    private void nextScreen(){
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            if (disposable != null ) disposable.dispose();
            finish();
        },DELAY);
    }
}
