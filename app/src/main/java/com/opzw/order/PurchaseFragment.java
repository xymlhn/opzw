package com.opzw.order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.OptionsPickerView;
import com.jakewharton.rxbinding2.view.RxView;
import com.opzw.R;
import com.opzw.base.BaseFragment;
import com.opzw.bean.Order;
import com.opzw.guide.nav.OnTabReselectListener;
import com.opzw.login.LoginActivity;
import com.opzw.utils.SharedPrefUtils;

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


public class PurchaseFragment extends BaseFragment implements OnTabReselectListener {
    private LinearLayout supplierBtn;
    private LinearLayout statusBtn;
    private LinearLayout specBtn;
    private LinearLayout monthBtn;
    private static final String[] CHANNELS = new String[]{"采购订单", "销售订单" };
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private OrderAdapter orderAdapter;
    private RecyclerView recyclerView;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_purchase;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        supplierBtn = root.findViewById(R.id.gongyignshang);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RxView.clicks(supplierBtn)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        //条件选择器
                        OptionsPickerView pvOptions = new  OptionsPickerView.Builder(getContext(), new OptionsPickerView.OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                                //返回的分别是三个级别的选中位置

                            }
                        }).isCenterLabel(true).build();
                        pvOptions.setPicker(mDataList,null ,null );
                        pvOptions.show();
                    }
                });
    }

    @Override
    public void onTabReselect() {

    }

    @Override
    protected void initData() {
        super.initData();
        orderAdapter = new OrderAdapter(getContext(), generalList());
        recyclerView.setAdapter(orderAdapter);
    }

    private List<Order> generalList(){
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setOrderCode("FP160905000" + i);
            order.setCreateDatetime(new Date());
            order.setPurOrderStatus(30);
            order.setOrderAmt(21002.63);
            order.setProviderName("热轧板卷");
            order.setTotalWeight(5.250);
            orders.add(order);
        }
        return orders;
    }
}
