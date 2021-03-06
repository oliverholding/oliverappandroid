package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CommercializationTutorial.SliderAdapter;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CommercializationTutorial.SliderItem;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;
import java.util.List;

public class LogisticTutorialActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    Handler slideHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistic_tutorial);

        viewPager2 = findViewById(R.id.viewPagerImageSlider);

        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-16.png"));
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-17.png"));
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-18.png"));
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-19.png"));
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-20.png"));
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-21.png"));
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-22.png"));
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-23.png"));
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-24.png"));


        viewPager2.setAdapter(new SliderAdapter(sliderItems,viewPager2,this));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.35f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable,3000);
            }
        });
    }
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

}
