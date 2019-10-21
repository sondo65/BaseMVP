/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.sondo65.basemvp.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.sondo65.basemvp.di.ActivityContext;
import com.sondo65.basemvp.di.PerActivity;
import com.sondo65.basemvp.ui.category.CategoryMvpPresenter;
import com.sondo65.basemvp.ui.category.CategoryMvpView;
import com.sondo65.basemvp.ui.category.CategoryPresenter;
import com.sondo65.basemvp.ui.main.MainMvpPresenter;
import com.sondo65.basemvp.ui.main.MainMvpView;
import com.sondo65.basemvp.ui.main.MainPresenter;
import com.sondo65.basemvp.utils.rx.AppSchedulerProvider;
import com.sondo65.basemvp.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by janisharali on 27/01/17.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    CategoryMvpPresenter<CategoryMvpView> provideCategoryPresenter(CategoryPresenter<CategoryMvpView> presenter){
        return presenter;
    }

}
