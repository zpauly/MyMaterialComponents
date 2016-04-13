package com.zpauly.materialcomponents.buttons;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;

import com.zpauly.floatingactionbutton.R;


/**
 * Created by root on 16-4-12.
 */
public class FlatButton extends Button {
    private Context mContext;

    private boolean isDarkTheme;

    private boolean isPressed;
    private boolean isFocused;
    private boolean isEnabled;

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

    private void initFlatButton(AttributeSet attrs) {
        setAttributeSet(attrs);

        setColors();
        states = new int[][] {
                new int[]{android.R.attr.state_enabled},
                new int[]{-android.R.attr.state_enabled},
                new int[]{android.R.attr.state_focused},
                new int[]{-android.R.attr.state_focused},
                new int[]{android.R.attr.state_pressed},
                new int[]{-android.R.attr.state_pressed},
        };
        setPadding(getResources().getDimensionPixelOffset(R.dimen.fb_horizontal_size), 0, getResources().getDimensionPixelOffset(R.dimen.fab_button_size), 0);
        setMinWidth(88);
        setBackground(null);
    }

    private void setAttributeSet(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.FlatButton);
        isDarkTheme = typedArray.getBoolean(R.styleable.FlatButton_button_dark_theme, false);
        typedArray.recycle();

        isEnabled = true;
        isFocused = isFocused();
        isPressed = false;
    }

    private void setColors() {
        if (!isDarkTheme) {
            bgColors = new int[] {
                    Color.argb(0, 153, 153, 153),
                    Color.argb(0, 153, 153, 153),
                    Color.argb(20, 153, 153, 153),
                    Color.argb(0, 153, 153, 153),
                    Color.argb(40, 153, 153, 153),
                    Color.argb(0, 153, 153, 153),
            };
            textColors = new int[] {
                    Color.argb(87, 255, 255, 255),
                    Color.argb(26, 255, 255, 255),
                    Color.argb(87, 255, 255, 255),
                    Color.argb(87, 255, 255, 255),
                    Color.argb(87, 255, 255, 255),
                    Color.argb(87, 255, 255, 255),
            };
        } else {
            bgColors = new int[] {
                    Color.argb(0, 204, 204, 204),
                    Color.argb(0, 204, 204, 204),
                    Color.argb(13, 204, 204, 204),
                    Color.argb(0, 204, 204, 204),
                    Color.argb(25, 204, 204, 204),
                    Color.argb(0, 204, 204, 204),
            };
            textColors = new int[] {
                    Color.argb(100, 255, 255, 255),
                    Color.argb(30, 255, 255, 255),
                    Color.argb(100, 255, 255, 255),
                    Color.argb(100, 255, 255, 255),
                    Color.argb(100, 255, 255, 255),
                    Color.argb(100, 255, 255, 255),
            };
        }
        setTextColor(new ColorStateList(states, textColors));
    }
}
