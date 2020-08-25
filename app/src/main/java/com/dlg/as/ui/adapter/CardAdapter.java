package com.dlg.as.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dlg.as.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author: crzep
 * create time: 2020/8/25
 * description: 卡片适配器
 * Version: 1.0
 **/
public class CardAdapter extends BaseQuickAdapter<ArrayList<String>, BaseViewHolder> {

    // 传入的数据
    private List list;

    public CardAdapter(int layoutResId, @Nullable List<ArrayList<String>> data) {
        super(layoutResId, data);
        this.list=data;
    }

    @Override
    protected void convert(BaseViewHolder helper, ArrayList<String> item) {
        helper.setText(R.id.title,item.get(0))
                .setText(R.id.introduce,item.get(1));
    }
}
