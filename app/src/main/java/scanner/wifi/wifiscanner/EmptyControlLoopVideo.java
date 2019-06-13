package scanner.wifi.wifiscanner;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.shuyu.gsyvideoplayer.GSYVideoBaseManager;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 无任何控制ui的播放
 * Created by guoshuyu on 2017/8/6.
 */

public class EmptyControlLoopVideo extends StandardGSYVideoPlayer {

    public EmptyControlLoopVideo(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public EmptyControlLoopVideo(Context context) {
        super(context);
    }

    public EmptyControlLoopVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.empty_control_video;
    }

    @Override
    protected void touchSurfaceMoveFullLogic(float absDeltaX, float absDeltaY) {
        super.touchSurfaceMoveFullLogic(absDeltaX, absDeltaY);
        //不给触摸快进，如果需要，屏蔽下方代码即可
        mChangePosition = false;
        //不给触摸音量，如果需要，屏蔽下方代码即可
        mChangeVolume = false;
        //不给触摸亮度，如果需要，屏蔽下方代码即可
        mBrightness = false;
    }

    @Override
    protected void touchDoubleUp() {
        //super.touchDoubleUp();
        //不需要双击暂停
    }
    List<String> dataUrls=new ArrayList<>();
    int position=0;
    public void setUps(List<String> urls){
        dataUrls.addAll(urls);
        setUp(dataUrls.get(position),true,"视频:"+position);
        position++;
    }

    @Override
    public boolean setUp(String url, boolean cacheWithPlay, String title) {
        return super.setUp(url, cacheWithPlay, title);
    }

    @Override
    public void onAutoCompletion() {
        //循环播放
        //super.onAutoCompletion();
        Log.i("XXX","onAutoCompletion"+position);
        setUp(dataUrls.get(position), true, "视频:"+position);
        startPlayLogic();
        position++;
    }
}