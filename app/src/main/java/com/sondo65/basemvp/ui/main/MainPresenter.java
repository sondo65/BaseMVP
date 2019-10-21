package com.sondo65.basemvp.ui.main;


import com.sondo65.basemvp.ui.base.BasePresenter;
import com.sondo65.basemvp.utils.rx.SchedulerProvider;
import com.sondo65.basemvp.data.DataManager;

import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    private static final String TAG = "MainPresenter";

    @Inject
    public MainPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onRadioButtonCategoryClick() {
        getMvpView().onRadioButtonCategoryClick();
    }
}