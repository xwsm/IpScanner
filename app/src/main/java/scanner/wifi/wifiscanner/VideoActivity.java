package scanner.wifi.wifiscanner;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.cache.CacheFactory;
import com.shuyu.gsyvideoplayer.cache.ProxyCacheManager;
import com.shuyu.gsyvideoplayer.player.IjkPlayerManager;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.player.SystemPlayerManager;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

import tv.danmaku.ijk.media.exo2.Exo2PlayerManager;
import tv.danmaku.ijk.media.exo2.ExoPlayerCacheManager;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 2019 重庆指讯科技股份有限公司
 *
 * @author: Wsm
 * @date: 2019/6/13 15:39
 * @description:
 */
public class VideoActivity extends AppCompatActivity {
    EmptyControlLoopVideo videoPlayer;
    OrientationUtils orientationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity);
        videoPlayer = findViewById(R.id.video);
        String source1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
        String source2 = "http://www-1251738822.cos.ap-chengdu.myqcloud.com/csxc.mp4";
        List<String> urls=new ArrayList<>();
        urls.add(source2);
        urls.add(source1);

        videoPlayer.setUps(urls);
        //设置旋转
        orientationUtils = new OrientationUtils(this, videoPlayer);

        //是否可以滑动调整
//        videoPlayer.setIsTouchWiget(true);
//        //设置返回按键功能
//        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
        videoPlayer.startPlayLogic();

        //EXOPlayer内核，支持格式更多
        PlayerFactory.setPlayManager(Exo2PlayerManager.class);
        //系统内核模式
        PlayerFactory.setPlayManager(SystemPlayerManager.class);
        //ijk内核，默认模式
        PlayerFactory.setPlayManager(IjkPlayerManager.class);


        //exo缓存模式，支持m3u8，只支持exo
        CacheFactory.setCacheManager(ExoPlayerCacheManager.class);
        //代理缓存模式，支持所有模式，不支持m3u8等，默认
        CacheFactory.setCacheManager(ProxyCacheManager.class);


//切换渲染模式
        GSYVideoType.setShowType(GSYVideoType.SCREEN_MATCH_FULL);
        //默认显示比例
        //GSYVideoType.SCREEN_TYPE_DEFAULT = 0;
        //16:9
        //GSYVideoType.SCREEN_TYPE_16_9 = 1;
        //4:3
//        GSYVideoType.SCREEN_TYPE_4_3 = 2;
        //全屏裁减显示，为了显示正常 CoverImageView 建议使用FrameLayout作为父布局
//        GSYVideoType.SCREEN_TYPE_FULL = 4;
        //全屏拉伸显示，使用这个属性时，surface_container建议使用FrameLayout
//        GSYVideoType.SCREEN_MATCH_FULL = -4;


        //切换绘制模式
        GSYVideoType.setRenderType(GSYVideoType.SUFRACE);
        GSYVideoType.setRenderType(GSYVideoType.GLSURFACE);
        GSYVideoType.setRenderType(GSYVideoType.TEXTURE);


        //ijk关闭log
        IjkPlayerManager.setLogLevel(IjkMediaPlayer.IJK_LOG_SILENT);

    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        videoPlayer.onVideoPause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        videoPlayer.onVideoResume();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        GSYVideoManager.releaseAllVideos();
//        if (orientationUtils != null)
//            orientationUtils.releaseListener();
//    }
//
//    @Override
//    public void onBackPressed() {
//        //先返回正常状态
//        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
//            videoPlayer.getFullscreenButton().performClick();
//            return;
//        }
//        //释放所有
//        videoPlayer.setVideoAllCallBack(null);
//        super.onBackPressed();
//    }


}
