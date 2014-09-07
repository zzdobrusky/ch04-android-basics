package com.badlogic.androidgames.ch04_android_basics;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by zbynek on 9/6/2014.
 */
public class FullScreenTest extends SingleTouchTest
{
    WakeLock wakeLock = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        // make screen fulbright
        Context context = (Context) this;
        PowerManager powerManager = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");

        // make fullscreen window
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(wakeLock != null)
            wakeLock.acquire();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        if(wakeLock != null)
            wakeLock.release();
    }
}
