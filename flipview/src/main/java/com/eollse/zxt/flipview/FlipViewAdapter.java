package com.eollse.zxt.flipview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.eollse.zxt.flipview.bean.FamilyInfo;
import com.eollse.zxt.flipview.flipview.FlipViewController;
import com.eollse.zxt.flipview.view.LLWithWebView;


import java.util.List;

/**
 * Created by think on 2017/6/2.
 */

public class FlipViewAdapter extends BaseAdapter {

    private Context mContext;
    private Context appmContext;
    private List<List<FamilyInfo>> familyInfos;


    public FlipViewAdapter(Context mContext, Context appmContext, List<List<FamilyInfo>> familyInfos) {
        this.mContext = mContext;
        this.familyInfos = familyInfos;
        this.appmContext = appmContext;
        Log.i("Page", familyInfos.size() + "SSS");
    }

    @Override
    public int getCount() {
        return familyInfos == null ? 0 : familyInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return familyInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.flipview_gridview_family, null);
            viewHolder.lin_parent = convertView.findViewById(R.id.lin_parent);
            viewHolder.web_parent = (LLWithWebView)convertView.findViewById(R.id.web_parent);
            viewHolder.lefttxt = (TextView) convertView.findViewById(R.id.grid_view_left);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.grid_view_right);
            viewHolder.wvb = (WebView) convertView.findViewById(R.id.webview);

            final View finalConvertView = convertView;
            viewHolder.web_parent.setLeftRightListener(new LLWithWebView.LeftRightListener() {
                @Override
                public void left() {
                    Log.e("test ","setLeftRightListener left");
                    useFlipViewController(true);

                }

                @Override
                public void right() {
                    Log.e("test ","setLeftRightListener right");
                    useFlipViewController(true);
                }

                @Override
                public void verticalScrolling() {
//                    useFlipViewController(false);
                }
            });

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        List<FamilyInfo> familyInfosLeft;
        familyInfosLeft = familyInfos.get(position).subList(0, 1);
        //viewHolder.lefttxt.setText(familyInfosLeft.get(0).name);
        viewHolder.wvb.loadUrl(familyInfosLeft.get(0).name);
        //支持javascript
        viewHolder.wvb.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        viewHolder.wvb.getSettings().setSupportZoom(false);
        // 设置出现缩放工具
        viewHolder.wvb.getSettings().setBuiltInZoomControls(false);
        //扩大比例的缩放
        viewHolder.wvb.getSettings().setUseWideViewPort(true);
        //自适应屏幕

        viewHolder.wvb.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        viewHolder.wvb.getSettings().setLoadWithOverviewMode(true);
        viewHolder.wvb.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        viewHolder.wvb.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                //view.loadUrl("about:blank"); // 避免出现默认的错误界面
                //view.loadUrl("file:///android_asset/err.html");
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                // 断网或者网络连接超时
                //view.loadUrl("about:blank"); // 避免出现默认的错误界面
                //view.loadUrl("file:///android_asset/err.html");
            }


        });


        Glide.with(appmContext)
                .load(familyInfosLeft.get(0).header).priority(Priority.HIGH)
                .into(viewHolder.img);



        Log.i("LA", familyInfosLeft.get(0).header + "---");
        return convertView;
    }

    int count = 0;

    static class ViewHolder {

        TextView lefttxt;
        View lin_parent;
        LLWithWebView web_parent;
        ImageView img;
        //        WebView wvb;
        WebView wvb;

    }

    private FlipViewController fview;

    public void setView_FlipViewController(FlipViewController view) {
        fview = view;
    }

    private void useFlipViewController(boolean use) {
        if (fview != null) {
            fview.setFlipByTouchEnabled(use);
        }
    }

    private void dispatchTouchEvent(MotionEvent ev) {
        if (fview != null) {
            fview.dispatchTouchEvent(ev);
        }
    }
}
