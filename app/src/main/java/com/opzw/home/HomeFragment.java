package com.opzw.home;

import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.opzw.R;
import com.opzw.base.BaseFragment;
import com.opzw.bean.Result;
import com.opzw.bean.Token;
import com.opzw.bean.User;
import com.opzw.guide.nav.OnTabReselectListener;
import com.opzw.login.CompanyActivity;
import com.opzw.login.LoginActivity;
import com.opzw.login.PersonalActivity;
import com.opzw.service.ApiManager;
import com.opzw.service.CallbackWrapper;
import com.opzw.utils.SharedPrefUtils;
import com.opzw.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 文 件 名: TestFragment
 * 创 建 人: Cartman
 * 创建日期: 2017/11/24 11:11
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class HomeFragment extends BaseFragment implements OnTabReselectListener {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
    }

    @Override
    public void onTabReselect() {

    }

    @Override
    protected void initData() {
        super.initData();

    }
}
