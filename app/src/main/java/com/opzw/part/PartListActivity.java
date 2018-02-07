package com.opzw.part;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.opzw.R;
import com.opzw.base.BaseActivity;
import com.opzw.bean.Car;

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


public class PartListActivity extends BaseActivity {
    RecyclerView leftListView;

    private PartListAdapter leftAdapter;
    List<Car> mPriceList = new ArrayList<>();

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, PartListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_part_list;
    }

    @Override
    protected void initView() {
        initTitle(R.string.title_part_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        leftListView = findViewById(R.id.recyle);

        leftListView.setLayoutManager(layoutManager);
        leftAdapter = new PartListAdapter(this);
        leftListView.setAdapter(leftAdapter);
        getLeftName();
    }


    //设置左边数据源
    private void getLeftName() {

        for (int i = 0; i < 5; i++) {
            Car car = new Car();
            car.setCode("N00"+ i);
            car.setName("宝马");
            car.setSpec("SPEC");
            car.setGuige("0.8*1250*C");
            mPriceList.add(car);
        }

        leftAdapter.setList(mPriceList);
        leftAdapter.notifyDataSetChanged();

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }
}
