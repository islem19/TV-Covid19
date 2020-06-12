package dz.islem.tvcovid.ui.detail.country;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import androidx.leanback.app.DetailsFragment;
import androidx.leanback.app.DetailsFragmentBackgroundController;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ClassPresenterSelector;
import androidx.leanback.widget.DetailsOverviewRow;
import androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.RowPresenter;

import dz.islem.tvcovid.R;
import dz.islem.tvcovid.data.model.CountryData;

public class CountryDetailViewFragment extends DetailsFragment {

    private ArrayObjectAdapter mRowsAdapter;
    private final DetailsFragmentBackgroundController mDetailsBackground =
            new DetailsFragmentBackgroundController(this);
    private CountryData countryData;
    private Bitmap imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countryData = (CountryData) getActivity().getIntent().getSerializableExtra("data");
        imageView = getActivity().getIntent().getParcelableExtra("image");
        setupUi();
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
        mRowsAdapter = new ArrayObjectAdapter(rowPresenterSelector);

        // Setup action and detail row.
        DetailsOverviewRow detailsOverview = new DetailsOverviewRow(countryData);
        detailsOverview.setImageBitmap(getActivity(),imageView);

        mRowsAdapter.add(detailsOverview);

        setAdapter(mRowsAdapter);
        new Handler().postDelayed(this::startEntranceTransition, 500);
        initializeBackground();
    }

    private void initializeBackground() {
        mDetailsBackground.enableParallax();
        mDetailsBackground.setCoverBitmap(BitmapFactory.decodeResource(getResources(),
                R.drawable.corona));
    }
}
