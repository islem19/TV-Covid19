package dz.islem.tvcovid.ui.detail.country;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.leanback.app.DetailsFragment;
import androidx.leanback.app.DetailsFragmentBackgroundController;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ClassPresenterSelector;
import androidx.leanback.widget.DetailsOverviewRow;
import androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;

import dz.islem.tvcovid.R;
import dz.islem.tvcovid.data.model.CountryData;
import dz.islem.tvcovid.data.network.DataService;
import dz.islem.tvcovid.ui.cards.CountryCardPresenter;
import dz.islem.tvcovid.ui.detail.DetailViewActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CountryDetailViewFragment extends DetailsFragment {

    private ArrayObjectAdapter mRowsAdapter;
    private final DetailsFragmentBackgroundController mDetailsBackground =
            new DetailsFragmentBackgroundController(this);
    private CountryData countryData;
    private Bitmap imageView;
    private ArrayObjectAdapter countriesRowAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countryData = (CountryData) getActivity().getIntent().getSerializableExtra("data");
        imageView = getActivity().getIntent().getParcelableExtra("image");
        setupUi();
        setupEventListeners();
    }

    private void setupUi() {
        // Setup fragment
        setTitle(countryData.getCountry() + " (" + countryData.getCountryInfo().getIsoName() + ")");

        FullWidthDetailsOverviewRowPresenter rowPresenter = new FullWidthDetailsOverviewRowPresenter(
                new CountryDetailPresenter(getActivity())) {

            @Override
            protected RowPresenter.ViewHolder createRowViewHolder(ViewGroup parent) {
                // Customize Actionbar and Content by using custom colors.
                RowPresenter.ViewHolder viewHolder = super.createRowViewHolder(parent);

                View actionsView = viewHolder.view.
                        findViewById(R.id.details_overview_actions_background);
                actionsView.setBackgroundColor(Color.TRANSPARENT);


                View detailsView = viewHolder.view.findViewById(R.id.details_frame);
                //detailsView.setBackgroundColor(getResources().getColor(R.color.detail_view_background));
                detailsView.setBackground(getResources().getDrawable(R.drawable.detail_bg));
                return viewHolder;
            }
        };

        rowPresenter.setParticipatingEntranceTransition(true);
        prepareEntranceTransition();

        ListRowPresenter shadowDisabledRowPresenter = new ListRowPresenter();
        shadowDisabledRowPresenter.setShadowEnabled(false);

        // Setup PresenterSelector to distinguish between the different rows.
        ClassPresenterSelector rowPresenterSelector = new ClassPresenterSelector();
        rowPresenterSelector.addClassPresenter(DetailsOverviewRow.class, rowPresenter);
        rowPresenterSelector.addClassPresenter(ListRow.class, new ListRowPresenter());
        mRowsAdapter = new ArrayObjectAdapter(rowPresenterSelector);

        // Setup action and detail row.
        DetailsOverviewRow detailsOverview = new DetailsOverviewRow(countryData);
        detailsOverview.setImageBitmap(getActivity(),imageView);

        mRowsAdapter.add(detailsOverview);

        // Setup recommended row
        HeaderItem countriesHeader = new HeaderItem(0, "Recommended");
        CountryCardPresenter mCountriesDataPresenter = new CountryCardPresenter();
        // this is to add cards
        countriesRowAdapter = new ArrayObjectAdapter(mCountriesDataPresenter);
        mRowsAdapter.add(new ListRow(countriesHeader,countriesRowAdapter));

        loadRecommandation();

        setAdapter(mRowsAdapter);
        new Handler().postDelayed(this::startEntranceTransition, 500);
        initializeBackground();
    }

    private void setupEventListeners() {
        setOnItemViewClickedListener(new ItemViewClickedListener());
    }

    private class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            if (item instanceof CountryData ){
                Intent intent = new Intent(getContext(), DetailViewActivity.class);
                intent.putExtra("data",(CountryData) item);
                ImageView imageView = ((CountryCardPresenter.ViewHolder) itemViewHolder).getmCardView().getMainImageView();
                imageView.buildDrawingCache ();
                intent.putExtra("image", imageView.getDrawingCache());
                getContext().startActivity(intent);
                getActivity().finish();
            }
        }
    }

    private void loadRecommandation() {
        Disposable disposable = DataService.getInstance().getAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //convert single to observable
                .flatMapObservable(Observable::fromIterable)
                // filter countries with same continents and remove the same country name
                .filter(countryData -> countryData.getContinent().equals(this.countryData.getContinent())
                        && !countryData.getCountry().equals(this.countryData.getCountry()) )
                // convert back to single list
                .toList()
                .subscribe(countriesData -> {
                    for(CountryData countryData : countriesData) countriesRowAdapter.add(countryData);
                }, error -> {
                });
    }

    private void initializeBackground() {
        mDetailsBackground.enableParallax();
        mDetailsBackground.setCoverBitmap(BitmapFactory.decodeResource(getResources(),
                R.drawable.corona));
    }
}
