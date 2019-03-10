package com.example.user.testapphandh.helpers;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.user.testapphandh.R;

public enum DialogManager {
    Instance;

    private MaterialDialog dialog;

    public void showAlert(Context context, int title, int contentText, MaterialDialog.SingleButtonCallback positiveCallback) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(title)
                .content(contentText)
                .positiveText(R.string.ok_btn)
                .onPositive(positiveCallback);
        dialog = builder.build();
        dialog.show();
    }

    public void showProgressDialog(Context context, int message) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .content(message)
                .progress(true, 0)
                .cancelable(false);
        dialog = builder.build();
        dialog.show();
    }

    public void hide() {
        if (dialog != null && dialog.isShowing()) {
            Utils.p("dialog.dismiss()");
            dialog.dismiss();
        }
    }
}
