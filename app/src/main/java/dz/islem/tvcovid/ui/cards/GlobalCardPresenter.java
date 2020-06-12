package dz.islem.tvcovid.ui.cards;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import androidx.leanback.widget.BaseCardView;
import androidx.leanback.widget.ImageCardView;
import androidx.leanback.widget.Presenter;

import dz.islem.tvcovid.data.model.GlobalData;

public class GlobalCardPresenter extends Presenter {
    private static Context mContext;
    private static int CARD_WIDTH = 350;
    private static int CARD_HEIGHT = 200;

    public GlobalCardPresenter(){
    }

    private static class ViewHolder extends Presenter.ViewHolder {
        private GlobalData globalData;
        private ImageCardView mCardView;

        public ViewHolder(View view) {
            super(view);
            mCardView = (ImageCardView) view;
        }

        public GlobalData getGlobalData() {
            return globalData;
        }
        public void setGlobalData(GlobalData globalData) {
            this.globalData = globalData;
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
        GlobalData globalData = (GlobalData) item;
        ((GlobalCardPresenter.ViewHolder) viewHolder).setGlobalData(globalData);

        ((GlobalCardPresenter.ViewHolder) viewHolder).mCardView.setTitleText(globalData.getTitle());
        ((GlobalCardPresenter.ViewHolder) viewHolder).mCardView.setContentText(globalData.getDescription());
        ((GlobalCardPresenter.ViewHolder) viewHolder).mCardView.setMainImageDimensions(CARD_WIDTH,CARD_HEIGHT);
        ((GlobalCardPresenter.ViewHolder) viewHolder).mCardView.setMainImage(mContext.getDrawable(globalData.getImageResource(mContext)));
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }


}
