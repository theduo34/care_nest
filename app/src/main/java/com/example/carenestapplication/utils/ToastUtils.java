package com.example.carenestapplication.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.carenestapplication.R;

public class ToastUtils {

    public static void success(Context context, String message) {
        showToast(context, message, R.color.success_green);
    }

    public static void warning(Context context, String message) {
        showToast(context, message, R.color.warning_yellow);
    }
    public static void error(Context context, String message) {
        showToast(context, message, R.color.error_red);
    }

    private static void showToast(Context context, String message, int bgColor) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.custom_toast, null);

        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(message);

        layout.setBackgroundColor(bgColor);

        int paddingY = (int) (8 * context.getResources().getDisplayMetrics().density);
        int paddingX = (int) (16 * context.getResources().getDisplayMetrics().density);
        layout.setPadding(paddingX, paddingY, paddingX, paddingY);

        android.widget.Toast toast = new android.widget.Toast(context);
        toast.setDuration(android.widget.Toast.LENGTH_SHORT);
        toast.setView(layout);

        int xOffset = 50;
        int yOffset = 100;
        toast.setGravity(android.view.Gravity.TOP | android.view.Gravity.END, xOffset, yOffset);

        toast.show();
    }

}
