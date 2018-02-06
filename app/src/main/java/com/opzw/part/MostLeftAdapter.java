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
 * 文 件 名: MostLeftAdapter
 * 创 建 人: Cartman
 * 创建日期: 2018/2/6 14:53
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */


public class MostLeftAdapter extends RecyclerView.Adapter {

    private Context mContext;

    private List<String> mList = new ArrayList<>();
    private MyItemClickListener mItemClickListener;
    private int mPosition;
    private static int TYPE_NORMAL = 101;
    private static int TYPE_SELECT = 102;

    public MostLeftAdapter(Context context) {
        mContext = context;
        mPosition = 0;
    }

    public void setList(List<String> list) {
        mList = list;
    }

    public List<String> getList() {
        return mList;
    }

    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //根据type显示布局，选中与未选中，当然你可以自行修改
        if (viewType == TYPE_SELECT) {
            final View view = LayoutInflater.from(mContext).inflate(R.layout.most_left_list_item, parent, false);
            return new ItemViewSelectHolder(view, mItemClickListener);
        } else {
            final View view = LayoutInflater.from(mContext).inflate(R.layout.most_left_list_item, parent, false);
            return new ItemViewHolder(view, mItemClickListener);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder item = (ItemViewHolder) holder;
        item.mostLeftText.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mPosition) {
            return TYPE_SELECT;
        } else {
            return TYPE_NORMAL;
        }
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    public interface MyItemClickListener {
        void onItemClick(View view, int postion);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private MyItemClickListener mListener;

        TextView mostLeftText;
        View leftView;

        public ItemViewHolder(View itemView, MyItemClickListener listener) {
            super(itemView);
            this.mListener = listener;
            mostLeftText = itemView.findViewById(R.id.most_left_text);
            leftView = itemView.findViewById(R.id.leftView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getLayoutPosition());
            }
        }
    }

    private class ItemViewSelectHolder extends ItemViewHolder {
        public ItemViewSelectHolder(View view, MyItemClickListener listener) {
            super(view, listener);
            mostLeftText.setTextColor(mContext.getResources().getColor(R.color.mainColor));
            leftView.setVisibility(View.VISIBLE);
        }
    }
}
