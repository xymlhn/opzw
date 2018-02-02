package com.opzw.me;

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
import com.opzw.bean.Token;
import com.opzw.bean.User;
import com.opzw.service.ApiManager;
import com.opzw.service.CallbackWrapper;
import com.opzw.utils.SharedPrefUtils;
import com.opzw.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 文 件 名: PersonalActivity
 * 创 建 人: Cartman
 * 创建日期: 2018/1/25 13:48
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class PersonalActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private PersonalAdapter mAdapter;
    private List<List<String>> objectList;

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, PersonalActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    protected void initView() {
        initTitle(R.string.title_person);
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
        mAdapter = new PersonalAdapter(this, objectList);
        recyclerView.setAdapter(mAdapter);

        Token token = SharedPrefUtils.getToken();
        Map<String,Object> map = new HashMap<>();
        map.put("companyId",token.getCompanyId());
        map.put("appId",token.getAppId());
        map.put("userId",token.getId());
        map.put("userName",token.getUserName());
        ApiManager.getInstence().getService().getUser(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CallbackWrapper<Result<User>>() {
                    @Override
                    protected void onSuccess(Result<User> userResult) {

                        List<String> strings1 = new ArrayList<>();
                        strings1.add("用户名");
                        strings1.add(userResult.getData().getUsername());

                        List<String> strings2 = new ArrayList<>();
                        strings2.add("手机号");
                        strings2.add(userResult.getData().getMobile());

                        List<String> strings3 = new ArrayList<>();
                        strings3.add("姓名");
                        strings3.add(userResult.getData().getNickname());

                        List<String> strings4 = new ArrayList<>();
                        strings4.add("所属公司");
                        strings4.add(userResult.getData().getCompanyName());

                        List<String> strings5 = new ArrayList<>();
                        strings5.add("角色");
                        strings5.add(userResult.getData().getUsername());
                        objectList.add(strings1);
                        objectList.add(strings2);
                        objectList.add(strings3);
                        objectList.add(strings4);
                        objectList.add(strings5);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onFail(String t) {
                        ToastUtils.showToastShort(PersonalActivity.this,t);
                    }
                });
    }
}
