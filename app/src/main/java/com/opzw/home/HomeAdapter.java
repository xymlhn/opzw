package com.opzw.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.opzw.R;

import java.util.List;

/**
 * 文 件 名: PersonalAdapter
 * 创 建 人: Cartman
 * 创建日期: 2018/1/25 13:49
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class HomeAdapter extends RecyclerView.Adapter {
    private List<List<Integer>> mapList;
    private Context context;

    public HomeAdapter(Context context, List<List<Integer>> mapList) {
        this.context = context;
        this.mapList = mapList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_home, parent, false);
        return new VHolder(view);


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        List<Integer> map = mapList.get(position);

        ((VHolder) holder).icon.setImageResource(map.get(1));
        ((VHolder) holder).value.setText(map.get(0));

    }

    @Override
    public int getItemCount() {
        return mapList.size();
    }


    class VHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView value;

        public VHolder(View view) {
            super(view);
            icon = view.findViewById(R.id.icon);
            value = view.findViewById(R.id.content);
        }
    }


}
