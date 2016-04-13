package com.zpauly.materialcomponents.buttons;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
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
    }

    private void setAttributeSet(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.FlatButton);
        isDarkTheme = typedArray.getBoolean(R.styleable.FlatButton_button_dark_theme, false);
        typedArray.recycle();

        /*isEnabled = true;
        isFocused = isFocused();
        isPressed = false;*/
    }
}
