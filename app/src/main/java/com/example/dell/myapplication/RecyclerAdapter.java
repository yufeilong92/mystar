package com.example.dell.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: RecyclerAdapter.java
 * @Package com.example.dell.myapplication
 * @Description: todo
 * @author: YFL
 * @date: 2018/12/19 21:36
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018/12/19 星期三
 * 注意：本内容仅限于学川教育有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList mData;
    private final LayoutInflater mInflater;

    public RecyclerAdapter(Context mContext, ArrayList mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.main_reycler_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTvContent.setText((CharSequence) mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mTvContent = (TextView) itemView.findViewById(R.id.tv_content);

        }
    }

}
