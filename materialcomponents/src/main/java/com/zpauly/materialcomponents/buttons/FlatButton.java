package com.zpauly.materialcomponents.buttons;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.IInterface;
import android.util.AttributeSet;
import android.widget.Button;

import com.zpauly.floatingactionbutton.R;
import com.zpauly.utils.ColorUtils;


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

    public FlatButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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

        setBackgroundDrawable(drawableBackground());
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

    private Drawable drawableBackground() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        for (int i = 0; i < 6; i++) {
            stateListDrawable.addState(states[i], drawGradientDrawable(i));
        }

        return stateListDrawable;
    }

    private GradientDrawable drawGradientDrawable(int i) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(4.0f);
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(bgColors[i]);

        return drawable;
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

    @SuppressWarnings("deprecation")
    public void setDarkTheme(boolean isDarkTheme) {
        this.isDarkTheme = isDarkTheme;
        setStatesAndColors();
        setBackgroundDrawable(drawableBackground());
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
