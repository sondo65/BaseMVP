package com.sondo65.basemvp.ui.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sondo65.basemvp.R;
import com.sondo65.basemvp.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SampleActivity extends BaseActivity implements SampleMvpView {

    @Inject
    SamplePresenter<SampleMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SampleActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(SampleActivity.this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }
}