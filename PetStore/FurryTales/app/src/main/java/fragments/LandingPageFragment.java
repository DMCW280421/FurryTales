package fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.furrytales.R;
import com.example.furrytales.adapter.ImageSliderAdapter;


import java.util.ArrayList;
import java.util.List;


public class LandingPageFragment extends Fragment {

    SearchView searchView;
    ViewPager2 viewPager;
    ImageSliderAdapter imageSliderAdapter;
    List<Integer> images = new ArrayList<>();
    Handler handler = new Handler();
    Runnable runnable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_landing_page, container, false);

        searchView = rootView.findViewById(R.id.searchView);
        viewPager = rootView.findViewById(R.id.viewPager);

        // Add your images to the list
        images.add(R.drawable.discount1);
        images.add(R.drawable.discount2);
        images.add(R.drawable.carousal2);

        // Initialize and set up the adapter for the ViewPager2
        imageSliderAdapter = new ImageSliderAdapter(images);
        viewPager.setAdapter(imageSliderAdapter);

        // Start automatic sliding behavior
        startAutoSlider();

        return rootView;
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
    public void onDestroy() {
        super.onDestroy();
        // Stop the handler when the fragment is destroyed to prevent memory leaks
        handler.removeCallbacks(runnable);
    }

}