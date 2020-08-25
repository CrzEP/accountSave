package com.dlg.as.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dlg.as.R;
import com.dlg.as.base.BaseActivity;
import com.dlg.as.common.ShareKey;
import com.dlg.as.ui.adapter.CardAdapter;
import com.dlg.as.util.ARouterUtil;
import com.dlg.as.util.SharedPreferencesUtil;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = ARouterUtil.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    private CardAdapter cardAdapter;
    private List<ArrayList<String>> accounts=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置状态栏颜色
        StatusBarCompat.setStatusBarColor(this,
                getResources().getColor(R.color.cffffff));
        ButterKnife.bind(this);
        initData();
        initView();
    }

    // 初始化视图
    private void initView() {
        cardAdapter=new CardAdapter(R.layout.account_item_card_layout,accounts);
        cardAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ArrayList<String> item=cardAdapter.getItem(position);
                if (null!=item) {
                    jumpToItemActivity(item);
                }
            }
        });
        LinearLayoutManager linear=new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(linear);
        recycler.setAdapter(cardAdapter);
    }

    /**
     * 调到详细界面
     * @param item 详细界面Key
     */
    private void jumpToItemActivity(@NonNull ArrayList<String> item) {
        ARouter.getInstance()
                .build(ARouterUtil.ACCOUNT_ITEM_ACTIVITY)
                .withStringArrayList("key",item)
                .navigation();
    }

    // 初始化数据
    private void initData() {
        List<String> accountKeys = SharedPreferencesUtil.getListData(ShareKey.ACCOUNT_ALL_ACCOUNT_LIST, String.class);
        for (String key: accountKeys){
            ArrayList<String> list= (ArrayList<String>) SharedPreferencesUtil.getListData(key,String.class);
            if (list!=null){
                accounts.add(list);
            }
        }
    }

    @OnClick(R.id.add_new)
    public void addNewOnClick(){
        ARouter.getInstance()
                .build(ARouterUtil.ACCOUNT_ITEM_ACTIVITY)
                .withStringArrayList("key",null)
                .navigation();
    }

    /**
     * 设置界面跳转
     */
    @OnClick(R.id.setting)
    public void settingOnClick(){
        ARouter.getInstance()
                .build(ARouterUtil.MAIN_ACTIVITY)
                .navigation();
    }
}
