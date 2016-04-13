package com.zpauly.sample.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zpauly.materialcomponents.buttons.FlatButton;
import com.zpauly.sample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 16-4-13.
 */
public class FlatButtonActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Bind(R.id.fb_flatbutton1)
    protected FlatButton mFlatButton1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flatbutton);
        ButterKnife.bind(this);

        setupToolbar();

        mFlatButton1.setText("FlatButton1");
        mFlatButton1.setMaterialTextColor(getResources().getColor(R.color.colorAccent));
    }

    private void setupToolbar() {
        mToolbar.setTitle("FlatButton");
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
}
