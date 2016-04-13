package com.zpauly.sample.floatingactionbutton;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zpauly.materialcomponents.buttons.FloatingActionButton;
import com.zpauly.sample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 16-4-10.
 */
public class NestedScrollViewFragment extends Fragment {
    private View mView;

    @Bind(R.id.fab_nestedscrollview_nsv)
    protected NestedScrollView mNestedScrollView;

    @Bind(R.id.fab_nestedscrollview_layout)
    protected LinearLayout mLayout;

    @Bind(R.id.fab_nestedscrollview_fab)
    protected FloatingActionButton mFab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fab_nestedscrollview_fragment, container, false);
        ButterKnife.bind(this, mView);

        for (int i = 0; i < 70; i++) {
            addTextView("item" + i);
        }

        mFab.attachButtonToNestedScrollView(mNestedScrollView);

        return mView;
    }

    private void addTextView(String str) {
        TextView textView = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 16, 0, 16);
        textView.setHeight(60);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(str);
        textView.setTextSize(15f);
        mLayout.addView(textView, -1, layoutParams);
    }
}
