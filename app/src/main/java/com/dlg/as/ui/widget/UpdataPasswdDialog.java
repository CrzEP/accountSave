package com.dlg.as.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.dlg.as.R;

/**
 * author: crzep
 * create time: 2020/9/1
 * description:
 * Version: 1.0
 **/
public class UpdataPasswdDialog extends Dialog {

    private UpdataPasswdDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Buidler{

        UpdataPasswdDialog dialog;
        View mLayout;

        public Buidler(Context context){
            dialog=new UpdataPasswdDialog(context, R.style.NoticeDialogStyle);
            LayoutInflater inflater=
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //加载布局文件
            mLayout = inflater.inflate(R.layout.alert_dialog_hint_layout,
                    null,false);
            //添加布局文件到Dialog
            dialog.addContentView(mLayout,new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
        }

    }

}
