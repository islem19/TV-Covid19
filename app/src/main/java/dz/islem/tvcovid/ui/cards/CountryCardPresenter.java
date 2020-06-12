package dz.islem.tvcovid.ui.cards;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import androidx.leanback.widget.BaseCardView;
import androidx.leanback.widget.ImageCardView;
import androidx.leanback.widget.Presenter;

import com.bumptech.glide.Glide;

import dz.islem.tvcovid.R;
import dz.islem.tvcovid.data.model.CountryData;

public class CountryCardPresenter extends Presenter {
    private static Context mContext;
    private static int CARD_WIDTH = 350;
    private static int CARD_HEIGHT = 200;

    public CountryCardPresenter(){
    }

    public static class ViewHolder extends Presenter.ViewHolder {
        private CountryData countryData;
        private ImageCardView mCardView;

        public ViewHolder(View view) {
            super(view);
            mCardView = (ImageCardView) view;
        }

        public CountryData getCountryData() {
            return countryData;
        }
        public void setCountryData(CountryData countryData) {
            this.countryData = countryData;
        }
        public ImageCardView getmCardView() {
            return mCardView;
        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        mContext = parent.getContext();
        ImageCardView mCardView = new ImageCardView(mContext);
        mCardView.setCardType(BaseCardView.CARD_TYPE_INFO_UNDER);
        mCardView.setInfoVisibility(BaseCardView.CARD_REGION_VISIBLE_ALWAYS);
        mCardView.setFocusable(true);
        mCardView.setFocusableInTouchMode(true);
        mCardView.setBackgroundColor(Color.WHITE);
        return new ViewHolder(mCardView);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        CountryData countryData = (CountryData) item;
        ((CountryCardPresenter.ViewHolder) viewHolder).setCountryData(countryData);

        ((CountryCardPresenter.ViewHolder) viewHolder).mCardView.setTitleText(countryData.getCountry());
        ((CountryCardPresenter.ViewHolder) viewHolder).mCardView.setContentText(countryData.getDescription());
        ((CountryCardPresenter.ViewHolder) viewHolder).mCardView.setMainImageDimensions(CARD_WIDTH,CARD_HEIGHT);

        Glide.with(mContext)
                .load(countryData.getCountryInfo().getFlag()) // image url
                .error(R.drawable.global_icon)  // any image in case of error
                .override(CARD_WIDTH, CARD_HEIGHT) // resizing
                .centerCrop()
                .into(((CountryCardPresenter.ViewHolder) viewHolder).mCardView.getMainImageView());  // imageview object
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }
}
