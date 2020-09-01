package com.dlg.as.ui.activity;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dlg.as.R;
import com.dlg.as.base.BaseActivity;
import com.dlg.as.common.ShareKey;
import com.dlg.as.util.ARouterUtil;
import com.dlg.as.util.SharedPreferencesUtil;
import com.githang.statusbar.StatusBarCompat;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = ARouterUtil.SETTING_ACTIVITY)
public class SettingActivity extends BaseActivity {

    @BindView(R.id.passwd_confirm_const)
    ConstraintLayout passwdConfirmConst;
    @BindView(R.id.body)
    ConstraintLayout body;
    @BindView(R.id.passwd_confirm)
    EditText passwdConfirm;
    @BindView(R.id.app_lock_switch)
    Switch appLockSwitch;
    @BindView(R.id.switch_hint)
    TextView switchHint;

    @BindString(R.string.error_passwd)
    String errorPasswd;
    @BindString(R.string.update_success)
    String updateSuccess;
    @BindString(R.string.update_fail)
    String updateFail;
    @BindString(R.string.start_off)
    String startOff;
    @BindString(R.string.start_on)
    String startOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //设置状态栏颜色
        StatusBarCompat.setStatusBarColor(this,
                getResources().getColor(R.color.cffffff));
        ButterKnife.bind(this);
        boolean appLockState= (boolean) SharedPreferencesUtil.getData(ShareKey.ACCOUT_APP_IN_PASSWD_USE_KEY,Boolean.TRUE);
        switchHint.setText(appLockState?startOn:startOff);
        appLockSwitch.setChecked(appLockState);
        appLockSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            switchHint.setText(isChecked?startOn:startOff);
            SharedPreferencesUtil.putData(ShareKey.ACCOUT_APP_IN_PASSWD_USE_KEY,
                    isChecked);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        passwdConfirmConst.setVisibility(View.VISIBLE);
        body.setVisibility(View.GONE);
    }

    @OnClick(R.id.confirm)
    public void confirmOnClick(){
        String pass= (String) SharedPreferencesUtil.getData(ShareKey.ACCOUNT_PASSWD,"");
        if (passwdConfirm.getText().toString().equals(pass)){
            passwdConfirmConst.setVisibility(View.GONE);
            body.setVisibility(View.VISIBLE);
        }else{
            Toast.makeText(this,errorPasswd,Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.passwd_update_const)
    public void passwdUpdateConstOnClick(){

    }

    @OnClick(R.id.back)
    public void backOnClick(){
        this.onBackPressed();
    }

}
