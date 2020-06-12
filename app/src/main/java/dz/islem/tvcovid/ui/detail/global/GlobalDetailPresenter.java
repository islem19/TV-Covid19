package dz.islem.tvcovid.ui.detail.global;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.leanback.widget.Presenter;

import dz.islem.tvcovid.R;
import dz.islem.tvcovid.data.model.GlobalData;
import dz.islem.tvcovid.util.ResourceCache;
import dz.islem.tvcovid.util.Utils;

public class GlobalDetailPresenter extends Presenter {

    private ResourceCache mResourceCache = new ResourceCache();
    private Context mContext;

    public GlobalDetailPresenter(Context context) {
        mContext = context;
    }

    @Override
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.global_detail_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        TextView active_cases_tv = mResourceCache.getViewById(viewHolder.view, R.id.active_cases_tv);
        TextView recover_case_tv = mResourceCache.getViewById(viewHolder.view, R.id.recover_cases_tv);
        TextView death_case_tv = mResourceCache.getViewById(viewHolder.view, R.id.death_cases_tv);

        active_cases_tv.setText(Utils.decimalFormat(((GlobalData) item).getNbrCases()));
        recover_case_tv.setText(Utils.decimalFormat(((GlobalData) item).getNbrRecovered()));
        death_case_tv.setText(Utils.decimalFormat(((GlobalData) item).getNbrDeaths()));

    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        // Nothing to do here.
    }
}