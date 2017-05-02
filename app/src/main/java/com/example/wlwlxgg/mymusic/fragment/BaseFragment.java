package com.example.wlwlxgg.mymusic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wlwlxgg on 2017/4/15.
 * initView:加载布局
 * initData:布局加载完成后，进行控件实例化
 * loadMore:fragment显示
 * OnResume:
 * invisible:fragment隐藏
 */

public abstract class BaseFragment extends Fragment {


    /**
     * 返回的布局view
     */
    public View view = null;
    /**
     * 上下文
     */
    public Context context = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = initView(inflater);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData(view, savedInstanceState);
        super.onActivityCreated(savedInstanceState);
    }


    public abstract View initView(LayoutInflater inflater);

    public abstract void initData(View view, Bundle savedInstanceState);

    /**
     * 设置Menu是否可见，menuVisible如果为true则当期fragment对象onCreateView返回的view可见，否则就不可见
     */
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }
}
