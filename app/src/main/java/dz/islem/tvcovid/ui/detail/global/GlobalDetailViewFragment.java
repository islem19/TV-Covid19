package dz.islem.tvcovid.ui.detail.global;

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
import dz.islem.tvcovid.data.model.GlobalData;

public class GlobalDetailViewFragment extends DetailsFragment {

    private ArrayObjectAdapter mRowsAdapter;
    private final DetailsFragmentBackgroundController mDetailsBackground =
            new DetailsFragmentBackgroundController(this);
    private GlobalData globalData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalData = (GlobalData) getActivity().getIntent().getSerializableExtra("data");
        setupUi();
    }

    private void setupUi() {
        // Load the card we want to display from a JSON resource. This JSON data could come from
        // anywhere in a real world app, e.g. a server.

        // Setup fragment
        setTitle(globalData.getTitle());

        FullWidthDetailsOverviewRowPresenter rowPresenter = new FullWidthDetailsOverviewRowPresenter(
                new GlobalDetailPresenter(getActivity())) {

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

        // Setup detail row.
        DetailsOverviewRow detailsOverview = new DetailsOverviewRow(globalData);
        int imageResId = globalData.getImageResource(getActivity());
        detailsOverview.setImageDrawable(getResources().getDrawable(imageResId, null));

        mRowsAdapter.add(detailsOverview);

        setAdapter(mRowsAdapter);
        new Handler().postDelayed(this::startEntranceTransition, 500);
        initializeBackground();
    }

    private void initializeBackground() {
        mDetailsBackground.enableParallax();
        mDetailsBackground.setCoverBitmap(BitmapFactory.decodeResource(getResources(),
                R.drawable.global_bg));
    }


}
