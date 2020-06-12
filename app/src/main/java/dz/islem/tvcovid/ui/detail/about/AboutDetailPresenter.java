package dz.islem.tvcovid.ui.detail.about;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.leanback.widget.Presenter;

import dz.islem.tvcovid.R;
import dz.islem.tvcovid.util.ResourceCache;

public class AboutDetailPresenter extends Presenter {
    private ResourceCache mResourceCache = new ResourceCache();
    private Context mContext;

    public AboutDetailPresenter(Context context) {
        mContext = context;
    }

    @Override
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.about_detail_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {

    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        // Nothing to do here.
    }
}
