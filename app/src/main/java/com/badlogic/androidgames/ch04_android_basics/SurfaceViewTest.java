package com.badlogic.androidgames.ch04_android_basics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by zbynek on 9/23/2014.
 */
public class SurfaceViewTest extends Activity
{
    FastRenderView _renderView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        _renderView = new FastRenderView(this);
        setContentView(_renderView);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        _renderView.resume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        _renderView.pause();
    }

    private class FastRenderView extends SurfaceView implements Runnable
    {
        Thread _renderThread = null;
        SurfaceHolder _holder;
        volatile boolean running = false;

        public FastRenderView(Context context)
        {
            super(context);
            _holder = getHolder();
        }

        @Override
        public void run()
        {
            while(running)
            {
                if(!_holder.getSurface().isValid())
                    continue;

                Canvas canvas = _holder.lockCanvas();
                canvas.drawRGB(255, 0, 0);
                _holder.unlockCanvasAndPost(canvas);
            }
        }

        public void resume()
        {
            running = true;
            _renderThread = new Thread(this);
            _renderThread.start();
        }

        public void pause()
        {
            running = false;
            while (true)
            {
                try
                {
                    _renderThread.join();
                    return;
                }
                catch (InterruptedException e)
                {
                    // retry
                }
            }
        }
    }
}
