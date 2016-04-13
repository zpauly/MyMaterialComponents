package com.zpauly.sample.floatingactionbutton;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.materialcomponents.buttons.FloatingActionButton;
import com.zpauly.sample.R;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 16-4-10.
 */
public class RecyclerViewFragment extends Fragment {
    private View mView;

    @Bind(R.id.fab_recyclerview_fragment_rv)
    protected RecyclerView mRecyclerView;

    @Bind(R.id.fab_recyclerview_fragment_fab)
    protected FloatingActionButton mFab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fab_recyclerview_fragment, container, false);
        ButterKnife.bind(this, mView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new RecyclerViewAdater(getContext()));

        mFab.attachButtonToRecyclerView(mRecyclerView);

        return mView;
    }
}
