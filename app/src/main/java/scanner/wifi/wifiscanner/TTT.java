package scanner.wifi.wifiscanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.eollse.zxt.flipview.FlipViewAdapter;
import com.eollse.zxt.flipview.flipview.FlipViewController;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 2019 重庆指讯科技股份有限公司
 *
 * @author: Wsm
 * @date: 2019/5/30 11:58
 * @description:
 */
public class TTT extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttt);
        ButterKnife.bind(this);
        initFlipView();
    }
    void initFlipView(){
        List<com.eollse.zxt.flipview.bean.FamilyInfo> tl = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            com.eollse.zxt.flipview.bean.FamilyInfo familyInfo1 = new com.eollse.zxt.flipview.bean.FamilyInfo();
            familyInfo1.header = "http://pic26.nipic.com/20130116/1773545_152734135000_2.jpg";
            familyInfo1.name ="http://mfdog.cn";
            tl.add(familyInfo1);
        }
        setFlipViewAdapter(tl);
    }
    @BindView(R.id.ff)
    FlipViewController flipView;
    Animation shakeAnimation;
    //总页数
    private int totalPage = 0;
    List<List<com.eollse.zxt.flipview.bean.FamilyInfo>> family=null;
    private List<com.eollse.zxt.flipview.bean.FamilyInfo> familyInfos = new ArrayList<>();
    private FlipViewAdapter adapter;

    public void setFlipViewAdapter( List<com.eollse.zxt.flipview.bean.FamilyInfo> tl){
        shakeAnimation = AnimationUtils.loadAnimation(TTT.this,R.anim.shake);
        //加载动画资源文件
        //总页数页数
        familyInfos = tl;
        totalPage = familyInfos.size();
        family = new ArrayList<>();
        for (int i = 0; i < totalPage; i++) {
            List newlist = null;
            newlist = familyInfos.subList(i, i + 1);
            family.add(newlist);
        }
        adapter = new FlipViewAdapter(this,getApplicationContext(), family);
        adapter.setView_FlipViewController(flipView);
        flipView.setAdapter(adapter);

    }
}
