package com.fengtou.acckale;

import android.accessibilityservice.AccessibilityService;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class AcckaleService extends AccessibilityService {

    private static final String TAG = "AcckaleService";

    public AcckaleService() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.i(TAG, "onAccessibilityEvent " + event.toString() + "////" + event.getContentChangeTypes());
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        if (rootNode == null) {
            Log.i(TAG, "rootNode is　null");
            return;
        } else {
           recycle(rootNode);
        }
    }

    @Override
    protected boolean onGesture(int gestureId) {
        Log.i(TAG, "onGesture " + gestureId);
        return super.onGesture(gestureId);
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        Log.i(TAG, "onKeyEvent " + event.toString());
        return super.onKeyEvent(event);
    }

    public void recycle(AccessibilityNodeInfo info) {
        if (info.getChildCount() == 0) {
            /*
            if (info.getText() != null && info.getText().equals("华为工作法")) {
                Log.i(TAG, "child widget----------------------------" + info.getClassName());
                Log.i(TAG, "isClickable:" + info.isClickable());
                Log.i(TAG, "Text：" + info.getText());
                Log.i(TAG, "windowId:" + info.getWindowId());
                Log.i(TAG, "windowId:" + info.getWindowId());
                AccessibilityNodeInfo parent_info = null;
                if (!info.isClickable())  parent_info = info;
                if (!parent_info.isClickable()) {
                    parent_info = parent_info.getParent();
                }
                if (parent_info != null) {
                    Log.i(TAG, "点击了华为工作法！！！！");
                    parent_info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }
            */
            Log.i(TAG, info.hashCode() + " recycleinfo = " + info.getContentDescription());
        } else {
            for (int i = 0; i < info.getChildCount(); i++) {
               // if (info.getContentDescription() != null) {
                 //   Log.i(TAG, "recycleinfo = " + info.getContentDescription());
                //}

                if(info.getChild(i)!=null){
                    recycle(info.getChild(i));
                }
            }
        }
    }

    @Override
    public void onInterrupt() {

    }
}
