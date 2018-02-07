package com.opzw.part;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.opzw.R;
import com.opzw.bean.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名: MostRightAdapter
 * 创 建 人: Cartman
 * 创建日期: 2018/2/6 14:54
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class PartListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Car> mList = new ArrayList<>();
    private ItemClickListener mItemClickListener;
    public PartListAdapter(Context context) {
        mContext = context;
    }

    public void setList(List<Car> list) {
        if (list!=null){
            mList = list;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_part_list, parent, false);
        return new ItemViewHolder(view, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder item = (ItemViewHolder) holder;
        final Car car = mList.get(position);
        item.code.setText(car.getCode());
        item.spec.setText(car.getSpec());
        item.guige.setText(car.getGuige());
        item.name.setText(car.getName());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView code;
        TextView name;
        TextView spec;
        TextView guige;
        ImageView icon;
        private ItemClickListener mListener;
        public ItemViewHolder(View itemView, ItemClickListener listener) {
            super(itemView);
            code = itemView.findViewById(R.id.code);
            spec = itemView.findViewById(R.id.spec);
            name = itemView.findViewById(R.id.name);
            guige = itemView.findViewById(R.id.guige);
            icon = itemView.findViewById(R.id.icon);
            this.mListener = listener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getLayoutPosition());
            }
        }
    }
    public interface ItemClickListener {
        void onItemClick(View view, int postion);
    }
}
