package com.opzw.login;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.opzw.App;
import com.opzw.R;
import com.opzw.service.ApiManager;
import com.opzw.service.CallbackWrapper;
import com.opzw.utils.LogUtils;
import com.opzw.utils.SharedPrefUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 文 件 名: PersonalActivity
 * 创 建 人: Cartman
 * 创建日期: 2018/1/25 13:48
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class PersonalActivity extends Activity {
    private RecyclerView recyclerView;
    private PersonalAdapter mAdapter;
    private List<List<String>> objectList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_personal);
        recyclerView = findViewById(R.id.recyle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        objectList = new ArrayList<>();
        mAdapter = new PersonalAdapter(this, objectList);
        recyclerView.setAdapter(mAdapter);
    }
}
