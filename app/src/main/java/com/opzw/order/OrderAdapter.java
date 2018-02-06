package com.opzw.order;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.opzw.R;
import com.opzw.bean.Order;

import java.util.List;

/**
 * 文 件 名: PersonalAdapter
 * 创 建 人: Cartman
 * 创建日期: 2018/1/25 13:49
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class OrderAdapter extends RecyclerView.Adapter {
    private List<Order> orderList;
    private Context context;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_purchase, parent, false);
        return new CHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Order order = orderList.get(position);
        ((CHolder)holder).hetong.setText("采购合同号:" + order.getOrderCode());
        ((CHolder)holder).fukuan.setText((order.getPurOrderStatus() == 20 || order.getPurOrderStatus() == 30 || order.getPurOrderStatus() == 40)?
                "已生效" : order.getPurOrderStatus() == 50? "已发货" : order.getPurOrderStatus() == 90? "已撤销" : "待生效" );
        ((CHolder)holder).createtime.setText("创建时间:" + "2015-07");
        ((CHolder)holder).type.setText(order.getProviderName());
        ((CHolder)holder).zhognliang.setText(order.getTotalWeight() + "吨");
        ((CHolder)holder).money.setText("¥" + order.getOrderAmt());

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }


    class CHolder extends RecyclerView.ViewHolder {
        private TextView hetong;
        private TextView fukuan;
        private TextView createtime;
        private TextView type;
        private TextView zhognliang;
        private TextView money;
        private LinearLayout jinduBtn;

        public CHolder(View view) {
            super(view);
            hetong = view.findViewById(R.id.hetongId);
            fukuan = view.findViewById(R.id.fukuan);
            createtime = view.findViewById(R.id.createTime);
            type = view.findViewById(R.id.type);
            zhognliang = view.findViewById(R.id.zhognliang);
            money = view.findViewById(R.id.money);
            jinduBtn = view.findViewById(R.id.jindu);
        }
    }
}
