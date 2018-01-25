package com.opzw.guide;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.opzw.R;
import com.opzw.guide.nav.IndicatorView;
import com.opzw.main.MainActivity;
import com.opzw.utils.DialogUtils;
import com.prolificinteractive.parallaxpager.ParallaxContainer;

/**
 * 文 件 名: GuideFragment
 * 创 建 人: Cartman
 * 创建日期: 2017/11/23 16:54
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class GuideFragment extends Fragment implements ViewPager.OnPageChangeListener {

    IndicatorView mIndicatorView;
    Button loginBtn;
    Button registerBtn;
    LinearLayout layoutBottom;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_parallax, container, false);
        mIndicatorView = view.findViewById(R.id.indicatorView);
        loginBtn = view.findViewById(R.id.login_button);
        registerBtn = view.findViewById(R.id.register_button);
        layoutBottom = view.findViewById(R.id.layoutBottom);
        ParallaxContainer parallaxContainer = view.findViewById(R.id.parallax_container);
        parallaxContainer.setLooping(false);
        parallaxContainer.setupChildren(inflater,
                R.layout.parallax_view_0,
                R.layout.parallax_view_1,
                R.layout.parallax_view_2,
                R.layout.parallax_view_3,
                R.layout.parallax_view_4,
                R.layout.parallax_view_5,
                R.layout.parallax_view_6
        );

        parallaxContainer.setOnPageChangeListener(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showLoading(getContext(),R.string.loading,true);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                MainActivity.openActivity(getContext());
            }
        });
        return view;
    }

    @Override
    public void onPageScrolled(int position, float offset, int offsetPixels) {
        if (offset > 0.5) {
            mIndicatorView.setSelect(position + 1);
        } else {
            mIndicatorView.setSelect(position);
        }
    }

    @Override
    public void onPageSelected(int position) {
        layoutBottom.setVisibility(position == 6 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
