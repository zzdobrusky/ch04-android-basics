package com.badlogic.androidgames.ch04_android_basics;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zbynek on 9/5/2014.
 */
public class AssetsTest extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        setContentView(textView);

        AssetManager assetManager = getAssets();
        InputStream inputStream = null;
        try
        {
            inputStream = assetManager.open("texts/myawesometext.txt");
            String text = loadTextFile(inputStream);
            textView.setText(text);
        }
        catch (IOException ioe)
        {
            textView.setText("Couldn't load file");
        }
        finally
        {
            if (inputStream != null)
            {
                try
                {
                    inputStream.close();
                }
                catch (IOException ioe)
                {
                    textView.setText("Couldn't close file");
                }
            }
        }
    }

    public String loadTextFile(InputStream inputStream) throws IOException
    {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[4096];
        int len = 0;
        while((len = inputStream.read(bytes)) > 0)
            byteStream.write(bytes, 0, len);

        return new String(byteStream.toByteArray(), "UTF8");
    }


}
