package com.opzw.home;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.opzw.R;
import com.opzw.base.BaseActivity;
import com.opzw.bean.Result;
import com.opzw.bean.User;
import com.opzw.service.ApiManager;
import com.opzw.service.CallbackWrapper;
import com.opzw.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ScheduleActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private ScheduleAdapter scheduleAdapter;
    private List<List<String>> objectList;

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, ScheduleActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_schedule;
    }

    @Override
    protected void initView() {
        initTitle(R.string.title_schedule);
        recyclerView = findViewById(R.id.recyle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            return;
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(0xff508DFF);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        objectList = new ArrayList<>();
        scheduleAdapter = new ScheduleAdapter(this, objectList);
        recyclerView.setAdapter(scheduleAdapter);
        List<String> strings1 = new ArrayList<>();
        strings1.add("已处理");
        strings1.add("订单号：DDZX123456788912,共计40234.70元\n" +
                "未付款，请尽快完成付款。");

        List<String> strings2 = new ArrayList<>();
        strings2.add("待处理");
        strings2.add("订单号：DDZX123456788912,共计40234.70元\n" +
                "未付款，请尽快完成付款。");


        objectList.add(strings1);
        objectList.add(strings2);
        scheduleAdapter.notifyDataSetChanged();
    }
}
