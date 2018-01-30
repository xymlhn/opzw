package com.opzw.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.opzw.R;
import com.opzw.base.BaseActivity;
import com.opzw.bean.Company;
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


public class CompanyActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private PersonalAdapter mAdapter;
    private List<List<String>> objectList;

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, CompanyActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    protected void initView() {
        initTitle(R.string.title_company);
        recyclerView = findViewById(R.id.recyle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        ApiManager.getInstence().getService().getCompany(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CallbackWrapper<Result<Company>>() {
                    @Override
                    protected void onSuccess(Result<Company> userResult) {

                        generalList(userResult);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onFail(String t) {
                        ToastUtils.showToastShort(CompanyActivity.this,t);
                    }
                });
    }

    private void generalList(Result<Company> userResult) {
        List<String> strings1 = new ArrayList<>();
        strings1.add("公司名称");
        strings1.add(userResult.getData().getName());

        List<String> strings2 = new ArrayList<>();
        strings2.add("税号");
        strings2.add(userResult.getData().getTaxNum());

        List<String> strings3 = new ArrayList<>();
        strings3.add("开户行");
        strings3.add(userResult.getData().getBankAccount());

        List<String> strings4 = new ArrayList<>();
        strings4.add("开户行账号");
        strings4.add(userResult.getData().getBank());

        List<String> strings5 = new ArrayList<>();
        strings5.add("开票地址");
        strings5.add(userResult.getData().getBillAddress());

        List<String> strings6 = new ArrayList<>();
        strings6.add("传真");
        strings6.add(userResult.getData().getFaxNum());

        List<String> strings7 = new ArrayList<>();
        strings7.add("一般纳税人");
        strings7.add(userResult.getData().getBillAddress());

        List<String> strings8 = new ArrayList<>();
        strings8.add("公司类型");
        strings8.add(userResult.getData().getCompanyTypes());

        List<String> strings9 = new ArrayList<>();
        strings9.add("认证状态");
        strings9.add("It’s often said that Amazon and Google operate at scales far removed from most organizations, so the solutions they needed may not be relevant to an average organization. While it’s true that most software projects don’t need that level of scale, it’s also true that more and more organizations are beginning to explore what they can do by capturing and processing more data—and to run into the same problems. So, as more information leaked out about what Google and Amazon had done, people began to explore making databases along similar lines—explicitly designed to live in a world of clusters. While the earlier menaces to relational dominance turned out to be phantoms, the threat from clusters was serious.");

        objectList.add(strings1);
        objectList.add(strings2);
        objectList.add(strings3);
        objectList.add(strings4);
        objectList.add(strings5);
        objectList.add(strings6);
        objectList.add(strings7);
        objectList.add(strings8);
        objectList.add(strings9);
    }
}