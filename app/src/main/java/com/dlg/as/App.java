package com.dlg.as;

import android.app.Application;
import android.content.Context;

import com.dlg.as.common.ShareKey;
import com.dlg.as.util.ARouterUtil;
import com.dlg.as.util.DensityUtils;
import com.dlg.as.util.SharedPreferencesUtil;

/**
 * author: crzep
 * create time: 2020/8/25
 * description:
 * Version: 1.0
 **/
public class App extends Application {

    public static final boolean DEBUG=Boolean.TRUE;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 屏幕适配方案
        DensityUtils.setDensity(this,360);
        // 路由组件
        ARouterUtil.initRouter(this);
        // 初始化SharePf工具
        SharedPreferencesUtil.getInstance(this, ShareKey.ACCOUNT_SHAREDPRE_NAME);
    }

}
