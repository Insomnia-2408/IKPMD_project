package com.hsleiden.ikpmd_project.Helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;

import com.hsleiden.ikpmd_project.R;

public class PopupHelper {

    private Object service;
    private AlertDialog.Builder builder;
    private Activity activity;

   public PopupHelper(Object service, Activity activity) {
        this.service = service;
        this.builder = new AlertDialog.Builder(activity);
        this.activity = activity;
   }

   @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
   public Dialog showPrompt(int layout) {

      final Dialog dialog = builder
              .setView(layout)
              .create();

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

        final Dialog dialog = this.builder
                .setTitle(R.string.title)
                .setMessage(description)
                .setPositiveButton(android.R.string.ok, null) //Set to null. We override the onclick
                .setNegativeButton(android.R.string.cancel, null)
                .create();

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
