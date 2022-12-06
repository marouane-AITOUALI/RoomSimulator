package com.example.virtualhabitat.model;

import android.content.Context;
import android.graphics.*;
import android.view.SurfaceView;
import android.view.View;

public class myCanvas extends View {


    private SurfaceView surfaceView;
    private Paint paint;
    private Rect rectangle;

    public myCanvas(Context context, SurfaceView surfaceView, Rect rect) {
        super(context);
        rectangle = rect;
        this.surfaceView = surfaceView;
        init();

    }



    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas = surfaceView.getHolder().lockCanvas();
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawRect(rectangle, paint);
        surfaceView.getHolder().unlockCanvasAndPost(canvas);
    }


}
