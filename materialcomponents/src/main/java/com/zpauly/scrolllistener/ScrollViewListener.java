package com.zpauly.scrolllistener;

import android.view.View;
import android.widget.ScrollView;

import com.zpauly.Contants;

/**
 * Created by root on 16-4-10.
 */
@SuppressWarnings("NewApi")
public abstract class ScrollViewListener implements ScrollView.OnScrollChangeListener {

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (Math.abs(scrollY - oldScrollY) > Contants.SCROLL_THRESHOLD) {
            if (scrollY > oldScrollY)
                onScrollUp();
            else
                onScrollDown();
        }
    }

    public abstract void onScrollUp();

    public abstract void onScrollDown();
}
