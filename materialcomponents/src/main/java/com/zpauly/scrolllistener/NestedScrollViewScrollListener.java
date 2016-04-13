package com.zpauly.scrolllistener;

import android.support.v4.widget.NestedScrollView;

import com.zpauly.Contants;

/**
 * Created by root on 16-4-10.
 */
public abstract class NestedScrollViewScrollListener implements NestedScrollView.OnScrollChangeListener {


    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (Math.abs(scrollY - oldScrollY) > Contants.SCROLL_THRESHOLD) {
            if (scrollY > oldScrollY) {
                onScrollUp();
            } else {
                onScrollDown();
            }
        }
    }

    public abstract void onScrollUp();

    public abstract void onScrollDown();
}
