package com.opzw.part;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.opzw.R;
import com.opzw.base.BaseActivity;
import com.opzw.bean.ChoiceLeftBean;
import com.opzw.bean.Parts;
import com.opzw.bean.Result;
import com.opzw.service.ApiManager;
import com.opzw.service.CallbackWrapper;

import java.util.ArrayList;
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


public class PartActivity extends BaseActivity {
    RecyclerView leftListView;
    RecyclerView rightListView;
    private TextView customNum;
    private TextView carNum;
    private TextView partNum;
    private MostLeftAdapter leftAdapter;
    private MostRightAdapter rightAdapter;

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
        customNum = findViewById(R.id.customNum);
        carNum = findViewById(R.id.carNum);
        partNum = findViewById(R.id.partNum);
        leftListView.setLayoutManager(layoutManager);
        leftAdapter = new MostLeftAdapter(this);
        leftListView.setAdapter(leftAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rightListView.setLayoutManager(gridLayoutManager);
        rightAdapter = new MostRightAdapter(this);
        rightListView.setAdapter(rightAdapter);

    }


    @Override
    protected void setListener() {
        rightAdapter.setOnItemClickListener(new MostRightAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                PartListActivity.openActivity(PartActivity.this);
            }
        });

    }

    @Override
    protected void initData() {
        ApiManager.getInstence().getService().getPartFilter()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CallbackWrapper<Result<Parts>>() {
                    @Override
                    protected void onSuccess(Result<Parts> userResult) {
                        customNum.setText(userResult.getData().getC1() + "");
                        carNum.setText(userResult.getData().getC2() + "");
                        partNum.setText(userResult.getData().getC3() + "");
                        leftAdapter.setList(userResult.getData().getCarCustomerList());
                        leftAdapter.notifyDataSetChanged();
                        leftAdapter.setOnItemClickListener(new MostLeftAdapter.MyItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //设置position，根据position的状态刷新
                                leftAdapter.setPosition(position);
                                leftAdapter.notifyDataSetChanged();
                            }
                        });
                    }

                    @Override
                    protected void onFail(String t) {

                    }
                });
    }
}
