package com.zpauly.sample.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zpauly.sample.R;
import com.zpauly.sample.floatingactionbutton.ListViewFragment;
import com.zpauly.sample.floatingactionbutton.NestedScrollViewFragment;
import com.zpauly.sample.floatingactionbutton.RecyclerViewFragment;
import com.zpauly.sample.floatingactionbutton.ScrollViewFragment;
import com.zpauly.sample.floatingactionbutton.ViewPagerFragmentAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FloatingActionButtonActivity extends AppCompatActivity {
    @Bind(R.id.appbar)
    protected AppBarLayout mAppbarLayout;

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Bind(R.id.tabs)
    protected TabLayout mTabLayout;

    @Bind(R.id.viewpager)
    protected ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floatingactionbutton);

        ButterKnife.bind(this);

        setupToolbar();

        setupViewPager();
    }

    private void setupToolbar() {
        mToolbar.setTitle("FloatingActionButton");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setupViewPager() {
        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new RecyclerViewFragment(), "RecyclerView");
        adapter.addFragment(new ListViewFragment(), "ListView");
        adapter.addFragment(new NestedScrollViewFragment(), "NestedScrollView");
        adapter.addFragment(new ScrollViewFragment(), "ScrollView");
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
