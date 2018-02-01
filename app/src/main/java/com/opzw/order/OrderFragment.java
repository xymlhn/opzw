package com.opzw.order;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.opzw.R;
import com.opzw.base.BaseFragment;
import com.opzw.guide.TestFragment;
import com.opzw.guide.nav.OnTabReselectListener;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文 件 名: TestFragment
 * 创 建 人: Cartman
 * 创建日期: 2017/11/24 11:11
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class OrderFragment extends BaseFragment implements OnTabReselectListener {
    private ViewPager mPager;
    private static final String[] CHANNELS = new String[]{"采购订单", "销售订单" };
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private ExamplePagerAdapter mExamplePagerAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mPager = root.findViewById(R.id.view_pager);
        PurchaseFragment testFragment1 = new PurchaseFragment();
        TestFragment testFragment2 = new TestFragment();
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(testFragment1);
        list.add(testFragment2);
        mExamplePagerAdapter = new ExamplePagerAdapter(getActivity().getSupportFragmentManager(),list);
        mPager.setAdapter(mExamplePagerAdapter);

        initMagicIndicator1(root);

    }
    private void initMagicIndicator1(View root) {
        MagicIndicator magicIndicator = root.findViewById(R.id.magic_indicator);
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(Color.parseColor("#666666"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#508DFF"));
                simplePagerTitleView.setTextSize(14);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(Color.parseColor("#508DFF"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mPager);
    }

    @Override
    public void onTabReselect() {

    }

    @Override
    protected void initData() {
        super.initData();

    }
}
