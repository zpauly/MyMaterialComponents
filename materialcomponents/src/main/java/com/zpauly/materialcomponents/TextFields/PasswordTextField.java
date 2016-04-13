package com.zpauly.materialcomponents.TextFields;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zpauly.floatingactionbutton.R;


/**
 * Created by root on 16-4-12.
 */
public class PasswordTextField extends NormalTextField {
    private Context mContext;

    private int mVisiableResource;
    private int mVisiableOffResource;

    private boolean isPasswordVisiable;

    protected ImageView mVisiableImage;

    public PasswordTextField(Context context) {
        super(context);
        mContext = context;
        initPasswordTextField(null);
    }

    public PasswordTextField(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPasswordTextField(attrs);
    }

    public PasswordTextField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initPasswordTextField(attrs);
    }

    public PasswordTextField(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        initPasswordTextField(attrs);
    }

    private void initPasswordTextField(AttributeSet attrs) {
        mVisiableImage = new ImageView(mContext);

        setAttributeSet(attrs);
        initViews();
    }

    private void initViews() {
        initVisiableImage();
        hidePassword();
    }

    private void setAttributeSet(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.PasswordTextField);
        mVisiableResource = typedArray.getInt(R.styleable.PasswordTextField_visibility_image, -1);
        mVisiableOffResource = typedArray.getInt(R.styleable.PasswordTextField_visibility_off_image, -1);
        typedArray.recycle();

        isPasswordVisiable = true;
    }

    private void initVisiableImage() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        addImage(R.drawable.ic_visibility_off_24dp);
        mVisiableImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisiable) {
                    hidePassword();
                    isPasswordVisiable = false;
                    mEditText.setSelection(mEditText.getText().toString().length());
                }
                else {
                    showPassword();
                    isPasswordVisiable = true;
                    mEditText.setSelection(mEditText.getText().toString().length());
                }
            }
        });
        mEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    mVisiableImage.setVisibility(VISIBLE);
                else
                    mVisiableImage.setVisibility(INVISIBLE);
            }
        });
        mEditLayout.invalidate();
    }

    private void showPassword() {
        mEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        setVisibilityImage();
    }

    private void hidePassword() {
        mEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        setVisibilityOffImage();
    }

    @SuppressWarnings("deprecation")
    private void setVisibilityImage() {
        mVisiableImage.setImageResource(R.drawable.ic_visibility_24dp);
        mVisiableImage.setAlpha(0.54f);
    }

    @SuppressWarnings("deprecation")
    private void setVisibilityOffImage() {
        mVisiableImage.setImageResource(R.drawable.ic_visibility_off_24dp);
        mVisiableImage.setAlpha(0.38f);
    }

    private void addImage(int resId) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        layoutParams.setMargins(0, 0, 0, 0);
        mVisiableImage.setImageResource(resId);
        mVisiableImage.setAlpha(0.54f);
        mEditLayout.addView(mVisiableImage, -1, layoutParams);
    }
}
