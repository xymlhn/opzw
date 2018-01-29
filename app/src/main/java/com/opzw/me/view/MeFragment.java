package com.opzw.me.view;

import com.opzw.R;
import com.opzw.base.BaseFragment;
import com.opzw.bean.Result;
import com.opzw.guide.nav.OnTabReselectListener;
import com.opzw.login.model.bean.Token;
import com.opzw.login.model.bean.User;
import com.opzw.service.ApiManager;
import com.opzw.service.CallbackWrapper;
import com.opzw.utils.SharedPrefUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }


    @Override
    public void onTabReselect() {

    }



    @Override
    public void onStart() {
        super.onStart();
        Token token = SharedPrefUtils.getToken();
        Map<String,Object> map = new HashMap<>();
        map.put("companyId",token.getCompanyId());
        map.put("appId",token.getAppId());
        map.put("userId",token.getId());
        ApiManager.getInstence().getService().getUser(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CallbackWrapper<Result<User>>(this) {
                    @Override
                    protected void onSuccess(Result<User> userResult) {
                        userResult.getStatus();
                    }
                });
    }

}
