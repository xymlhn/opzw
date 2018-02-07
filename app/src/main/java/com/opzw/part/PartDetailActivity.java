package com.opzw.part;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.opzw.R;
import com.opzw.base.BaseActivity;
import com.opzw.bean.ChoiceLeftBean;
import com.opzw.bean.Company;
import com.opzw.bean.MostSeriesBean;
import com.opzw.bean.Result;
import com.opzw.bean.User;
import com.opzw.me.CompanyActivity;
import com.opzw.me.PersonalActivity;
import com.opzw.me.PersonalAdapter;
import com.opzw.order.OrderPagerAdapter;
import com.opzw.order.PurchaseFragment;
import com.opzw.service.ApiManager;
import com.opzw.service.CallbackWrapper;
import com.opzw.utils.ToastUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 文 件 名: PartActivity
 * 创 建 人: Cartman
 * 创建日期: 2018/2/6 14:52
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class PartDetailActivity extends BaseActivity {
    private ViewPager mPager;
    private static final String[] CHANNELS = new String[]{"车型图", "爆炸图","零件图" };
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private OrderPagerAdapter mOrderPagerAdapter;
    private RecyclerView recyclerView;
    private PersonalAdapter mAdapter;
    private List<List<String>> objectList;
    public static void openActivity(Context context) {
        Intent intent = new Intent(context, PartDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_part_detail;
    }

    @Override
    protected void initView() {
        initTitle(R.string.title_part);
        PartDetailFragment testFragment1 = new PartDetailFragment();
        PartDetailFragment testFragment2 = new PartDetailFragment();
        PartDetailFragment testFragment3 = new PartDetailFragment();
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(testFragment1);
        list.add(testFragment2);
        list.add(testFragment3);
        mPager = findViewById(R.id.view_pager);
        mOrderPagerAdapter = new OrderPagerAdapter(getSupportFragmentManager(),list);
        mPager.setAdapter(mOrderPagerAdapter);

        initMagicIndicator1();
        recyclerView = findViewById(R.id.recyle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
    }
    private void initMagicIndicator1() {
        MagicIndicator magicIndicator = findViewById(R.id.magic_indicator);
        CommonNavigator commonNavigator = new CommonNavigator(this);
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
    protected void setListener() {

    }

    @Override
    protected void initData() {
        objectList = new ArrayList<>();
        mAdapter = new PersonalAdapter(this, objectList);
        recyclerView.setAdapter(mAdapter);


        List<String> strings1 = new ArrayList<>();
        strings1.add("用户名");
        strings1.add("123456");

        List<String> strings2 = new ArrayList<>();
        strings2.add("手机号");
        strings2.add("123456");

        List<String> strings3 = new ArrayList<>();
        strings3.add("姓名");
        strings3.add("123456");

        List<String> strings4 = new ArrayList<>();
        strings4.add("所属公司");
        strings4.add("123456");

        List<String> strings5 = new ArrayList<>();
        strings5.add("角色");
        strings5.add("123456");

        List<String> strings6 = new ArrayList<>();
        strings6.add("角色");
        strings6.add("123456");

        List<String> strings7= new ArrayList<>();
        strings7.add("角色");
        strings7.add("123456");

        List<String> strings8 = new ArrayList<>();
        strings8.add("角色");
        strings8.add("123456");
        objectList.add(strings1);
        objectList.add(strings2);
        objectList.add(strings3);
        objectList.add(strings4);
        objectList.add(strings5);
        objectList.add(strings6);
        objectList.add(strings7);
        objectList.add(strings8);
        mAdapter.notifyDataSetChanged();
    }

}
