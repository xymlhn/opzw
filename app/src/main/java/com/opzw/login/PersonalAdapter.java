package com.opzw.login;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.opzw.R;

import java.util.List;
import java.util.Map;

/**
 * 文 件 名: PersonalAdapter
 * 创 建 人: Cartman
 * 创建日期: 2018/1/25 13:49
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class PersonalAdapter extends RecyclerView.Adapter {
    private List<List<String>> mapList;
    private Context context;

    public PersonalAdapter(Context context,List<List<String>> mapList) {
        this.context = context;
        this.mapList = mapList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 10){
            View view = LayoutInflater.from(context).inflate(R.layout.adapter_company, parent, false);
            return new CHolder(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.adapter_personal, parent, false);
            return new VHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        List<String> map = mapList.get(position);
        if (map.get(0).equals("开票地址")){
            ((CHolder)holder).name.setText(map.get(0));
            ((CHolder)holder).value.setText(map.get(1));
        }else {
            ((VHolder)holder).name.setText(map.get(0));
            ((VHolder)holder).value.setText(map.get(1));
        }
    }

    @Override
    public int getItemCount() {
        return mapList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(mapList.get(position).get(0).equals("开票地址")){
            return 10;
        }else {
            return 20;
        }
    }

    class VHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView value;

        public VHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            value = view.findViewById(R.id.value);
        }
    }

    class CHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView value;

        public CHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            value = view.findViewById(R.id.value);
        }
    }
}
