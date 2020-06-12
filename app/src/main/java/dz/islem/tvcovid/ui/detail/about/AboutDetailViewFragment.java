package dz.islem.tvcovid.ui.detail.about;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import androidx.leanback.app.DetailsFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ClassPresenterSelector;
import androidx.leanback.widget.DetailsOverviewRow;
import androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.RowPresenter;

import dz.islem.tvcovid.R;
import dz.islem.tvcovid.data.model.Card;

public class AboutDetailViewFragment extends DetailsFragment {

    private ArrayObjectAdapter mRowsAdapter;
    private Card card;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        card = (Card) getActivity().getIntent().getSerializableExtra("data");
        setupUi();
    }

    private void setupUi() {

        FullWidthDetailsOverviewRowPresenter rowPresenter = new FullWidthDetailsOverviewRowPresenter(
                new AboutDetailPresenter(getActivity())) {

            @Override
            protected RowPresenter.ViewHolder createRowViewHolder(ViewGroup parent) {
                // Customize Actionbar and Content by using custom colors.
                RowPresenter.ViewHolder viewHolder = super.createRowViewHolder(parent);

                View actionsView = viewHolder.view.
                        findViewById(R.id.details_overview_actions_background);
                actionsView.setVisibility(View.GONE);


                View detailsView = viewHolder.view.findViewById(R.id.details_frame);
                //detailsView.setBackgroundColor(getResources().getColor(R.color.detail_view_background));
                detailsView.setBackground(getResources().getDrawable(R.drawable.default_background));
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
        DetailsOverviewRow detailsOverview = new DetailsOverviewRow(card);
        int resId = getResources().getIdentifier(card.getLocalImageResource(), "drawable",
                getActivity().getPackageName());
        detailsOverview.setImageDrawable(getResources().getDrawable(resId, null));

        mRowsAdapter.add(detailsOverview);

        setAdapter(mRowsAdapter);
        new Handler().postDelayed(this::startEntranceTransition, 500);
    }


}
