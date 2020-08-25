package com.dlg.as.util;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dlg.as.App;

/**
 * author: crzep
 * create time: 2020/8/25
 * description: 路由组件
 * Version: 1.0
 **/
public class ARouterUtil {

    // 项目一级路由
    public final static String ACCOUNT_SAVE="/accountSave";

    // 主界面
    public final static String MAIN_ACTIVITY=ACCOUNT_SAVE+"/main_activity";
    // 检查用户activity
    public final static String CHECK_USER_ACTIVITY=ACCOUNT_SAVE+"/check_user_activity";
    // 设置界面
    public final static String SETTING_ACTIVITY=ACCOUNT_SAVE+"/setting_activity";
    // 账户详情页面
    public final static String ACCOUNT_ITEM_ACTIVITY=ACCOUNT_SAVE+"/account_item_activity";


    public ARouterUtil() {
        throw new IllegalStateException("Can't instantiate this!");
    }

    /**
     * 初始化路由组件
     * @param app 全局APP
     */
    public static void initRouter(Application app){
        // 路由组件
        if (App.DEBUG) {
            // 打印日志
            ARouter.openDebug();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openLog();
        }
        ARouter.init(app);
    }



}
