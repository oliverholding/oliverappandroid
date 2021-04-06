package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement;

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

public class FinanceTutorialActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    Handler slideHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_tutorial);

        viewPager2 = findViewById(R.id.viewPagerImageSlider);

        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-23-copia-7.png"));
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-24-copia-7.png"));
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-25-copia-7.png"));
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-1-copia.png"));
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-2-copia.png"));
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-3-copia.png"));
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-4-copia.png"));
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-5-copia.png"));
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-8-copia-2.png"));
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-8-copia-3.png"));
        sliderItems.add(new SliderItem("https://oliver.com.pe/wp-content/uploads/2021/04/Mesa-de-trabajo-8-copia.png"));



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
