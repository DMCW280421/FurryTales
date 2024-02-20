package com.example.furrytales.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.furrytales.R;
import com.example.furrytales.adapter.MainFragmentAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import utility.FurryTalesConstants;

public class DashBoardActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    Toolbar toolbar;

    MainFragmentAdapter mainFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        toolbar = findViewById(R.id.toolBar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager2);
        mainFragmentAdapter = new MainFragmentAdapter(this);
        viewPager2.setAdapter(mainFragmentAdapter);
        setSupportActionBar(toolbar);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setIcon(R.drawable.dashboard);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.product_list);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.profile);
                        break;
                    case 3:
                        tab.setIcon(R.drawable.cart);

                }
            }
        }).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getSharedPreferences(FurryTalesConstants.SHARED_PREFERENCE_FILE_NAME, MODE_PRIVATE)
                .getBoolean(FurryTalesConstants.LOGIN_STATUS, false))
            menu.add("logout").setIcon(R.drawable.logout).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add("Checkout").setIcon(R.drawable.checkout).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        getSharedPreferences(FurryTalesConstants.SHARED_PREFERENCE_FILE_NAME, MODE_PRIVATE)
                .edit()
                .putBoolean(FurryTalesConstants.LOGIN_STATUS, false)
                .apply();
        finish();
        return super.onOptionsItemSelected(item);
    }
}