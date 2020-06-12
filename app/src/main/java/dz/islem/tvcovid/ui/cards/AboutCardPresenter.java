package dz.islem.tvcovid.ui.cards;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.leanback.widget.ImageCardView;

import dz.islem.tvcovid.R;
import dz.islem.tvcovid.ui.cards.presenter.icon.ImageCardViewPresenter;

public class AboutCardPresenter extends ImageCardViewPresenter {
    private static final int ANIMATION_DURATION = 200;

    public AboutCardPresenter(Context context) {
        super(context, R.style.IconCardTheme);
    }

    @Override
    protected ImageCardView onCreateView() {
        final ImageCardView imageCardView = super.onCreateView();
        final ImageView image = imageCardView.getMainImageView();
        image.setBackgroundResource(R.drawable.icon_focused);
        image.getBackground().setAlpha(0);
        imageCardView.setOnFocusChangeListener((v, hasFocus) -> animateIconBackground(image.getBackground(), hasFocus));
        return imageCardView;
    }

    private void animateIconBackground(Drawable drawable, boolean hasFocus) {
        if (hasFocus) {
            ObjectAnimator.ofInt(drawable, "alpha", 0, 255).setDuration(ANIMATION_DURATION).start();
        } else {
            ObjectAnimator.ofInt(drawable, "alpha", 255, 0).setDuration(ANIMATION_DURATION).start();
        }
    }
}
