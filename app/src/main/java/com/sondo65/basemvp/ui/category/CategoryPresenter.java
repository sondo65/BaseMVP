package com.sondo65.basemvp.ui.category;


import com.sondo65.basemvp.ui.base.BasePresenter;
import com.sondo65.basemvp.utils.rx.SchedulerProvider;
import com.sondo65.basemvp.data.DataManager;

import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class CategoryPresenter<V extends CategoryMvpView> extends BasePresenter<V> implements CategoryMvpPresenter<V> {

    private static final String TAG = "CategoryPresenter";

    @Inject
    public CategoryPresenter(DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}