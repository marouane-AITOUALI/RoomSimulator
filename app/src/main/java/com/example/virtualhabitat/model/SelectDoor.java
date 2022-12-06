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
    private Paint p;
    private Rect rectangle;

    public SelectDoor(Context context, SurfaceView surfaceView, Rect rect) {
        super(context);
        rectangle = rect;
        init();

    }



    private void init() {
        p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setAntiAlias(true);
        p.setStrokeWidth(10);
        p.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        surfaceView.getHolder().unlockCanvasAndPost(canvas);


    }
}
