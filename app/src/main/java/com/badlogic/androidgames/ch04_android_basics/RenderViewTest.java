package com.badlogic.androidgames.ch04_android_basics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Random;

/**
 * Created by zbynek on 9/7/2014.
 */
public class RenderViewTest extends Activity
{
    class RenderView extends View
    {
        Random rand = new Random();

        public RenderView(Context context)
        {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);

            canvas.drawRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            invalidate();
        }
    }

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(new RenderView(this));
    }

}
