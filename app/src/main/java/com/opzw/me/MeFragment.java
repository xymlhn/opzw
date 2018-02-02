package com.opzw.me;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.opzw.R;
import com.opzw.base.BaseFragment;
import com.opzw.bean.Company;
import com.opzw.bean.Result;
import com.opzw.guide.nav.OnTabReselectListener;
import com.opzw.bean.Token;
import com.opzw.bean.User;
import com.opzw.login.LoginActivity;
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


public class MeFragment extends BaseFragment implements OnTabReselectListener {
    private TextView phontText;
    private TextView companyText;
    private View personBtn;
    private View companyBtn;
    private View logoutBtn;
    private ImageView company;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        phontText = root.findViewById(R.id.phoneText);
        companyText = root.findViewById(R.id.companyText);
        logoutBtn = root.findViewById(R.id.logoutBtn);
        personBtn = root.findViewById(R.id.personBtn);
        companyBtn = root.findViewById(R.id.companyBtn);
        company = root.findViewById(R.id.company);
    }

    @Override
    public void onTabReselect() {

    }

    @Override
    protected void initData() {
        super.initData();
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
                        phontText.setText(userResult.getData().getMobile());
                        companyText.setText(userResult.getData().getCompanyName());
                    }

                    @Override
                    protected void onFail(String t) {
                        ToastUtils.showToastShort(getContext(),t);
                    }
                });
        Map<String,Object> map1 = new HashMap<>();
        map1.put("companyId",token.getCompanyId());
        map1.put("appId",token.getAppId());
        map1.put("userId",token.getId());
        ApiManager.getInstence().getService().getCompany(map1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CallbackWrapper<Result<Company>>() {
                    @Override
                    protected void onSuccess(Result<Company> userResult) {
                        company.setImageResource(userResult.getData().getAuthStatus() != 30 ?R.mipmap.auth_disable : R.mipmap.auth);
                    }

                    @Override
                    protected void onFail(String t) {
                        ToastUtils.showToastShort(getContext(),t);
                    }
                });

        RxView.clicks(logoutBtn)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        getActivity().finish();
                        LoginActivity.openActivity(getContext());
                        SharedPrefUtils.removeParam(getContext(),SharedPrefUtils.TOKEN);
                    }
                });

        RxView.clicks(personBtn)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        PersonalActivity.openActivity(getContext());
                    }
                });
        RxView.clicks(companyBtn)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        CompanyActivity.openActivity(getContext());
                    }
                });
    }
}
