package com.opzw.part;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.opzw.R;
import com.opzw.base.BaseActivity;
import com.opzw.bean.ChoiceLeftBean;
import com.opzw.bean.MostSeriesBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名: PartActivity
 * 创 建 人: Cartman
 * 创建日期: 2018/2/6 14:52
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class PartActivity extends BaseActivity {
    RecyclerView leftListView;
    RecyclerView rightListView;

    private MostLeftAdapter leftAdapter;
    private MostRightAdapter rightAdapter;
    List<ChoiceLeftBean> mPriceList = new ArrayList<>();

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, PartActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_part;
    }

    @Override
    protected void initView() {
        initTitle(R.string.title_part);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        leftListView = findViewById(R.id.left_list);
        rightListView = findViewById(R.id.right_list);

        leftListView.setLayoutManager(layoutManager);
        leftAdapter = new MostLeftAdapter(this);
        leftListView.setAdapter(leftAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rightListView.setLayoutManager(gridLayoutManager);
        rightAdapter = new MostRightAdapter(this);
        rightListView.setAdapter(rightAdapter);
        rightAdapter.setOnItemClickListener(new MostRightAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                PartListActivity.openActivity(PartActivity.this);
            }
        });
        getLeftName();
    }

    private void requestRightData(int position) {
        //这里为了方便，直接更改左边的数据为右边加载的数据了，实际开发中改为所传参数即可
        String category = leftAdapter.getList().get(position);
        List<MostSeriesBean> list = new ArrayList<>();
        MostSeriesBean beans = new MostSeriesBean();
        beans.setName(category);
        list.add(beans);

        MostSeriesBean beans1 = new MostSeriesBean();
        beans1.setName(category);
        list.add(beans1);

        MostSeriesBean beans2 = new MostSeriesBean();
        beans2.setName(category);
        list.add(beans2);

        getRightData(list);

    }

    private void getRightData(List<MostSeriesBean> beans) {
        rightAdapter.setList(beans);
        rightAdapter.notifyDataSetChanged();
    }

    //设置左边数据源
    private void getLeftName() {

        for (int i = 0; i < 5; i++) {
            ChoiceLeftBean bean = new ChoiceLeftBean();
            if (i == 1) {
                bean.setName("宝马");
            }
            if (i == 2) {
                bean.setName("奔驰");
            }
            if (i == 3) {
                bean.setName("凯迪拉克");
            }
            if (i == 4) {
                bean.setName("现代");
            }
            if (i == 0) {
                bean.setName("SUV");
            }
            mPriceList.add(bean);
        }

        List<String> prices = new ArrayList<>();
        for (ChoiceLeftBean priceBean : mPriceList) {
            prices.add(priceBean.getName());
        }
        leftAdapter.setList(prices);
        //默认根据left的第一项数据去加载右边得数据
        requestRightData(0);
        leftAdapter.notifyDataSetChanged();
        leftAdapter.setOnItemClickListener(new MostLeftAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //设置position，根据position的状态刷新
                leftAdapter.setPosition(position);
                leftAdapter.notifyDataSetChanged();
                requestRightData(position);
            }
        });

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }
}
