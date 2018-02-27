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

public class DemandDetailActivity extends BaseActivity {



    public static void openActivity(Context context) {
        Intent intent = new Intent(context, DemandDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demand_detail;
    }

    @Override
    protected void initView() {
        initTitle(R.string.title_demand_detail);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    }



    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }
}
