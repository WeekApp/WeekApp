package com.bw.movie.bean.cinemabean;

import android.content.Context;
import android.widget.Toast;

/**
 * date:2019.1.25
 * author:赵颖冰(lenovo)
 * function:
 */
public class ToastUtil {
    private static Toast toast;
    public static Toast showShort(Context context, CharSequence message) {
        if (null == toast) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();

        return toast;
    }
}
