package com.opzw.demand;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.opzw.R;
import com.opzw.base.BaseActivity;
import com.opzw.bean.Car;

import java.util.ArrayList;
import java.util.List;

public class DemandActivity extends BaseActivity {

    RecyclerView recyclerView;

    private DemandAdapter listAdapter;
    List<Car> mPriceList = new ArrayList<>();

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, DemandActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demand;
    }

    @Override
    protected void initView() {
        initTitle(R.string.title_demand);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = findViewById(R.id.recyle);

        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new DemandAdapter(this);
        recyclerView.setAdapter(listAdapter);
        getData();
    }


    //设置数据源
    private void getData() {

        for (int i = 0; i < 5; i++) {
            Car car = new Car();
            car.setCode("需求量：3.5吨");
            car.setName("冷扎板卷／DC03FD/0.8*1250*C");
            mPriceList.add(car);
        }

        listAdapter.setList(mPriceList);
        listAdapter.notifyDataSetChanged();

    }

    @Override
    protected void setListener() {
        listAdapter.setOnItemClickListener(new DemandAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {

            }
        });
    }

    @Override
    protected void initData() {

    }
}
