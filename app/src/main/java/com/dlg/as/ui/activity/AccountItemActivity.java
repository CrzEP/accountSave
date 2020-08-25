package com.dlg.as.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.dlg.as.R;
import com.dlg.as.common.ShareKey;
import com.dlg.as.util.ARouterUtil;
import com.dlg.as.util.AccountCurd;
import com.dlg.as.util.SharedPreferencesUtil;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = ARouterUtil.ACCOUNT_ITEM_ACTIVITY)
public class AccountItemActivity extends AppCompatActivity {

    @BindView(R.id.account_name_content)
    EditText accountName;
    @BindView(R.id.account_id_content)
    EditText accountId;
    @BindView(R.id.account_passwd_content)
    EditText accountPasswd;
    @BindView(R.id.account_phone_content)
    EditText accountPhone;
    @BindView(R.id.account_email_content)
    EditText accountEmail;
    @BindView(R.id.describe)
    EditText describe;

    @Autowired(name = "key")
    ArrayList<String> account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_item);
        //设置状态栏颜色
        StatusBarCompat.setStatusBarColor(this,
                getResources().getColor(R.color.cffffff));
        ButterKnife.bind(this);
        initView();
    }

    // 初始化视图
    private void initView() {
        if (account!=null){
            accountName.setText(account.get(1));
            accountId.setText(account.get(2));
            accountPasswd.setText(account.get(3));
            accountPhone.setText(account.get(4));
            accountEmail.setText(account.get(5));
            describe.setText(account.get(6));
        }
    }

    @OnClick(R.id.confirm_update)
    public void confirmUpdate(){
        // 如果account等于空则表示新建 否则更新
        if (account==null){
            account=new ArrayList<>();
            account.add(0,AccountCurd.getId());
        }
        account.add(1,accountName.getText().toString());
        account.add(3,accountId.getText().toString());
        account.add(4,accountPasswd.getText().toString());
        account.add(5,accountPhone.getText().toString());
        account.add(6,accountEmail.getText().toString());
        account.add(7,describe.getText().toString());
        //添加到Share文件中
        boolean ifSuccess=AccountCurd.addAccount(account.get(0),account);
        if (ifSuccess){
            Toast.makeText(this,"保存/修改成功！",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"保存/修改失败！",Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.back)
    public void backOnclick(){
        super.onBackPressed();
    }
}
