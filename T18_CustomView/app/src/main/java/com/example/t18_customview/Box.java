package com.example.t18_customview;

import android.graphics.PointF;

public class Box {
    private PointF current;
    private PointF origin;

    public Box(PointF origin) {
        this.origin = origin;
    }

    public PointF getCurrent() {
        return current;
    }

    public void setCurrent(PointF current) {
        this.current = current;
    }

    public PointF getOrigin() {
        return origin;
    }

    public void setOrigin(PointF origin) {
        this.origin = origin;
    }
}
