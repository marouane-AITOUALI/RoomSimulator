package com.example.virtualhabitat.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceView;
import android.view.View;

public class SelectDoor extends View {


    private SurfaceView surfaceView;
    private Paint paint;
    private Rect rectangle;

    public SelectDoor(Context context, SurfaceView surfaceView, Rect rect) {
        super(context);
        rectangle = rect;
        init();

    }



    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(rectangle, paint);
        surfaceView.getHolder().unlockCanvasAndPost(canvas);


    }
}
