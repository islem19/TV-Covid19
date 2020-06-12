package dz.islem.tvcovid.ui.detail.country;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.leanback.widget.Presenter;

import dz.islem.tvcovid.R;
import dz.islem.tvcovid.data.model.CountryData;
import dz.islem.tvcovid.util.ResourceCache;
import dz.islem.tvcovid.util.Utils;

public class CountryDetailPresenter extends Presenter {
    private ResourceCache mResourceCache = new ResourceCache();
    private Context mContext;

    public CountryDetailPresenter(Context context) {
        mContext = context;
    }

    @Override
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.country_detail_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {

        TextView overall_active_cases_tv = mResourceCache.getViewById(mResourceCache.getViewById(viewHolder.view, R.id.overall_active_case_item), R.id.active_cases_tv);
        TextView overall_recover_case_tv = mResourceCache.getViewById(mResourceCache.getViewById(viewHolder.view, R.id.overall_recover_case_item), R.id.recover_cases_tv);
        TextView overall_death_case_tv = mResourceCache.getViewById(mResourceCache.getViewById(viewHolder.view, R.id.overall_death_case_item), R.id.death_cases_tv);

        overall_active_cases_tv.setText(Utils.decimalFormat(((CountryData) item).getNbrCases()));
        overall_recover_case_tv.setText(Utils.decimalFormat(((CountryData) item).getNbrRecovered()));
        overall_death_case_tv.setText(Utils.decimalFormat(((CountryData) item).getNbrDeath()));


        TextView today_active_cases_tv = mResourceCache.getViewById(viewHolder.view, R.id.today_active_case_item).findViewById(R.id.active_cases_tv);
        TextView today_recover_case_tv = mResourceCache.getViewById(viewHolder.view, R.id.today_recover_case_item).findViewById(R.id.recover_cases_tv);
        TextView today_death_case_tv = mResourceCache.getViewById(viewHolder.view, R.id.today_death_case_item).findViewById(R.id.death_cases_tv);

        today_active_cases_tv.setText(Utils.decimalFormat(((CountryData) item).getTodayCases()));
        today_recover_case_tv.setText(Utils.decimalFormat(((CountryData) item).getTodayRecovered()));
        today_death_case_tv.setText(Utils.decimalFormat(((CountryData) item).getTodayDeaths()));

    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        // Nothing to do here.
    }
}
