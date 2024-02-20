package com.example.furrytales.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.furrytales.activity.DashBoardActivity;
import com.example.furrytales.activity.LandingPageActivity;

import fragments.CartFragment;
import fragments.LandingPageFragment;
import fragments.ProductListFragment;
import fragments.ProfileFragment;

public class MainFragmentAdapter extends FragmentStateAdapter {
    public MainFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){

            case 0:
                return new LandingPageFragment();
            case 1:
                return new ProductListFragment();
            case 2:
                return new ProfileFragment();
            case 3:
                return new CartFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
