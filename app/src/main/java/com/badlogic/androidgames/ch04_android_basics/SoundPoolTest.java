package com.badlogic.androidgames.ch04_android_basics;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

/**
 * Created by zbynek on 9/6/2014.
 */
public class SoundPoolTest extends Activity implements OnTouchListener
{
    SoundPool soundPool = null;
    int explosionID = -1;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setOnTouchListener(this);
        setContentView(textView);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 1);

        try
        {
            AssetManager assetManager = getAssets();
            AssetFileDescriptor descriptor = assetManager.openFd("audio" + File.separator + "explosion.ogg");
            explosionID = soundPool.load(descriptor, 1);
        }
        catch (IOException ioe)
        {
            textView.setText("Error: " + ioe.getMessage());
        }

    }

    // for some reason doesn't resume
    protected void onResume()
    {
        super.onResume();
        if (soundPool != null)
        {
            soundPool.resume(explosionID);
        }

    }
    // for some reason doesn't pause
    protected void onPause()
    {
        super.onPause();
        if (soundPool != null)
        {
            soundPool.pause(explosionID);
            if (isFinishing())
            {
                soundPool.stop(explosionID);
                soundPool.release();
            }
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP)
        {
            if (explosionID != -1)
                soundPool.play(explosionID, 1, 1, 0, 0, 1);
        }

        return true;
    }
}
