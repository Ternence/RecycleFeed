package com.dianxinos.optimizer.view;

import android.content.Context;
import android.support.design.internal.ForegroundLinearLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * A extension of ForegroundLinearLayout that is always 4:3 aspect ratio.
 */
public class FourThreeLinearLayout extends ForegroundLinearLayout {

    public FourThreeLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int fourThreeHeight = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(widthSpec) * 9 / 16,
                View.MeasureSpec.EXACTLY);
        super.onMeasure(widthSpec, fourThreeHeight);
    }

    @Override
    public boolean hasOverlappingRendering() {
        return false;
    }
}
