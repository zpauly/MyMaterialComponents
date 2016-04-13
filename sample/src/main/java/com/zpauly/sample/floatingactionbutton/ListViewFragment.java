package com.zpauly.sample.floatingactionbutton;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zpauly.materialcomponents.buttons.FloatingActionButton;
import com.zpauly.sample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 16-4-10.
 */
public class ListViewFragment extends Fragment {
    private View mView;

    @Bind(R.id.fab_listview_fragment_lv)
    protected ListView mListView;

    @Bind(R.id.fab_listview_fragment_fab)
    protected FloatingActionButton mFab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fab_listview_fragment, container, false);
        ButterKnife.bind(this, mView);

        mListView.setAdapter(new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_expandable_list_item_1,
                getListViewData()));

        mFab.attachButtonToListView(mListView);

        return mView;
    }

    private List<String> getListViewData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("item" + i);
        }
        return list;
    }
}
