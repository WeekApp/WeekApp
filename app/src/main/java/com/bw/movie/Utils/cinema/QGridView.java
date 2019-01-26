package com.bw.movie.Utils.cinema;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * date:2019.1.25
 * author:赵颖冰(lenovo)
 * function:
 */
public class QGridView extends GridView {
    public QGridView(Context context) {
        super(context);
    }

    public QGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}