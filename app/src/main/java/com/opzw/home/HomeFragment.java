package com.opzw.home;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding2.view.RxView;
import com.opzw.R;
import com.opzw.base.BaseFragment;
import com.opzw.demand.DemandActivity;
import com.opzw.guide.nav.OnTabReselectListener;
import com.opzw.part.PartActivity;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * 文 件 名: TestFragment
 * 创 建 人: Cartman
 * 创建日期: 2017/11/24 11:11
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class HomeFragment extends BaseFragment implements OnTabReselectListener {
    private static final Integer[] CHANNELS = new Integer[]{R.mipmap.banner_1, R.mipmap.banner_2};
    private List<Integer> mDataList = Arrays.asList(CHANNELS);
    private HomePagerAdapter mExamplePagerAdapter = new HomePagerAdapter(mDataList);
    private ViewPager mViewPager;
    private ConstraintLayout payBtn;
    private LinearLayout partBtn;
    private LinearLayout yuceBtn;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mViewPager = root.findViewById(R.id.view_pager);
        payBtn = root.findViewById(R.id.payBtn);
        partBtn = root.findViewById(R.id.partBtn);
        yuceBtn = root.findViewById(R.id.yuceBtn);
        mViewPager.setAdapter(mExamplePagerAdapter);
        initMagicIndicator1(root);

        RxView.clicks(payBtn)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ScheduleActivity.openActivity(getContext());
                    }
                });
        RxView.clicks(partBtn)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        PartActivity.openActivity(getContext());
                    }
                });
        RxView.clicks(yuceBtn)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        DemandActivity.openActivity(getContext());
                    }
                });
    }
    private void initMagicIndicator1(View root) {
        MagicIndicator magicIndicator = root.findViewById(R.id.magic_indicator1);
        CircleNavigator circleNavigator = new CircleNavigator(getContext());
        circleNavigator.setCircleCount(CHANNELS.length);
        circleNavigator.setCircleColor(Color.WHITE);
        circleNavigator.setCircleClickListener(new CircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                mViewPager.setCurrentItem(index);
            }
        });
        magicIndicator.setNavigator(circleNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }
    @Override
    public void onTabReselect() {

    }

    @Override
    protected void initData() {
        super.initData();

    }
}
