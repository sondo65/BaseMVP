package com.sondo65.basemvp.ui.main;

import com.sondo65.basemvp.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showCategoryFragment();
    void onRadioButtonCategoryClick();
    void lockDrawer();
    void unlockDrawer();
}