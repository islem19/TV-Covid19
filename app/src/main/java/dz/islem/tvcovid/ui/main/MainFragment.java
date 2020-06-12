/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package dz.islem.tvcovid.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageView;

import androidx.leanback.app.BackgroundManager;
import androidx.leanback.app.BrowseSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;

import dz.islem.tvcovid.R;
import dz.islem.tvcovid.data.local.SharedPreferencesHelper;
import dz.islem.tvcovid.data.model.Card;
import dz.islem.tvcovid.data.model.CountryData;
import dz.islem.tvcovid.data.model.GlobalData;
import dz.islem.tvcovid.data.network.DataService;
import dz.islem.tvcovid.ui.cards.AboutCardPresenter;
import dz.islem.tvcovid.ui.cards.CountryCardPresenter;
import dz.islem.tvcovid.ui.cards.GlobalCardPresenter;
import dz.islem.tvcovid.ui.cards.presenter.ShadowRowPresenterSelector;
import dz.islem.tvcovid.ui.detail.DetailViewActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;


public class MainFragment extends BrowseSupportFragment {
    private static final String TAG = "MainFragment";
    private static ArrayObjectAdapter mRowsAdapter;
    private static BackgroundManager mBackgroundManager;
    private ArrayObjectAdapter globalRowAdapter;
    private GlobalCardPresenter mGlobalDataPresenter;
    private CountryCardPresenter mCountryCardPresenter;
    private ArrayObjectAdapter profileRowAdapter;
    private CompositeDisposable disposable = new CompositeDisposable();
    private CountryCardPresenter mCountriesDataPresenter;
    private ArrayObjectAdapter countriesRowAdapter;
    private String country;
    private AboutCardPresenter mIconPresenter;
    private ArrayObjectAdapter iconRowAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        RxJavaPlugins.setErrorHandler(throwable -> {}); // nothing or some logging

        setupUI();
        setupAdapter();
        loadData();
        generateIcon();
        setupBackgroundManager();
        setupEventListeners();
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.e(TAG, "onResume: " );
        mBackgroundManager.setDrawable(getResources().getDrawable(R.drawable.global_bg));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) disposable.clear();
    }

    private void setupUI() {
        //setBadgeDrawable(getActivity().getResources().getDrawable(R.drawable.videos_by_google_banner));
        setTitle(getString(R.string.main_title)); // Badge, when set, takes precedent
        // over title
        setHeadersState(HEADERS_DISABLED);
        setHeadersTransitionOnBackEnabled(false);
        // set fastLane (or headers) background color
        setBrandColor(Color.TRANSPARENT);

        prepareEntranceTransition();

    }

    private void setupAdapter() {

        mRowsAdapter = new ArrayObjectAdapter(new ShadowRowPresenterSelector());

        /* global data row */
        HeaderItem globalHeader = new HeaderItem(0, "Global Data");
        mGlobalDataPresenter = new GlobalCardPresenter();
        globalRowAdapter = new ArrayObjectAdapter(mGlobalDataPresenter);
        mRowsAdapter.add(new ListRow(globalHeader,globalRowAdapter));

        /* local country data row */
        HeaderItem profileHeader = new HeaderItem(1, "Local Data");
        mCountryCardPresenter = new CountryCardPresenter();
        profileRowAdapter = new ArrayObjectAdapter(mCountryCardPresenter);
        mRowsAdapter.add(new ListRow(profileHeader,profileRowAdapter));

        /* countries data row */
        HeaderItem countriesHeader = new HeaderItem(2, "Countries Data");
        mCountriesDataPresenter = new CountryCardPresenter();
        countriesRowAdapter = new ArrayObjectAdapter(mCountriesDataPresenter);
        mRowsAdapter.add(new ListRow(countriesHeader,countriesRowAdapter));

        /* about me row */
        HeaderItem iconHeader = new HeaderItem(3, "About Developer");
        mIconPresenter = new AboutCardPresenter(getContext());
        iconRowAdapter = new ArrayObjectAdapter(mIconPresenter);
        mRowsAdapter.add(new ListRow(iconHeader,iconRowAdapter));

        setAdapter(mRowsAdapter);
    }

    private void setupBackgroundManager() {
        mBackgroundManager = BackgroundManager.getInstance(getActivity());
        mBackgroundManager.attach(getActivity().getWindow());
    }

    private void setupEventListeners() {
        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    private void loadData(){
        country = SharedPreferencesHelper.getInstance().getDefaultCountry();

        disposable.add(DataService.getInstance().getGlobalData()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe( globalData -> {
                    globalRowAdapter.add(globalData);
                    startEntranceTransition();
                }, error -> Log.e("tg", "loadData: " + error )));
        disposable.add(DataService.getInstance().getDataByCountry(country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(countryData -> {
                    profileRowAdapter.add(countryData);
                    startEntranceTransition();
                }, error -> {
                    Log.e(TAG, "loadData: " + error );
                }));

        disposable.add(DataService.getInstance().getAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(countriesData -> {
                    for(CountryData countryData : countriesData) countriesRowAdapter.add(countryData);
                    startEntranceTransition();
                }, error -> {
                    Log.e(TAG, "loadData: " + error );
                }));
    }

    private void generateIcon() {
        Card card = new Card();
        card.setType(Card.Type.ICON);
        card.setLocalImageResource("avataaars");
        iconRowAdapter.add(card);
    }

    private class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            Intent intent = new Intent(getContext(), DetailViewActivity.class);
            if (item instanceof GlobalData) {
                intent.putExtra("data",(GlobalData) item);
            } else if (item instanceof CountryData ){
                intent.putExtra("data",(CountryData) item);
                ImageView imageView = ((CountryCardPresenter.ViewHolder) itemViewHolder).getmCardView().getMainImageView();
                imageView.buildDrawingCache ();
                intent.putExtra("image", imageView.getDrawingCache());
            } else if (item instanceof Card ){
                intent.putExtra("data",(Card) item);
            }
            getContext().startActivity(intent);
        }
    }

    private class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {

            if (item instanceof GlobalData) {
                mBackgroundManager.setDrawable(getResources().getDrawable(R.drawable.global_bg));
            }else {
                mBackgroundManager.setDrawable(getResources().getDrawable(R.drawable.corona));
            }
        }
    }

}





