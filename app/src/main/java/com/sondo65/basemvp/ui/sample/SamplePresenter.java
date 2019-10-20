package com.sondo65.basemvp.ui.sample;


import com.sondo65.basemvp.data.DataManager;
import com.sondo65.basemvp.ui.base.BasePresenter;
import com.sondo65.basemvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SamplePresenter<V extends SampleMvpView> extends BasePresenter<V> implements SampleMvpPresenter<V> {

    private static final String TAG = "SamplePresenter";

    @Inject
    public SamplePresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}