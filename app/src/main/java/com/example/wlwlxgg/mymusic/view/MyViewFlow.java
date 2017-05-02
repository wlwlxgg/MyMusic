package com.example.wlwlxgg.mymusic.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.wlwlxgg.mymusic.fragment.HomeFragment;

/**
 * Created by wlwlxgg on 2017/4/15.
 */

public class MyViewFlow extends ViewFlow {
    private ViewPager mPager;

    public MyViewFlow(Context context, AttributeSet attrs) {
        super(context, attrs);;
        this.mPager = HomeFragment.viewPager;
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mPager != null)
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mPager.requestDisallowInterceptTouchEvent(true);
                    break;
                case MotionEvent.ACTION_UP:
                    mPager.requestDisallowInterceptTouchEvent(false);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    mPager.requestDisallowInterceptTouchEvent(false);
                    break;
                case MotionEvent.ACTION_MOVE:
                    mPager.requestDisallowInterceptTouchEvent(true);
                    break;
            }
        return super.onInterceptTouchEvent(ev);
    }

}
