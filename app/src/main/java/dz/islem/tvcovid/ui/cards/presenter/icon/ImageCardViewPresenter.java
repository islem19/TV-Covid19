package dz.islem.tvcovid.ui.cards.presenter.icon;

import android.content.Context;
import android.view.ContextThemeWrapper;

import androidx.leanback.widget.ImageCardView;

import com.bumptech.glide.Glide;

import dz.islem.tvcovid.R;
import dz.islem.tvcovid.data.model.Card;

public class ImageCardViewPresenter extends AbstractCardPresenter<ImageCardView>{
    public ImageCardViewPresenter(Context context, int cardThemeResId) {
        super(new ContextThemeWrapper(context, cardThemeResId));
    }

    public ImageCardViewPresenter(Context context) {
        this(context, R.style.DefaultCardTheme);
    }

    @Override
    protected ImageCardView onCreateView() {
        ImageCardView imageCardView = new ImageCardView(getContext());
        return imageCardView;
    }

    @Override
    public void onBindViewHolder(Card card, final ImageCardView cardView) {
        cardView.setTag(card);
        cardView.setTitleText(card.getTitle());
        cardView.setContentText(card.getDescription());
        if (card.getLocalImageResourceName() != null) {
            int resourceId = getContext().getResources()
                    .getIdentifier(card.getLocalImageResourceName(),
                            "drawable", getContext().getPackageName());
            Glide.with(getContext())
                    .load(resourceId)
                    .into(cardView.getMainImageView());
        }
    }

}
