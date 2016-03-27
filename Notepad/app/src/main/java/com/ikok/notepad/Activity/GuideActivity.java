package com.ikok.notepad.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.ikok.notepad.Fragment.Guide1;
import com.ikok.notepad.Fragment.Guide2;
import com.ikok.notepad.Fragment.Guide3;
import com.ikok.notepad.R;
import com.ikok.notepad.Util.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anonymous on 2016/3/26.
 */
public class GuideActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragment = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.guide_view);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        Fragment guide1 = new Guide1();
        Fragment guide2 = new Guide2();
        Fragment guide3 = new Guide3();

        mFragment.add(guide1);
        mFragment.add(guide2);
        mFragment.add(guide3);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }
        };

        // 为ViewPager添加动画效果,3.0以上可用
        mViewPager.setPageTransformer(true,new DepthPageTransformer());
//        mViewPager.setPageTransformer(true,new ZoomOutPageTransformer());
        mViewPager.setAdapter(mAdapter);

    }
}
