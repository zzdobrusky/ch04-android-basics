
package com.badlogic.androidgames.ch04_android_basics;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

/**
 *
 * @author zbynek
 */
public class SingleTouchTest extends Activity
                             implements OnTouchListener
{
    StringBuilder builder = new StringBuilder();
    TextView textView;
    
    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        textView = new TextView(this);
        textView.setText("Touch and drag (one finger only)!");
        textView.setOnTouchListener(this);
        setContentView(textView);
    }

    public boolean onTouch(View v, MotionEvent event)
    {
        builder.setLength(0);
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                builder.append("down, ");
                break;
            case MotionEvent.ACTION_MOVE:
                builder.append("cancel, ");
                break;
            case MotionEvent.ACTION_CANCEL:
                builder.append("cancel, ");
                break;
            case MotionEvent.ACTION_UP:
                builder.append("up, ");
                break;            
        }
        builder.append(event.getX());
        builder.append(", ");
        builder.append(event.getY());
        String text = builder.toString();
        Log.d("SingleTouchTest", text);
        textView.setText(text);
        
        return true;
    }
    
}
