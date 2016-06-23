package org.buildmlearn.mconference.conference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.google.android.gms.maps.MapView;

/**
 * Created by jai on 22/6/16.
 */

public class VenueMapView extends MapView {

    public VenueMapView(Context context) {
        super(context);
    }

    public VenueMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VenueMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                this.getParent().requestDisallowInterceptTouchEvent(true);
                break;

            case MotionEvent.ACTION_UP:
                this.getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }

        super.dispatchTouchEvent(ev);
        return true;
    }
}
