package com.example.user.testapphandh.helpers;

import android.content.Context;
import android.content.DialogInterface;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.user.testapphandh.R;

public class DialogManager {

    public static void showAlert(Context context, int title, int contentText, MaterialDialog.SingleButtonCallback positiveCallback) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(title)
                .content(contentText)
                .positiveText(R.string.ok_btn)
                .onPositive(positiveCallback);
        MaterialDialog dialog = builder.build();
        dialog.show();
    }

    public static void showProgressDialog(Context context, int title, int message) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .content(R.string.please_wait)
                .progress(true, 0)
                .cancelable(false);
        MaterialDialog dialog = builder.build();
        dialog.show();
    }
}
