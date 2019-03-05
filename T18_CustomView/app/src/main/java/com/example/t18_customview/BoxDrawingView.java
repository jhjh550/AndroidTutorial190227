package com.example.t18_customview;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class BoxDrawingView extends View {
    private static final String TAG = "BoxDrawingView";
    // code
    public BoxDrawingView(Context context) {
        super(context);
    }

    // xml
    public BoxDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        Log.d(TAG, "action : "+event.getAction() +
                "x : "+current.x + "y : "+current.y);

        return true;
    }
}
