package com.zpauly.materialcomponents.TextFields;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nineoldandroids.view.ViewPropertyAnimator;
import com.zpauly.floatingactionbutton.R;

/**
 * Created by root on 16-4-10.
 */
public class NormalTextField extends LinearLayout {

    private Context mContext;

    protected RelativeLayout mTopLayout;
    protected TextView mLabelsTextView;
    protected RelativeLayout mEditLayout;
    protected TextView mBottomLabelsTextView;
    protected EditText mEditText;
    protected RelativeLayout mBottomLayout;
    protected TextView mLimitTextView;

    private String mLabels;
    private String mBottomLabels;
    private String mDefaultText;
    private String mLimitWarning;
    private boolean mIsDarkTheme;
    private int mDividerColor;
    private int mLabelsColor;
    private int mBottomLabelsColor;
    private float mTextSize;
    private float mLabelsSize;
    private float mBottomLabelsSize;
    private int mTextLengthLimit;
    private int mLimitWarningColor;

    private boolean mHasLabels;
    private boolean mHasBottomLabels;
    private boolean isShowingLabels;
    private boolean mHasTextLengthLimit;

    private DividerDrawable bg;
    private Drawable bgDrawable;

    private TextChangedListener mTextChangedListener;

    public void addTextChangedListener(TextChangedListener listener) {
        mTextChangedListener = listener;
    }

    public interface TextChangedListener {
        void onTextChanging();
    }

    public NormalTextField(Context context) {
        super(context);
        mContext = context;
        initNormalTextField(null);
    }

    public NormalTextField(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initNormalTextField(attrs);
    }

    public NormalTextField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initNormalTextField(attrs);
    }

    public NormalTextField(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        initNormalTextField(attrs);
    }

    private void initNormalTextField(AttributeSet attrs) {
        setOrientation(LinearLayout.VERTICAL);
        setAttributeSet(attrs);

        mTopLayout = new RelativeLayout(mContext);
        mEditLayout = new RelativeLayout(mContext);
        mEditText = new EditText(mContext);
        mLabelsTextView = new TextView(mContext);
        mBottomLabelsTextView = new TextView(mContext);
        mBottomLayout = new RelativeLayout(mContext);
        mLimitTextView = new TextView(mContext);

        bg = new DividerDrawable();
        bgDrawable = bg.getDivider();

        initViews();
    }

    private void initViews() {
        initTopLayout();
        initMiddleLayout();
        initBottomLayout();
    }

    private void setAttributeSet(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.NormalTextField);
        mIsDarkTheme = typedArray.getBoolean(R.styleable.NormalTextField_dark_theme, false);
        mLabels = typedArray.getString(R.styleable.NormalTextField_labels);
        mBottomLabels = typedArray.getString(R.styleable.NormalTextField_bottom_labels);
        mDividerColor = typedArray.getColor(R.styleable.NormalTextField_divider_color, -1);
        mLabelsColor = typedArray.getColor(R.styleable.NormalTextField_labels_color, Color.argb(54, 0, 0, 0));
        mBottomLabelsColor = typedArray.getColor(R.styleable.NormalTextField_bottom_labels_color, mDividerColor);
        mTextSize = typedArray.getFloat(R.styleable.NormalTextField_text_size, -1);
        mLabelsSize = typedArray.getFloat(R.styleable.NormalTextField_labels_size, -1);
        mBottomLabelsSize = typedArray.getFloat(R.styleable.NormalTextField_bottom_labels_size, -1);
        mDefaultText = typedArray.getString(R.styleable.NormalTextField_default_text);
        mTextLengthLimit = typedArray.getInt(R.styleable.NormalTextField_text_length_limit, -1);
        mLimitWarning = typedArray.getString(R.styleable.NormalTextField_bottom_labels_limit_warning);
        mLimitWarningColor = typedArray.getColor(R.styleable.NormalTextField_limit_warning_color, -1);


        if (mLimitWarning == null)
            mLimitWarning = "beyond limit";

        if (mLabelsColor == -1)
            mLabelsColor = Color.argb(54, 0, 0, 0);

        if (mBottomLabelsColor == -1)
            mBottomLabelsColor = Color.argb(54, 0, 0, 0);

        mHasTextLengthLimit = (mTextLengthLimit != -1) ? true : false;
        mHasLabels = (mLabels != null) ? true : false;
        mHasBottomLabels = (mBottomLabels != null) ? true : false;
        isShowingLabels = false;
        typedArray.recycle();
    }

    private void initTopLayout() {
        if (!mHasLabels)
            return;
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 16, 0, 0);
        addView(mTopLayout, -1, layoutParams);
        initLabels();
    }

    @SuppressWarnings("deprecation")
    private void initLabels() {
        if (!mHasLabels)
            return;
        mLabelsTextView.setTextAppearance(mContext, R.style.InputView_Labels);
        mLabelsTextView.setText(mLabels);
        mLabelsTextView.setTextColor(mLabelsColor);
        mLabelsTextView.setVisibility(INVISIBLE);
        if (mLabelsSize != -1)
            mLabelsTextView.setTextSize(mLabelsSize);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        addView(mLabelsTextView, -1, layoutParams);
    }

    private void initMiddleLayout() {
        int bottomPadding = 8;
        int topPadding = mHasLabels ? 8 : 16;
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        if (mHasBottomLabels)
            layoutParams.setMargins(0, topPadding, 0, 0);
        else
            layoutParams.setMargins(0, topPadding, 0, bottomPadding);
        addView(mEditLayout, -1, layoutParams);
        initEditText();
    }

    @SuppressWarnings("deprecation")
    private void initEditText() {
        mEditText.setTextAppearance(mContext, R.style.InputView_Edittext);
        mEditText.setHint(mLabels);
        if (mHasTextLengthLimit)
            mEditText.setFilters(new InputFilter[] {
                    new InputFilter.LengthFilter(mTextLengthLimit)
            });
        if (mTextSize != -1)
            mEditText.setTextSize(mTextSize);
        if (mDividerColor == -1)
            bg.setDividerColor(Color.argb(54, 0, 0, 0));
        mEditText.setBackground(null);
        mEditText.setPadding(0, 0, 0, 8);
        mEditLayout.setBackground(bgDrawable);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        mEditLayout.addView(mEditText, -1, layoutParams);
        mEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (mDividerColor == -1)
                    return;
                if (hasFocus) {
                    mEditText.setHintTextColor(Color.argb(54, 0, 0, 0));
                    bg.setDividerColor(mDividerColor);
                    bg.setDividerHeight(6);
                } else {
                    bg.setDividerColor(Color.argb(54, 0, 0, 0));
                    bg.setDividerHeight(2);
                    mEditText.setHintTextColor(Color.argb(90, 0, 0, 0));
                }
            }
        });
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mHasTextLengthLimit) {
                    showBottomLimit();
                    int length = getText().toString().length();
                    mLimitTextView.setText(length + "/" + mTextLengthLimit);
                    if (length >= mTextLengthLimit) {
                        if (mLimitWarning != null) {
                            mBottomLabelsTextView.setText(mLimitWarning);
                        }
                        showBottomLabels();
                    } else {
                        mBottomLabelsTextView.setText(mBottomLabels);
                        hideBottomLabels();
                    }
                }
                if (mTextChangedListener != null) {
                    mTextChangedListener.onTextChanging();
                }
                if (s.length() == 0 || s == null) {
                    isShowingLabels = false;
                    hideTopLabels();
                    return;
                }
                if (isShowingLabels) {
                    return;
                } else {
                    showTopLabels();
                    isShowingLabels = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void initBottomLayout() {
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 8, 0, 8);
        addView(mBottomLayout, -1, layoutParams);
        if (!mHasBottomLabels && !mHasTextLengthLimit)
            return;
        initBottomLabels();
        initTextLimit();
    }

    @SuppressWarnings("deprecation")
    private void initBottomLabels() {
        if (!mHasBottomLabels)
            return;
        mBottomLabelsTextView.setTextAppearance(mContext, R.style.InputView_Labels);
        mBottomLabelsTextView.setText(mBottomLabels);
        mBottomLabelsTextView.setTextColor(mBottomLabelsColor);
        mBottomLabelsTextView.setVisibility(INVISIBLE);
        if (mLabelsSize != -1)
            mBottomLabelsTextView.setTextSize(mLabelsSize);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        mBottomLayout.addView(mBottomLabelsTextView, layoutParams);
    }

    @SuppressWarnings("deprecation")
    private void initTextLimit() {
        if (!mHasTextLengthLimit)
            return;
        mLimitTextView.setTextAppearance(mContext, R.style.InputView_Labels);
        mLimitTextView.setTextColor(mBottomLabelsColor);
        if (mLimitWarningColor != -1)
            mLimitTextView.setTextColor(mLimitWarningColor);
        if (mLabelsSize != -1)
            mLimitTextView.setTextSize(mLabelsSize);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        mBottomLayout.addView(mLimitTextView, layoutParams);
    }

    public void showTopLabels() {
        mLabelsTextView.setVisibility(VISIBLE);
        mLabelsTextView.setAlpha(0);
        ViewPropertyAnimator.animate(mLabelsTextView).alpha(255).start();
    }

    public void hideTopLabels() {
        mLabelsTextView.setVisibility(INVISIBLE);
    }

    public void showBottomLabels() {
        mBottomLabelsTextView.setVisibility(VISIBLE);
    }

    public void hideBottomLabels() {
        mBottomLabelsTextView.setVisibility(INVISIBLE);
    }

    public void showBottomLimit() {
        mLimitTextView.setVisibility(VISIBLE);
    }

    public void hideBottomLimit() {
        mLimitTextView.setVisibility(INVISIBLE);
    }

    public String getLabels() {
        return mLabels;
    }

    public void setLabels(String mLabels) {
        this.mLabels = mLabels;
        mHasLabels = true;
        mLabelsTextView.setText(mLabels);
    }

    public String getBottomLabels() {
        return mBottomLabels;
    }

    public void setBottomLabels(String mBottomLabels) {
        this.mBottomLabels = mBottomLabels;
        mHasBottomLabels = true;
        mBottomLabelsTextView.setText(mBottomLabels);
    }

    public boolean isIsDarkTheme() {
        return mIsDarkTheme;
    }

    public void setIsDarkTheme(boolean mIsDarkTheme) {
        this.mIsDarkTheme = mIsDarkTheme;
    }

    public int getDividerColor() {
        return mDividerColor;
    }

    public void setDividerColor(int mDividerColor) {
        this.mDividerColor = mDividerColor;
    }

    public float getTextSize() {
        return mTextSize;
    }

    public void setTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
        mEditText.setTextSize(mTextSize);
    }

    public float getLabelsSize() {
        return mLabelsSize;
    }

    public void setLabelsSize(float mLabelsSize) {
        this.mLabelsSize = mLabelsSize;
        mLabelsTextView.setTextSize(mLabelsSize);
    }

    public void setBottomLabelsSize(float mBottomLabelsSize) {
        this.mBottomLabelsSize = mBottomLabelsSize;
        mBottomLabelsTextView.setTextSize(mBottomLabelsSize);
        mLimitTextView.setTextSize(mBottomLabelsSize);
    }

    public String getText() {
        return mEditText.getText().toString();
    }

    public void setText(CharSequence s) {
        mEditText.setText(s);
    }

    private class DividerDrawable {
        private ShapeDrawable divider;
        private ShapeDrawable contentDrawable;
        private LayerDrawable bg;

        private void setDivider() {
            divider = new ShapeDrawable(new RectShape());
            divider.getPaint().setColor(Color.argb(54, 0, 0, 0));
        }

        @SuppressWarnings("deprecation")
        private void setContentDrawable() {
            int color;
            contentDrawable = new ShapeDrawable(new RectShape());
            if (mIsDarkTheme) {
                color = getResources().getColor(R.color.dark_theme_background);
            } else {
                color = getResources().getColor(R.color.light_theme_background);
            }
            contentDrawable.getPaint().setColor(color);
        }

        public void drawDividerDrawable() {
            bg = new LayerDrawable(new Drawable[]{divider, contentDrawable});
            bg.setLayerInset(1, 0, 0, 0, 2);
        }

        public LayerDrawable getDivider() {
            setDivider();
            setContentDrawable();
            drawDividerDrawable();
            return bg;
        }

        public void setDividerColor(int color) {
            divider.getPaint().setColor(color);
            bg.invalidateSelf();
        }

        public void setDividerHeight(int height) {
            bg.setLayerInset(1, 0, 0, 0, height);
            bg.invalidateSelf();
        }
    }
}
