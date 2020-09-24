package com.example.titaniwmbluetoothcontrol;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.constraintlayout.widget.ConstraintLayout;


public class MyViewGroup {

    // For more information, see https://issuetracker.google.com/issues/36911528
    // To use this class, simply invoke assistActivity() on an Activity that already has its content view set.

    public static void assistActivity (TelaNovoLayoutsPersonalizados activity) {
        new MyViewGroup(activity);
    }

    private View mChildOfContent;
    private int usableHeightPrevious;
    private ConstraintLayout.LayoutParams constraintLayoutParams;

    public MyViewGroup(TelaNovoLayoutsPersonalizados activity) {
        ConstraintLayout content = (ConstraintLayout) activity.findViewById(R.id.tela_layouts_personalizados);
        mChildOfContent = content.getChildAt(0);
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                possiblyResizeChildOfContent();
            }
        });
        constraintLayoutParams = (ConstraintLayout.LayoutParams) mChildOfContent.getLayoutParams();
    }

    private void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;
            if (heightDifference > (usableHeightSansKeyboard/4)) {
                // keyboard probably just became visible
                constraintLayoutParams.height = usableHeightSansKeyboard - heightDifference;
            } else {
                // keyboard probably just became hidden
                constraintLayoutParams.height = usableHeightSansKeyboard;
            }
            mChildOfContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight() {
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        return (r.bottom);
    }
}


