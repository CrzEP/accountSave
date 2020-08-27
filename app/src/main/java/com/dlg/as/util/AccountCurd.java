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

    /**
     * 增加、修改账户信息
     * @param accountList 账户列表
     * @return true 成功 false 失败
     */
    public static boolean addUpdateAccount(ArrayList<String> accountList){// 保存账户信息
        boolean listKey=SharedPreferencesUtil.putListData(accountList.get(0),accountList);
        if (!listKey){
            return false;
        }
        // 先获取原来的key集合
        ArrayList<String> list= (ArrayList<String>) SharedPreferencesUtil
                        .getListData(ShareKey.ACCOUNT_ALL_ACCOUNT_LIST,String.class);
        if (!list.contains(accountList.get(0))){
            // 添加
            list.add(accountList.get(0));
        }
        return SharedPreferencesUtil.putListData(ShareKey.ACCOUNT_ALL_ACCOUNT_LIST,list);
    }

    /**
     * 删除账户信息
     * @param accountList 账户列表
     * @return true 成功 false 失败
     */
    public static boolean deleteAccount(ArrayList<String> accountList){
        // 先获取原来的key集合
        ArrayList<String> list= (ArrayList<String>) SharedPreferencesUtil
                .getListData(ShareKey.ACCOUNT_ALL_ACCOUNT_LIST,String.class);
        // 删除
        boolean bo1=list.remove(accountList.get(0));
        boolean bo2=SharedPreferencesUtil.putListData(ShareKey.ACCOUNT_ALL_ACCOUNT_LIST,list);
        // 全真才为真
        if (!(bo1 && bo2)){
            return false;
        }
        boolean shareKey= SharedPreferencesUtil.deleteData(accountList.get(0));
        if (shareKey){
            return true;
        }
        // 重新添加
        list.add(accountList.get(0));
        SharedPreferencesUtil.putListData(ShareKey.ACCOUNT_ALL_ACCOUNT_LIST,list);
        return false;
    }

    // 获取一个id
    public static String getId(){
        return String.valueOf(System.currentTimeMillis());
    }

}
