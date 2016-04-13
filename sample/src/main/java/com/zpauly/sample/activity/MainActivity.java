package com.zpauly.sample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.zpauly.sample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 16-4-11.
 */
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Bind(R.id.fab_activity_button)
    protected Button mFabButton;

    @Bind(R.id.mit_activity_button)
    protected Button mMitButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupToolbar();

        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, FloatingActionButtonActivity.class);
                startActivity(intent);
            }
        });

        mMitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MaterialInputViewActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupToolbar() {
        mToolbar.setTitle("MainActivity");
        setSupportActionBar(mToolbar);
    }
}
