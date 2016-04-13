package com.zpauly.materialcomponents.buttons;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ScrollView;

import com.nineoldandroids.view.ViewPropertyAnimator;
import com.zpauly.scrolllistener.ListViewScrollListener;
import com.zpauly.scrolllistener.NestedScrollViewScrollListener;
import com.zpauly.scrolllistener.RecyclerViewScrollListener;
import com.zpauly.scrolllistener.ScrollViewListener;
import com.zpauly.utils.ButtonImageLoader;

import com.zpauly.floatingactionbutton.R;
/**
 * Created by zpauly on 16-4-6.
 */
public class FloatingActionButton extends ImageButton {
    private static final boolean SHOW = true;
    private static final boolean HIDE = false;

    private Context mContext;

    private int mButtonColor;
    private int mInnerImageId;
    private int mShadowSize;
    private int mColorRipple;
    private int mMarginBottom;

    private int mButtonColorPressed;
    private boolean isMarginSet;
    private boolean buttonVisibleState;

    public FloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initFloatingActionButton(attrs);
    }

    public FloatingActionButton(Context context) {
        super(context);
        mContext = context;
        initFloatingActionButton(null);
    }

    public FloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initFloatingActionButton(attrs);
    }

    @SuppressLint("NewApi")
    private void initFloatingActionButton(AttributeSet attrs) {
        setAttributeSet(attrs);

        mButtonColorPressed = darkenColor(mButtonColor);
        mShadowSize = getResources().getDimensionPixelSize(R.dimen.fab_shadow_size);
        isMarginSet = false;
        buttonVisibleState = SHOW;

        StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(mContext,
                R.anim.fab_press_elevation);
        setStateListAnimator(stateListAnimator);

        setInnerImage();
        drawBackground();
    }

    private void setAttributeSet(AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.FloatingActionButton);
        mButtonColor = a.getColor(R.styleable.FloatingActionButton_button_color, Color.BLACK);
        mInnerImageId = a.getResourceId(R.styleable.FloatingActionButton_inner_image, 0);
        mColorRipple = a.getColor(R.styleable.FloatingActionButton_color_ripple, mButtonColor);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size =  getResources().getDimensionPixelSize(R.dimen.fab_button_size);
        size += mShadowSize * 2;
        setButtonMargins();
        setMeasuredDimension(size, size);
    }

    @SuppressLint("NewApi")
    private void drawBackground() {
            Drawable drawable = createBackgroundDrawable();
            setBackground(drawable);
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    private Drawable createBackgroundDrawable() {
        OvalShape oval = new OvalShape();
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(oval);
        shapeDrawable.getPaint().setColor(mButtonColor);

        Drawable shadowDrawable = getResources().getDrawable(R.drawable.fab_shadow);
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{shadowDrawable, shapeDrawable});
        layerDrawable.setLayerInset(1, mShadowSize, mShadowSize, mShadowSize, mShadowSize);

        RippleDrawable rippleDrawable = new RippleDrawable(new ColorStateList(new int[][]{{}},
                new int[]{mColorRipple}), layerDrawable, null);
        return rippleDrawable;
    }

    private void setButtonMargins() {
        if (isMarginSet)
            return;
        if (getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams layoutParams =
                    (ViewGroup.MarginLayoutParams) getLayoutParams();
            layoutParams.setMargins(layoutParams.leftMargin - mShadowSize,
                    layoutParams.topMargin - mShadowSize,
                    layoutParams.rightMargin - mShadowSize,
                    layoutParams.bottomMargin - mShadowSize);
            mMarginBottom = layoutParams.bottomMargin - mShadowSize;
            requestLayout();
            isMarginSet = true;
        }
    }

    private void setInnerImage() {
        Bitmap bitmap = ButtonImageLoader.decodeSampledBitmapFromRes(getResources(), mInnerImageId, 24, 24);
        setImageBitmap(bitmap);
        setImageResource(mInnerImageId);
    }

    private void buttonShowOrHide(final boolean show, final boolean force) {
        if (buttonVisibleState != show || force) {
            buttonVisibleState = show;
            int height = getHeight();
            if (height == 0 && !force) {
                ViewTreeObserver vto = getViewTreeObserver();
                if (vto.isAlive()) {
                    vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            ViewTreeObserver currentVto = getViewTreeObserver();
                            if (currentVto.isAlive()) {
                                currentVto.removeOnPreDrawListener(this);
                            }
                            buttonShowOrHide(show, force);
                            return true;
                        }
                    });
                    return;
                }
            }
            final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
            float distance = show ? 0 : mMarginBottom + height;
            if (show) {
                ViewPropertyAnimator.animate(this).setDuration(200).setInterpolator(mInterpolator).translationY(-distance);
            } else {
                ViewPropertyAnimator.animate(this).setDuration(200).setInterpolator(mInterpolator).translationY(distance);
            }
        }
    }

    private static int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.9f;
        return Color.HSVToColor(hsv);
    }

    public void attachButtonToRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new ButtonRecyclerViewScrollListener());
    }

    public void attachButtonToListView(AbsListView listView) {
        ButtonListViewScrollListener listener = new ButtonListViewScrollListener();
        listener.setAttachedListView(listView);
        listView.setOnScrollListener(listener);
    }

    public void attachButtonToNestedScrollView(NestedScrollView nestedScrollView) {
        ButtonNestedScrollViewScrollListener listener = new ButtonNestedScrollViewScrollListener();
        nestedScrollView.setOnScrollChangeListener(listener);
    }

    @SuppressWarnings("NewApi")
    public void attachButtonToScrollView(ScrollView scrollView) {
        ButtonScrollViewScrollListener listener = new ButtonScrollViewScrollListener();
        scrollView.setOnScrollChangeListener(listener);
    }

    private class ButtonRecyclerViewScrollListener extends RecyclerViewScrollListener {
        @Override
        public void onScrollUp() {
            buttonShowOrHide(HIDE,false);
        }

        @Override
        public void onScrollDown() {
            buttonShowOrHide(SHOW, false);
        }
    }

    private class ButtonListViewScrollListener extends ListViewScrollListener {
        @Override
        public void onScrollUp() {
            buttonShowOrHide(HIDE,false);
        }

        @Override
        public void onScrollDown() {
            buttonShowOrHide(SHOW, false);
        }
    }

    private class ButtonNestedScrollViewScrollListener extends NestedScrollViewScrollListener {
        @Override
        public void onScrollUp() {
            buttonShowOrHide(HIDE,false);
        }

        @Override
        public void onScrollDown() {
            buttonShowOrHide(SHOW, false);
        }
    }

    private class ButtonScrollViewScrollListener extends ScrollViewListener {
        @Override
        public void onScrollUp() {
            buttonShowOrHide(HIDE,false);
        }

        @Override
        public void onScrollDown() {
            buttonShowOrHide(SHOW, false);
        }
    }
}
