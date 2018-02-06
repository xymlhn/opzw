package com.opzw.part;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.opzw.R;

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


public class MostRightAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<MostSeriesBean> mList = new ArrayList<>();
    private MyItemClickListener mItemClickListener;

    public MostRightAdapter(Context context) {
        mContext = context;
    }

    public void setList(List<MostSeriesBean> list) {
        if (list!=null){
            mList = list;
        }

    }

    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.series_list_item, parent, false);
        return new ItemViewHolder(view, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder item = (ItemViewHolder) holder;
        final MostSeriesBean bean = mList.get(position);
        item.mostRightName.setText(bean.getName());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public interface MyItemClickListener {
        void onItemClick(View view, int postion);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private MyItemClickListener mListener;
        TextView mostRightName;

        public ItemViewHolder(View itemView, MyItemClickListener listener) {
            super(itemView);
            this.mListener = listener;
            mostRightName = itemView.findViewById(R.id.series_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getLayoutPosition());
            }
        }
    }
}
