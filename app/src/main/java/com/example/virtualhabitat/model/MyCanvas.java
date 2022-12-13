package com.example.virtualhabitat.model;

import android.content.Context;
import android.graphics.*;
import android.view.SurfaceView;
import android.view.View;

public class MyCanvas extends View {


    private SurfaceView surfaceView;
    private Paint paint;
    private Rect rectangle;

    /**
     * Constructeur de myCanvas
     * @param context Le contexte
     * @param surfaceView La surface view sur laquelle on va dessiner
     * @param rect Le rectangle à dessiner
     */
    public MyCanvas(Context context, SurfaceView surfaceView, Rect rect) {
        super(context);
        rectangle = rect;
        this.surfaceView = surfaceView;
        initPaint();

    }


    /**
     * Initialisation de paint et de ses attributs
     */
    private void initPaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(4);
        paint.setColor(Color.BLUE);
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
