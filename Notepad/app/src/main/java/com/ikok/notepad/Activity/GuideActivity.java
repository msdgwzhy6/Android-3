package com.ikok.notepad.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.ikok.notepad.R;
import com.ikok.notepad.Util.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anonymous on 2016/3/26.
 */
public class GuideActivity extends Activity {

    private ViewPager mViewPager;
    private PagerAdapter mAdapter;
    private int[] imgIds = new int[] {R.drawable.guide1,R.drawable.guide2,R.drawable.guide3};
    private List<ImageView> mImgs = new ArrayList<ImageView>();
    /**
     * ViewPager的位置
     */
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.guide_view);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        // 为ViewPager添加动画效果,3.0以上可用
//        mViewPager.setPageTransformer(true,new DepthPageTransformer());
        mViewPager.setPageTransformer(true,new ZoomOutPageTransformer());
        mAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return imgIds.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(GuideActivity.this);
                imageView.setImageResource(imgIds[position]);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
                mImgs.add(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mImgs.get(position));
            }
        };
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                index = position;
                Log.d("Anonymous", "onPageSelected: " + index);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                Log.d("Anonymous",""+state);
//                Log.d("Anonymous", "onPageScrollStateChanged: -----------");
                if (index == 2 && state == ViewPager.SCROLL_STATE_DRAGGING){
                    Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
