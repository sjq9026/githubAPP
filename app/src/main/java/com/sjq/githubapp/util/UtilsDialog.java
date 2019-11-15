package com.sjq.githubapp.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;


/**
 * Dialog相关工具类（使用v7包下的AlertDailog，样式都一样了，最低兼容到2.2）
 * 使用{@link #getInstance()} 调用
 */
public class UtilsDialog {

    private static UtilsDialog instance = null;

    /**
     * 私有的构造方法
     */
    private UtilsDialog() {
    }

    /**
     * 提供公共静态方法获取实例
     *
     * @return UtilsDialog的实例
     */
    public static UtilsDialog getInstance() {
        if (instance == null) {
            instance = new UtilsDialog();
        }
        return instance;
    }





    /**
     * 显示对话框
     *
     * @param context
     * @param title
     * @param message
     * @param action
     * @param l
     */
    public static void showAlertDailog(Context context, String title, String message, String action, DialogInterface.OnClickListener l) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // 设置标题
        builder.setTitle(title);
        builder.setMessage(message);
        // 离线登录
        builder.setPositiveButton(action, l);
        builder.setCancelable(false);
        builder.create().show();
    }
    /**
     * 显示对话框
     *
     * @param context
     * @param message
     * @param action
     * @param l
     */
    public static void showAlertDailog(Context context, String message, String action, DialogInterface.OnClickListener l) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // 设置提示信息
        builder.setMessage(message);
        // 离线登录
        builder.setPositiveButton(action, l);
        builder.setCancelable(false);
        builder.create().show();
    }
    /**
     * 显示对话框
     *
     * @param context
     * @param title
     * @param message
     * @param action1
     * @param l1
     * @param action2
     * @param l2
     */
    public void showAlertDailog(Context context, String title, String message, String action1, DialogInterface.OnClickListener l1, String action2, DialogInterface.OnClickListener l2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // 设置标题
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(action1, l1);
        builder.setNeutralButton(action2, l2);
        builder.setCancelable(false);
        builder.create().show();
    }

    //提示设置时间
    public void setDateTimeDialog(final Context mContext, String ServerDateTime) {
        UtilsDialog.getInstance().showAlertDailog(mContext, "提示",
                "移动设备时间与网络时间不一致，请设置正确的时间!\n" +
                        "网络时间为" + ServerDateTime,
                "去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //设置
                        Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                        mContext.startActivity(intent);
                    }
                }, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
    }
}
