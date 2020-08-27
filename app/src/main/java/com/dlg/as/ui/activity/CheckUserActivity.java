package com.dlg.as.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.dlg.as.R;
import com.dlg.as.base.BaseActivity;
import com.dlg.as.common.ShareKey;
import com.dlg.as.util.ARouterUtil;
import com.dlg.as.util.SharedPreferencesUtil;
import com.githang.statusbar.StatusBarCompat;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = ARouterUtil.CHECK_USER_ACTIVITY)
public class CheckUserActivity extends BaseActivity {

    @BindView(R.id.passwd)
    EditText passwd;
    @BindView(R.id.confirm_passwd_hint)
    EditText confirmPasswdHint;
    @BindView(R.id.confirm_passwd)
    EditText confirmPasswd;
    @BindView(R.id.confirm)
    TextView confirm;
    @BindString(R.string.passwd_must_match)
    String passwdMustMatch;

    // share 密码
    private String pass;

    // 用户设置密码是的存储空间
    private String pass1;
    private String pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_user);
        //设置状态栏颜色
        StatusBarCompat.setStatusBarColor(this,
                getResources().getColor(R.color.cffffff));
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    // 初始化
    private void init() {
        pass= (String) SharedPreferencesUtil.getData(ShareKey.ACCOUNT_PASSWD,"");
        if (null == pass || pass.trim().length()==0){
            confirmPasswdHint.setVisibility(View.VISIBLE);
            confirmPasswd.setVisibility(View.VISIBLE);
            confirm.setVisibility(View.VISIBLE);
            setPasswd();
        }else{
            confirmPasswdHint.setVisibility(View.GONE);
            confirmPasswd.setVisibility(View.GONE);
            confirm.setVisibility(View.GONE);
            passwdCheck();
        }
    }

    /**
     * 设置密码
     */
    private void setPasswd() {

        passwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                pass1=s.toString();
            }
        });
        confirmPasswd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                pass2=s.toString();
            }
        });
        confirm.setOnClickListener(v -> {
            if (pass1.length()>0&&pass2.length()>0&&pass1.equals(pass2)){
                ARouter.getInstance()
                        .build(ARouterUtil.MAIN_ACTIVITY)
                        .navigation();
            }else{
                Toast.makeText(getBaseContext(),passwdMustMatch,Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 密码检查
     */
    private void passwdCheck() {
        passwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0 && s.toString().equals(pass)){
                    ARouter.getInstance()
                            .build(ARouterUtil.MAIN_ACTIVITY)
                            .navigation();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}
