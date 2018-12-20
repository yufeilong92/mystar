package com.example.dell.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: Dialoge.java
 * @Package com.example.dell.myapplication
 * @Description: todo
 * @author: YFL
 * @date: 2018/12/19 23:00
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018/12/19 星期三
 * 注意：本内容仅限于学川教育有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class Dialoge extends DialogFragment {
    private TextView mTvContent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_reycler_item, null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        mTvContent.setText("2haofhapudhpuoashdhasd");
    }

    private void initView(View view) {
        mTvContent = (TextView) view.findViewById(R.id.tv_content);
    }
}
