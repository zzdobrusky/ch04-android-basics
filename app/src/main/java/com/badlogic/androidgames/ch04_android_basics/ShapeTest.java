package com.badlogic.androidgames.ch04_android_basics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by zbynek on 9/7/2014.
 */
public class ShapeTest extends Activity
{
    class RenderView extends View
    {
        Paint paint;

        public RenderView(Context context)
        {
            super(context);
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }

        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);

            canvas.drawRGB(255, 255, 0); //255, 255, 255);
            paint.setColor(Color.RED);
            canvas.drawLine(0.0f, 0.0f, canvas.getWidth()-1, canvas.getHeight()-1, paint);

            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(0xFF0000FF);
            canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, 40.0f, paint);

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(0x770000FF);
            canvas.drawRect(100.0f, 100.0f, 200.0f, 200.0f, paint);

            invalidate();
        }
    }

    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new RenderView(this));
    }
}
