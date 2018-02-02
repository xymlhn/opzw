package com.opzw.home;

import android.view.View;

import com.opzw.R;
import com.opzw.base.BaseFragment;
import com.opzw.guide.nav.OnTabReselectListener;

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
