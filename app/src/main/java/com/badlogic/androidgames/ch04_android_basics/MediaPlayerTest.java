package com.badlogic.androidgames.ch04_android_basics;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

/**
 * Created by zbynek on 9/6/2014.
 */
public class MediaPlayerTest extends Activity
{
    MediaPlayer mediaPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        setContentView(textView);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mediaPlayer = new MediaPlayer();
        try
        {
            AssetManager assetManager = getAssets();
            AssetFileDescriptor descriptor = assetManager.openFd("audio" + File.separator + "FindYou.ogg");
            mediaPlayer.setDataSource(
                    descriptor.getFileDescriptor(),
                    descriptor.getStartOffset(),
                    descriptor.getLength());
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
        }
        catch (IOException ioe)
        {
            textView.setText("Error: " + ioe.getMessage());
            mediaPlayer = null;
        }
    }

    protected void onResume()
    {
        super.onResume();
        if (mediaPlayer != null)
            mediaPlayer.start();
    }

    protected void onPause()
    {
        super.onPause();
        if (mediaPlayer != null)
        {
            mediaPlayer.pause();
            if (isFinishing())
            {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }
    }
}
