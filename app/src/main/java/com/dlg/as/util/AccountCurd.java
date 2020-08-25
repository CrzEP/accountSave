package com.dlg.as.util;

import com.dlg.as.common.ShareKey;

import java.util.ArrayList;

/**
 * author: crzep
 * create time: 2020/8/25
 * description: 账户操作类
 * Version: 1.0
 **/
public class AccountCurd {

    public static boolean addAccount(String accountName, ArrayList<String> accountList){
        // 先获取原来的key集合
        ArrayList<String> list= (ArrayList<String>) SharedPreferencesUtil
                        .getListData(ShareKey.ACCOUNT_ALL_ACCOUNT_LIST,String.class);
        // 添加
        list.add(accountName);
        // 保存key
        boolean bKey=SharedPreferencesUtil.putListData(ShareKey.ACCOUNT_ALL_ACCOUNT_LIST,list);
        if (!bKey){
            return false;
        }
        // 保存账户信息
        return SharedPreferencesUtil.putListData(accountName,accountList);
    }

    // 获取一个id
    public static String getId(){
        return String.valueOf(System.currentTimeMillis());
    }

}
