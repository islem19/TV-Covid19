package dz.islem.tvcovid.ui.cards.presenter;

import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;

import dz.islem.tvcovid.data.model.Card;

public class ShadowRowPresenterSelector extends PresenterSelector {

    private ListRowPresenter mShadowEnabledRowPresenter = new ListRowPresenter();
    private ListRowPresenter mShadowDisabledRowPresenter = new ListRowPresenter();

    public ShadowRowPresenterSelector() {
        mShadowEnabledRowPresenter.setNumRows(1);
        mShadowDisabledRowPresenter.setShadowEnabled(false);
    }

    @Override public Presenter getPresenter(Object item) {
        if (!(item instanceof Card)) return mShadowDisabledRowPresenter;
        return mShadowEnabledRowPresenter;
    }

    @Override
    public Presenter[] getPresenters() {
        return new Presenter [] {
                mShadowDisabledRowPresenter,
                mShadowEnabledRowPresenter
        };
    }
}
