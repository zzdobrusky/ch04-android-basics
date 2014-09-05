package com.badlogic.androidgames.ch04_android_basics;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class AndroidBasicsStarter extends ListActivity
{
    String tests[] = { "LifeCycleTest", "SingleTouchTest", "MultiTouchTest",
            "KeyTest", "AccelerometerTest", "AccelerometerAxisRotationTest", "CompassTest",
            "AssetsTest", "ExternalStorageTest", "SoundPoolTest", "MediaPlayerTest",
            "FullScreenTest", "RenderViewTest", "ShapeTest", "BitmapTest",
            "FontTest", "SurfaceViewTest" };

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, tests));
    }

    @Override
    public void onListItemClick(ListView list, View view, int position, long id)
    {
        super.onListItemClick(list, view, position, id);
        String testName = tests[position];
        try
        {
            Class clazz = Class.forName("com.badlogic.androidgames.ch04_android_basics." + testName);
            Intent intent = new Intent(this, clazz);
            startActivity(intent);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
