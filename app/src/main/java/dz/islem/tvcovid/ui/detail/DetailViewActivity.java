package dz.islem.tvcovid.ui.detail;

import android.app.Activity;
import android.os.Bundle;

import androidx.leanback.app.DetailsFragment;

import dz.islem.tvcovid.R;
import dz.islem.tvcovid.data.model.Card;
import dz.islem.tvcovid.data.model.CountryData;
import dz.islem.tvcovid.data.model.GlobalData;
import dz.islem.tvcovid.ui.detail.about.AboutDetailViewFragment;
import dz.islem.tvcovid.ui.detail.country.CountryDetailViewFragment;
import dz.islem.tvcovid.ui.detail.global.GlobalDetailViewFragment;

public class DetailViewActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        DetailsFragment fragment = null;
        if (getIntent().getSerializableExtra("data") instanceof GlobalData){
            fragment = new GlobalDetailViewFragment();
        } else if (getIntent().getSerializableExtra("data") instanceof CountryData){
            fragment = new CountryDetailViewFragment();
        } else if (getIntent().getSerializableExtra("data") instanceof Card ){
            fragment = new AboutDetailViewFragment();
        } else
            finish();

        getFragmentManager().beginTransaction()
                .replace(R.id.details_fragment, fragment)
                .commit();
    }

}