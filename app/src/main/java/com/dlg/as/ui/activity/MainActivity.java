package com.dlg.as.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.dlg.as.R;
import com.dlg.as.base.BaseActivity;
import com.dlg.as.common.ShareKey;
import com.dlg.as.ui.adapter.CardAdapter;
import com.dlg.as.ui.widget.HintDialog;
import com.dlg.as.util.ARouterUtil;
import com.dlg.as.util.AccountCurd;
import com.dlg.as.util.SharedPreferencesUtil;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = ARouterUtil.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity {

    @BindView(R.id.top_bar)
    ConstraintLayout topBar;
    @BindView(R.id.null_hint)
    TextView nullHint;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindString(R.string.update_success)
    String updateSuccess;
    @BindString(R.string.update_fail)
    String updateFail;
    @BindString(R.string.confirm_delete_account)
    String confirmDeleteAccount;
    @BindString(R.string.cancle)
    String cancle;
    @BindString(R.string.confirm)
    String confirm;
    private CardAdapter cardAdapter;
    private List<ArrayList<String>> accounts=new ArrayList<>();

    private String TAG=MainActivity.class.getSimpleName();

    private HintDialog hintDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置状态栏颜色
        StatusBarCompat.setStatusBarColor(this,
                getResources().getColor(R.color.cffffff));
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAccountData();
        initView();
    }

    // 初始化视图
    private void initView() {
        cardAdapter=new CardAdapter(R.layout.account_item_card_layout,accounts);
        cardAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            ArrayList<String> item=cardAdapter.getItem(position);
            if (item!=null){
                if (view.getId()==R.id.delete){
                    // 删除点击侦听
                    deleteAccount(item);
                }else if(view.getId()==R.id.item_const){
                    // 详情点击侦听
                    jumpToItemActivity(item);
                }
            }
        });
        // 布局模型
        LinearLayoutManager linear=new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(linear);
        recycler.setAdapter(cardAdapter);
        emptyDataView();
    }

    /**
     * 刷新数据
     */
    private void refreshData(){
        cardAdapter.setNewData(getAccountData());
        cardAdapter.notifyDataSetChanged();
        emptyDataView();
    }

    /**
     * 判断有无数据，更换显示视图
     */
    private void emptyDataView(){
        if (accounts.size()>0){
            recycler.setVisibility(View.VISIBLE);
            nullHint.setVisibility(View.GONE);
        }else{
            recycler.setVisibility(View.GONE);
            nullHint.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 删除账户
     * @param item 账户
     */
    private void deleteAccount(ArrayList<String> item) {
        View.OnClickListener leftListener= v ->{
            Toast.makeText(getBaseContext(), updateFail, Toast.LENGTH_SHORT).show();
        };
        View.OnClickListener rightListener=v-> {
            if (AccountCurd.deleteAccount(item)) {
                refreshData();
                Toast.makeText(getBaseContext(), updateSuccess, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getBaseContext(), updateFail, Toast.LENGTH_SHORT).show();
            }
        };
        //创建弹出框
        hintDialog=new HintDialog.Builder(this)
                .setHint(String.format(confirmDeleteAccount,item.get(1)))
                .setLeftTv(cancle,
                        leftListener)
                .setRightTv(confirm,
                        rightListener)
                .create();
        hintDialog.show();
    }

    /**
     * 调到详细界面
     * @param item 详细界面Key
     */
    private void jumpToItemActivity(@NonNull ArrayList<String> item) {
        ARouter.getInstance()
                .build(ARouterUtil.ACCOUNT_ITEM_ACTIVITY)
                .withString("id",item.get(0))
                .withString("name",item.get(1))
                .withString("accountId",item.get(2))
                .withString("passwd",item.get(3))
                .withString("phone",item.get(4))
                .withString("email",item.get(5))
                .withString("desc",item.get(6))
                .navigation();
    }

    // 初始化数据
    private List<ArrayList<String>> getAccountData() {
        List<String> accountKeys = SharedPreferencesUtil.getListData(ShareKey.ACCOUNT_ALL_ACCOUNT_LIST, String.class);
        accounts=new ArrayList<>();
        for (String key: accountKeys){
            ArrayList<String> list= (ArrayList<String>) SharedPreferencesUtil.getListData(key,String.class);
            if (list!=null){
                accounts.add(list);
            }
        }
        return accounts;
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
                .build(ARouterUtil.SETTING_ACTIVITY)
                .navigation();
    }
}
