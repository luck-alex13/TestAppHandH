package com.example.user.testapphandh;

import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackbarHelper {

    public static void showSnack(View view, String text){
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }

    public static void showSnackWithButton(View view, String text, String buttonText, View.OnClickListener clickListener){
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                .setAction(buttonText, clickListener).show();
    }
}
