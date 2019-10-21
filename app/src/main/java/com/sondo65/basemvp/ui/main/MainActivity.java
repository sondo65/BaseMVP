package com.sondo65.basemvp.ui.main;

import android.os.Bundle;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


import com.google.android.material.navigation.NavigationView;
import com.sondo65.basemvp.ui.base.BaseActivity;

import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.sondo65.basemvp.R;
import com.sondo65.basemvp.ui.category.FragmentCategory;
import com.sondo65.basemvp.ui.custom.RoundedImageView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final String TAG = "MainActivity";

    @Inject
    MainPresenter<MainMvpView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_view)
    DrawerLayout mDrawer;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    @BindView(R.id.rgMain)
    RadioGroup rgMain;

    private SlidingUpPanelLayout mLayout;

    private TextView mNameTextView;

    private TextView mEmailTextView;

    private ActionBarDrawerToggle mDrawerToggle;

    private RoundedImageView mProfileImageView;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = findViewById(R.id.sliding_layout);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(MainActivity.this);

        setUp();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawer,mToolbar,
                R.string.open_drawer,
                R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        setupNavMenu();
        //innitDefault();
        listenerGroupRadioButtonClick();
        listenerSlidingUpPanelChanged();

    }

    void setupNavMenu() {
        View headerLayout = mNavigationView.getHeaderView(0);
        mProfileImageView = headerLayout.findViewById(R.id.iv_profile_pic);
        mNameTextView = headerLayout.findViewById(R.id.tv_name);
        mEmailTextView = headerLayout.findViewById(R.id.tv_email);

        mNavigationView.setNavigationItemSelectedListener(
                item -> {
                    mDrawer.closeDrawer(GravityCompat.START);
                    switch (item.getItemId()) {
                        case R.id.nav_item_about:
                            Toast.makeText(this, "Clicked about", Toast.LENGTH_SHORT).show();
                            //mPresenter.onDrawerOptionAboutClick();
                            return true;
                        case R.id.nav_item_rate_us:
                            Toast.makeText(this, "Clicked rate", Toast.LENGTH_SHORT).show();
                            //mPresenter.onDrawerRateUsClick();
                            return true;
                        case R.id.nav_item_feed:
                            Toast.makeText(this, "Clicked feed", Toast.LENGTH_SHORT).show();
                            //mPresenter.onDrawerMyFeedClick();
                            return true;
                        case R.id.nav_item_logout:
                            Toast.makeText(this, "Clicked logout", Toast.LENGTH_SHORT).show();
                            //mPresenter.onDrawerOptionLogoutClick();
                            return true;
                        default:
                            return false;
                    }
                });
    }

    private void innitDefault(){
        mPresenter.onRadioButtonCategoryClick();
        hiddenSlideUpPanel();
    }

    private void listenerGroupRadioButtonClick(){
        rgMain.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i){
                case R.id.rbCategory:
                    mPresenter.onRadioButtonCategoryClick();
                    hiddenSlideUpPanel();
                    break;

            }
        });
    }

    private void listenerSlidingUpPanelChanged(){

        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i(TAG, "onPanelStateChanged " + newState);
            }
        });
        mLayout.setFadeOnClickListener(view -> mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED));

    }

    private void hiddenSlideUpPanel(){
        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }

    @Override
    public void showCategoryFragment() {
        lockDrawer();;
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.frm_main, FragmentCategory.newInstance(), FragmentCategory.TAG)
                .commit();
    }

    @Override
    public void onRadioButtonCategoryClick() {
        showCategoryFragment();
    }


    @Override
    public void lockDrawer() {
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void unlockDrawer() {
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }
}