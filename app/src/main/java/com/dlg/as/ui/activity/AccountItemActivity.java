package com.dlg.as.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.dlg.as.R;
import com.dlg.as.base.BaseActivity;
import com.dlg.as.util.ARouterUtil;
import com.dlg.as.util.AccountCurd;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = ARouterUtil.ACCOUNT_ITEM_ACTIVITY)
public class AccountItemActivity extends BaseActivity {

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

    @Autowired(name = "id")
    String id;
    @Autowired(name = "name")
    String name;
    @Autowired(name = "accountId")
    String acId;
    @Autowired(name = "passwd")
    String passwd;
    @Autowired(name = "phone")
    String phone;
    @Autowired(name = "email")
    String email;
    @Autowired(name = "desc")
    String desc;


    private String TAG=AccountItemActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_item);
        //设置状态栏颜色
        StatusBarCompat.setStatusBarColor(this,
                getResources().getColor(R.color.cffffff));
        ARouter.getInstance().inject(this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    // 初始化视图
    private void initView() {
        if (id!=null){
            accountName.setText(name);
            accountId.setText(acId);
            accountPasswd.setText(passwd);
            accountPhone.setText(phone);
            accountEmail.setText(email);
            describe.setText(desc);
        }
    }

    @OnClick(R.id.confirm_update)
    public void confirmUpdate(){
        // 如果account等于空则表示新建 否则更新
        ArrayList<String> list=new ArrayList<String>();
        if (id==null || id.trim().length()==0){
            list.add(0,AccountCurd.getId());
        }else{
            list.add(0,id);
        }
        list.add(1,accountName.getText().toString());
        list.add(2,accountId.getText().toString());
        list.add(3,accountPasswd.getText().toString());
        list.add(4,accountPhone.getText().toString());
        list.add(5,accountEmail.getText().toString());
        list.add(6,describe.getText().toString());
        //添加到Share文件中
        boolean ifSuccess=AccountCurd.addUpdateAccount(list);
        if (ifSuccess){
            Toast.makeText(this,"保存/修改成功！",Toast.LENGTH_SHORT).show();
            onBackPressed();
        }else{
            Toast.makeText(this,"保存/修改失败！",Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.back)
    public void backOnclick(){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
