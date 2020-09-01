package com.dlg.as.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dlg.as.R;

/**
 * author:Crzep
 * create time:2020/3/12
 * description:提示弹出框
 **/
public class HintDialog extends Dialog {

    private HintDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder{

        TextView title;
        TextView hint;
        TextView left;
        TextView right;
        HintDialog hintDialog;
        View mLayout;

        public Builder(Context context){
            hintDialog=new HintDialog(context, R.style.NoticeDialogStyle);
            hintDialog.setCanceledOnTouchOutside(true);
            LayoutInflater inflater=
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //加载布局文件
            mLayout = inflater.inflate(R.layout.alert_dialog_hint_layout,
                    null,false);
            //添加布局文件到Dialog
            hintDialog.addContentView(mLayout,new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            title=mLayout.findViewById(R.id.alert_title);
            hint=mLayout.findViewById(R.id.hint);
            left=mLayout.findViewById(R.id.tv_left);
            right=mLayout.findViewById(R.id.tv_right);
        }

        /**
         * 设置主题
         * @param tit 主题
         * @return 链式编程
         */
        public Builder setTitle(String tit){
            title.setText(tit);
            return this;
        }

        /**
         * 设置提示信息
         * @param hin
         * @return
         */
        public Builder setHint(String hin){
            hint.setText(hin);
            return this;
        }

        /**
         * 设置左侧按钮
         * @param leftTv 左边字符
         * @return 链式编程
         */
        public Builder setLeftTv(String leftTv,View.OnClickListener listener){
            left.setText(leftTv);
            left.setOnClickListener(v -> {
                hintDialog.dismiss();//dialog消失
                listener.onClick(v);//调用传入监听
            });
            return this;
        }

        /**
         * 设置右侧按钮与监听
         * @param rightTv 左边字符
         * @param listener 监听
         * @return 链式编程
         */
        public Builder setRightTv(String rightTv,View.OnClickListener listener){
            right.setText(rightTv);
            right.setOnClickListener(v -> {
                hintDialog.dismiss();//dialog消失
                listener.onClick(v);//调用传入监听
            });
            return this;
        }

        /**
         * 创建
         * @return 链式编程
         */
        public HintDialog create(){
            hintDialog.setContentView(mLayout);
            hintDialog.setCancelable(true);//允许点击外部关闭
            return  hintDialog;
        }

    }
}
