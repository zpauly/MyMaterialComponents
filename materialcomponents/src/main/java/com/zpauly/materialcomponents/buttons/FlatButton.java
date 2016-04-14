package com.zpauly.materialcomponents.buttons;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.zpauly.floatingactionbutton.R;
import com.zpauly.utils.ApiUtils;
import com.zpauly.utils.ColorUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by root on 16-4-12.
 */
public class FlatButton extends Button {
    private Context mContext;

    protected boolean isDarkTheme;
    protected int mTextColor;

    private int[][] states;
    private int[] bgColors;
    private int[] textColors;
    private Map<Integer, Drawable> bgDrawables;

    public FlatButton(Context context) {
        super(context);
        mContext = context;
        initFlatButton(null);
    }

    public FlatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initFlatButton(attrs);
    }

    public FlatButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initFlatButton(attrs);
    }

    @SuppressWarnings("deprecation")
    private void initFlatButton(AttributeSet attrs) {
        setAttributeSet(attrs);
        setStatesAndColors();

        setMinWidth(getResources().getDimensionPixelOffset(R.dimen.fb_min_width));
        setPadding(getResources().getDimensionPixelOffset(R.dimen.fb_horizontal_size),
                0,
                getResources().getDimensionPixelOffset(R.dimen.fb_horizontal_size),
                0);
        drawableBackground();

        setColorsViaStates();
        Drawable drawableNormal = bgDrawables.get(states[0][0]);
        setBackground(drawableNormal);
    }

    private void setAttributeSet(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.FlatButton);
        isDarkTheme = typedArray.getBoolean(R.styleable.FlatButton_button_dark_theme, false);
        mTextColor = typedArray.getColor(R.styleable.FlatButton_button_text_color, -1);
        if (mTextColor == -1) {
            if (isDarkTheme)
                mTextColor = Color.rgb(255, 255, 255);
            else
                mTextColor = Color.rgb(0, 0, 0);
        }
        typedArray.recycle();
    }

    private void drawableBackground() {
        bgDrawables = new HashMap<>();
        if (!ApiUtils.hasLollipopApi()) {
            for (int i = 0; i < 6; i++) {
                bgDrawables.put(states[i][0], drawGradientDrawable(i));
            }
        } else {
            for (int i = 0; i < 6; i++) {
                RippleDrawable rippleDrawable = new RippleDrawable(new ColorStateList(new int[][]{{}}, new int[]{ColorUtils.darkenColor(bgColors[i])}),
                        drawGradientDrawable(i),
                        null);
                bgDrawables.put(states[i][0], rippleDrawable);
            }
        }
    }

    private GradientDrawable drawGradientDrawable(int i) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(4.0f);
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(bgColors[i]);

        return drawable;
    }

    private void setColorsViaStates() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Drawable drawablePressed = bgDrawables.get(states[4][0]);
                        setBackground(drawablePressed);
                        break;
                    case MotionEvent.ACTION_UP:
                        Drawable drawableNormal = bgDrawables.get(states[0][0]);
                        setBackground(drawableNormal);
                        break;
                }
                return false;
            }
        });

        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Drawable drawableFocused = bgDrawables.get(states[2][0]);
                    setBackground(drawableFocused);
                } else {
                    Drawable drawableNormal = bgDrawables.get(states[0][0]);
                    setBackground(drawableNormal);
                }
            }
        });
    }

    private void setStatesAndColors() {
        states = new int[][]{
                new int[]{android.R.attr.state_enabled},
                new int[]{-android.R.attr.state_enabled},
                new int[]{android.R.attr.state_focused},
                new int[]{-android.R.attr.state_focused},
                new int[]{android.R.attr.state_pressed},
                new int[]{-android.R.attr.state_pressed},
        };

        if (!isDarkTheme) {
            bgColors = new int[] {
                    ColorUtils.getColorWithAlpha(Color.rgb(153, 153, 153), 0f),
                    ColorUtils.getColorWithAlpha(Color.rgb(153, 153, 153), 1f),
                    ColorUtils.getColorWithAlpha(Color.rgb(153, 153, 153), 0.2f),
                    ColorUtils.getColorWithAlpha(Color.rgb(153, 153, 153), 0f),
                    ColorUtils.getColorWithAlpha(Color.rgb(153, 153, 153), 0.4f),
                    ColorUtils.getColorWithAlpha(Color.rgb(153, 153, 153), 0f),
            };
            textColors = new int[] {
                    ColorUtils.getColorWithAlpha(mTextColor, 0.87f),
                    ColorUtils.getColorWithAlpha(mTextColor, 0.26f),
                    ColorUtils.getColorWithAlpha(mTextColor, 0.87f),
                    ColorUtils.getColorWithAlpha(mTextColor, 0.87f),
                    ColorUtils.getColorWithAlpha(mTextColor, 0.87f),
                    ColorUtils.getColorWithAlpha(mTextColor, 0.87f),
            };
        } else {
            bgColors = new int[] {
                    ColorUtils.getColorWithAlpha(Color.rgb(204, 204, 204), 0f),
                    ColorUtils.getColorWithAlpha(Color.rgb(204, 204, 204), 0f),
                    ColorUtils.getColorWithAlpha(Color.rgb(204, 204, 204), 0.13f),
                    ColorUtils.getColorWithAlpha(Color.rgb(204, 204, 204), 0f),
                    ColorUtils.getColorWithAlpha(Color.rgb(204, 204, 204), 0.25f),
                    ColorUtils.getColorWithAlpha(Color.rgb(204, 204, 204), 0f),
            };
            textColors = new int[] {
                    ColorUtils.getColorWithAlpha(mTextColor, 1.0f),
                    ColorUtils.getColorWithAlpha(mTextColor, 0.3f),
                    ColorUtils.getColorWithAlpha(mTextColor, 1.0f),
                    ColorUtils.getColorWithAlpha(mTextColor, 1.0f),
                    ColorUtils.getColorWithAlpha(mTextColor, 1.0f),
                    ColorUtils.getColorWithAlpha(mTextColor, 1.0f),
            };
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            Drawable drawableNormal = bgDrawables.get(states[0][0]);
            setBackground(drawableNormal);
        } else {
            Drawable drawableDisabled = bgDrawables.get(states[1][0]);
            setBackground(drawableDisabled);
        }
    }

    @SuppressWarnings("deprecation")
    public void setDarkTheme(boolean isDarkTheme) {
        this.isDarkTheme = isDarkTheme;
        setStatesAndColors();
        invalidate();
    }

    public boolean isDarkTheme() {
        return this.isDarkTheme;
    }

    public void setMaterialTextColor(int colorRes) {
        mTextColor = colorRes;
        setStatesAndColors();
        setTextColor(new ColorStateList(states, textColors));
        invalidate();
    }

    public int getMaterialTextColor() {
        return this.mTextColor;
    }
}
