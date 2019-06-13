//package com.eollse.zxt.flipview;
//
//import android.os.Bundle;
//import android.os.SystemClock;
//import android.support.v7.app.AppCompatActivity;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.ImageButton;
//import com.eollse.zxt.flipview.bean.FamilyInfo;
//import java.util.ArrayList;
//import java.util.List;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
//public class MainActivity extends AppCompatActivity {
//
//    @BindView(R.id.flip_view)
//    com.eollse.zxt.flipview.flipview.FlipViewController flipView;
//    @BindView(R.id.left_page)
//    ImageButton leftPage;
//    @BindView(R.id.right_page)
//    ImageButton rightPage;
//
//
//    private FlipViewAdapter adapter;
//
//    private List<FamilyInfo> familyInfos = new ArrayList<>();
//
//    //总页数
//    private int totalPage = 0;
//
//    private Animation shakeAnimation;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.flipview_activity_main);
//        ButterKnife.bind(this);
//        initWidget();
//    }
//
//    private void initWidget() {
//        shakeAnimation = AnimationUtils.loadAnimation(this, shake);//加载动画资源文件
//        for (int i = 0; i < 17; i++) {
//            FamilyInfo familyInfo = new FamilyInfo();
//            familyInfo.header = "http://upload.jianshu.io/collections/images/429893/timg.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240";
//
//            familyInfo.name = "item" + i;
//            familyInfos.add(familyInfo);
//        }
//        //总页数页数
//        totalPage=familyInfos.size();
//        List<List<FamilyInfo>> family = new ArrayList<>();
//        for (int i = 0; i < totalPage; i++) {
//            List newlist = null;
//            newlist = familyInfos.subList(i,i + 1);
//            family.add(newlist);
//        }
//        adapter = new FlipViewAdapter(this, getApplicationContext(), family);
//        flipView.setAdapter(adapter);
//
//*
//         * 监听翻页事件
//         * 第一页时隐藏左边按钮，最后一页时隐藏右边按钮
//         * 当抖动动画正在进行时关闭动画
//
//
//*
//        flipView.setOnViewFlipListener((view, position) -> {
//            if (position == 0) {
//               // leftPage.setVisibility(View.INVISIBLE);
//                //rightPage.setVisibility(View.VISIBLE);
//            } else if (position == totalPage - 1) {
//               // leftPage.setVisibility(View.VISIBLE);
//               // rightPage.setVisibility(View.INVISIBLE);
//            } else {
//              //  leftPage.setVisibility(View.VISIBLE);
//               // rightPage.setVisibility(View.VISIBLE);
//            }
//
//
//        });
//*
//
//    }
//
//    //按钮的翻页监听，这里我们不能直接调用flipView.setSelection()方法，因为这样就没有翻页的动画了，我们就在这里dispatchTouchEvent模拟滑动view
//    @OnClick({R.id.left_page, R.id.right_page})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.left_page:
//                flipView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
//                        SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, flipView.getLeft(), flipView.getTop(), 0));
//                flipView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
//                        SystemClock.uptimeMillis(), MotionEvent.ACTION_MOVE, flipView.getLeft() + 100, flipView.getTop(), 0));
//                flipView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
//                        SystemClock.uptimeMillis(), MotionEvent.ACTION_MOVE, flipView.getLeft() + 200, flipView.getTop(), 0));
//                flipView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
//                        SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, flipView.getLeft() + 200, flipView.getTop(), 0));
//                break;
//            case R.id.right_page:
//                flipView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
//                        SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, flipView.getLeft(), flipView.getTop(), 0));
//                flipView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
//                        SystemClock.uptimeMillis(), MotionEvent.ACTION_MOVE, flipView.getLeft() - 100, flipView.getTop(), 0));
//                flipView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
//                        SystemClock.uptimeMillis(), MotionEvent.ACTION_MOVE, flipView.getLeft() - 200, flipView.getTop(), 0));
//                flipView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
//                        SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, flipView.getLeft() - 200, flipView.getTop(), 0));
//                break;
//        }
//    }
//}
