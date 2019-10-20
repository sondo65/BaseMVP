package com.sondo65.basemvp.ui.main;

import android.os.Bundle;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.google.android.material.navigation.NavigationView;
import com.sondo65.basemvp.ui.base.BaseActivity;

import android.content.Intent;
import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.sondo65.basemvp.R;
import com.sondo65.basemvp.ui.custom.RoundedImageView;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainPresenter<MainMvpView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_view)
    DrawerLayout mDrawer;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

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
}