package com.example.furrytales.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import com.example.furrytales.R;
import com.example.furrytales.adapter.ImageSliderAdapter;

import java.util.ArrayList;
import java.util.List;

public class LandingPageActivity extends AppCompatActivity {

    SearchView searchView;
    ViewPager2 viewPager;
    ImageSliderAdapter imageSliderAdapter;
    List<Integer> images = new ArrayList<>();
    Handler handler = new Handler();
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        searchView = findViewById(R.id.searchView);
        viewPager = findViewById(R.id.viewPager);

        // Add your images to the list
        images.add(R.drawable.discount1);
        images.add(R.drawable.discount2);
        images.add(R.drawable.carousal2);

        // Initialize and set up the adapter for the ViewPager2
        imageSliderAdapter = new ImageSliderAdapter(images);
        viewPager.setAdapter(imageSliderAdapter);

        // Start automatic sliding behavior
        startAutoSlider();
    }

    // Method to start automatic sliding behavior
    private void startAutoSlider() {
        runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int nextItem = (currentItem + 1) % images.size();
                viewPager.setCurrentItem(nextItem, true);
                handler.postDelayed(this, 3000); // Slide to the next image after 3 seconds
            }
        };
        handler.postDelayed(runnable, 3000); // Start sliding after 3 seconds
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop the handler when the activity is destroyed to prevent memory leaks
        handler.removeCallbacks(runnable);
    }
}
