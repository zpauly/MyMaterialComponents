package com.zpauly.materialcomponents.buttons;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

import com.zpauly.floatingactionbutton.R;

import org.w3c.dom.Attr;

/**
 * Created by root on 16-4-14.
 */
public class RaisedButton extends Button {
    private Context mContext;

    public RaisedButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initRaisedButton(attrs);
    }

    public RaisedButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RaisedButton(Context context) {
        this(context, null);
    }

    private void initRaisedButton (AttributeSet attrs) {
        setAttributeSet(attrs);

        initViews();
    }

    private void setAttributeSet(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.RaisedButton);
    }

    private void initViews() {

    }

    private Drawable drawBackground() {
        return  null;
    }

    private void setColorsViaStates() {

    }
}
