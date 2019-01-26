package com.bw.movie.view.filmview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class SearchView extends View {

    Paint mPaint;
    RectF mRectF;

    public SearchView(Context context) {
        super(context);

        initDta();
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initDta();

    }

    private void initDta() {
        mPaint = new Paint();
        mRectF = new RectF();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
