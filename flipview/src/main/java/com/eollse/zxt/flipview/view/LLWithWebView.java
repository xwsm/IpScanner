package com.eollse.zxt.flipview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.eollse.zxt.flipview.R;

public class LLWithWebView extends LinearLayout {
    WebView webview;
    private GestureDetector gestureDetector;
    private boolean isScrolling;

    public LLWithWebView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LLWithWebView(Context context) {
        super(context);
        init(context);
    }

    public LLWithWebView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        gestureDetector = new GestureDetector(this.getContext(),
                onGestureListener);
        View view = LayoutInflater.from(context).inflate(R.layout.llwithwebview, this);
        webview = (WebView) view.findViewById(R.id.webview);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("test ","onTouch onTouch");
                return false;
            }
        });
        //onTouch->scroll
        webview.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 10);
                gestureDetector.onTouchEvent(event);
                Log.e("test ","onTouch isLeftOrRight"+isLeftOrRight);
                if (event.getAction()==MotionEvent.ACTION_MOVE){
                    Log.e("test ","webview onTouch ACTION_MOVE");
                    Log.e("test ","webview onTouch ACTION_MOVE isScrolling"+isScrolling);
                }
                if (isLeftOrRight){
                  return   true;
                }else {
                    return false;
                }

//                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("test ","onTouchEvent onTouchEvent");
        return super.onTouchEvent(event);
    }


    private boolean isLeftOrRight;
    private GestureDetector.OnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {

            float x = e2.getX() - e1.getX();
            float y = e2.getY() - e1.getY();
            Log.e("test","onFling x"+x);
            Log.e("test","onFling y"+y);
            if (x > 216) {
                isLeftOrRight=true;
                // 右滑 事件
                Log.e("test ","右滑动2");
                leftRightListener.right();
            } else if (x < -216) {
                // 左滑事件
                isLeftOrRight=true;
                Log.e("test ","左滑动2");
                leftRightListener.left();
            }else {
                isLeftOrRight=false;
            }

            return true;
        }
    };
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("test ","onInterceptTouchEvent isLeftOrRight"+isLeftOrRight);
        if (isLeftOrRight) {
            return super.onInterceptTouchEvent(ev);
        }else {
            return webview.onInterceptTouchEvent(ev);
        }
    }
    private LeftRightListener leftRightListener;

    public void setLeftRightListener(LeftRightListener leftRightListener) {
        this.leftRightListener = leftRightListener;
    }

    public interface LeftRightListener{
        void left();
        void right();
        void verticalScrolling();
    }
}
