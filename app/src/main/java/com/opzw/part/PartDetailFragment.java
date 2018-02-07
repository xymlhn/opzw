package com.opzw.part;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.OptionsPickerView;
import com.jakewharton.rxbinding2.view.RxView;
import com.opzw.R;
import com.opzw.base.BaseFragment;
import com.opzw.bean.Order;
import com.opzw.guide.nav.OnTabReselectListener;
import com.opzw.order.OrderAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * 文 件 名: TestFragment
 * 创 建 人: Cartman
 * 创建日期: 2017/11/24 11:11
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class PartDetailFragment extends BaseFragment implements OnTabReselectListener {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_part_detail;
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
