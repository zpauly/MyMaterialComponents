package com.zpauly.sample.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zpauly.materialcomponents.TextFields.NormalTextField;
import com.zpauly.materialcomponents.TextFields.PasswordTextField;
import com.zpauly.sample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 16-4-11.
 */
public class MaterialInputViewActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Bind(R.id.first_inputview)
    protected NormalTextField mNormalInputView;

    @Bind(R.id.second_inputview)
    protected PasswordTextField mPasswordInputView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materialinputview);
        ButterKnife.bind(this);

        setupToolbar();
        setupNormalInputView();
        setupPasswordInputView();
    }

    private void setupToolbar() {
        mToolbar.setTitle("MaterialInputView");
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

    private void setupNormalInputView() {
        mNormalInputView.addTextChangedListener(new NormalTextField.TextChangedListener() {
            @Override
            public void onTextChanging() {
                if (mNormalInputView.getText() != null)
                    mNormalInputView.showBottomLabels();
                if (mNormalInputView.getText() == null ||
                        mNormalInputView.getText().equals(""))
                    mNormalInputView.hideBottomLabels();
            }
        });
    }

    private void setupPasswordInputView() {
        mPasswordInputView.setLabels("password inputview");
    }
}
