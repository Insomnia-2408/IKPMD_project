package com.hsleiden.ikpmd_project.Helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.RequiresApi;

import com.google.android.material.snackbar.Snackbar;
import com.hsleiden.ikpmd_project.R;

public class PopupHelper {

    private LayoutInflater service;
    private AlertDialog.Builder builder;
    private Activity activity;

   public PopupHelper(LayoutInflater service, Activity activity) {
        this.service = service;
        this.builder = new AlertDialog.Builder(activity);
        this.activity = activity;
   }

   @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
   public Dialog showPrompt(int layout) {

      Dialog dialog = builder
              .setView(layout)
              .create();

      dialog.setCanceledOnTouchOutside(false);

       dialog.setOnShowListener(new DialogInterface.OnShowListener() {

           @Override
           public void onShow(DialogInterface dialogInterface) {

               Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
               button.setOnClickListener(new View.OnClickListener() {

                   @Override
                   public void onClick(View view) {
                       // TODO Do something

                       //Dismiss once everything is OK.
                       dialog.dismiss();
                   }
               });
           }
       });

       dialog.show();
       return dialog;

   }

    public void showConfirmPopup(String description) {

        Dialog dialog = this.builder
                .setTitle(R.string.title)
                .setMessage(description)
                .setPositiveButton("Ja", null) //Set to null. We override the onclick
                .setNegativeButton("Nee", null)
                .setView(null)
                .create();

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        dialog.setCanceledOnTouchOutside(false);

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {

                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // TODO Do something

                        //Dismiss once everything is OK.
                        dialog.dismiss();
                    }
                });
            }
        });

        dialog.show();

    }

}
