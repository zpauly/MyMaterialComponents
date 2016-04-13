package com.zpauly.scrolllistener;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AbsListView;

import com.zpauly.Contants;

/**
 * Created by zpauly on 16-4-8.
 */
public abstract class ListViewScrollListener implements  AbsListView.OnScrollListener {
    private int mLastScrollDistance;
    private int mNewScrollDistance;
    private int mLastFirstVisibleItem;
    private float translationY;
    private AbsListView mListView;

    public void setAttachedListView(@NonNull AbsListView listView) {
        mListView = listView;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mListView.getChildAt(0) == null) {
            return;
        }
        if (visibleItemCount == 0) {
            return;
        }
        if (mLastFirstVisibleItem == firstVisibleItem) {
            View v = mListView.getChildAt(0);
            mNewScrollDistance = v.getTop();
            if (Math.abs(mNewScrollDistance - mLastScrollDistance) > Contants.SCROLL_THRESHOLD) {
                if (mNewScrollDistance > mLastScrollDistance)
                    onScrollDown();
                else
                    onScrollUp();
            }
            mLastScrollDistance = mNewScrollDistance;
        } else {
            if (firstVisibleItem > mLastFirstVisibleItem) {
                onScrollUp();
            } else {
                onScrollDown();
            }
            View v = mListView.getChildAt(0);
            mNewScrollDistance = v.getTop();
            mLastScrollDistance = mNewScrollDistance;
            mLastFirstVisibleItem = firstVisibleItem;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    public abstract void onScrollUp();

    public abstract void onScrollDown();
}
