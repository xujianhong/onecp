package com.daomingedu.onecp.mvp.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.daomingedu.onecp.mvp.model.entity.ConfirmBean;
import com.daomingedu.onecp.mvp.ui.activity.UploadAct;
import com.daomingedu.onecp.mvp.ui.activity.UploadVideoListAct;
import com.daomingedu.onecp.util.MyOkGoUtil;
import com.daomingedu.onecp.util.SharedPreferencesUtil;
import com.google.gson.Gson;
import com.jess.arms.utils.ArmsUtils;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.logging.Logger;

import timber.log.Timber;

/**
 * This class serves as a WebView to be used in conjunction with a VideoEnabledWebChromeClient.
 * It makes possible:
 * - To detect the HTML5 video ended event so that the VideoEnabledWebChromeClient can exit full-screen.
 *
 * Important notes:
 * - Javascript is enabled by default and must not be disabled with getSettings().setJavaScriptEnabled(false).
 * - setWebChromeClient() must be called before any loadData(), loadDataWithBaseURL() or loadUrl() method.
 *
 * @author Cristian Perez (http://cpr.name)
 *
 */

public class VideoEnabledWebView extends WebView {
    public class JavascriptInterface
    {
//        @android.webkit.JavascriptInterface
//        public void h5LoginOut(){
//            ArmsUtils.snackbarText("超时登陆");
//            Timber.e("超时登陆超时登陆超时登陆超时登陆超时登陆超时登陆超时登陆超时登陆");
//        }
        @android.webkit.JavascriptInterface
        public void shootVideo(){
            UploadVideoListAct.Companion.startUploadVideoListActivity((AppCompatActivity) context, 0, 0);
        }

        @android.webkit.JavascriptInterface
        public void notifyVideoEnd() // Must match Javascript interface method of VideoEnabledWebChromeClient
        {
            // This code is not executed in the UI thread, so we must force that to happen
            new Handler(Looper.getMainLooper()).post(new Runnable()
            {
                @Override
                public void run()
                {
                    if (videoEnabledWebChromeClient != null)
                    {
                        videoEnabledWebChromeClient.onHideCustomView();
                    }
                }
            });
        }

        @android.webkit.JavascriptInterface
        public void uploadVideoAppPage(String data) // Must match Javascript interface method of VideoEnabledWebChromeClient
        {
            Log.d("test", "1111");
            try {
                JSONObject jsonObject = new JSONObject(data);
                Log.d("test", new Gson().toJson(jsonObject));
                String url = jsonObject.optString("APIUrl");
                String videoId = jsonObject.optString("videoId");
                int isShowSongList = jsonObject.optInt("isShowSongList");
                int isShowImport = jsonObject.optInt("isShowImport");
                Log.d("test", url);
                Log.d("test", videoId);
                SharedPreferencesUtil.INSTANCE.setIsShowImport(context, isShowImport);
                getConfirmStatus(videoId, url, isShowSongList, isShowImport);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Log.d("test", new Gson().toJson(jsonObject));
            // This code is not executed in the UI thread, so we must force that to happen
        }

    }

    private void getConfirmStatus(String videoId, String url, int isShowSongList, int isShowImport){
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", "715c714249094c5fb8c90a50f92c8bcd");
        httpParams.put("videoId", videoId);
        MyOkGoUtil.INSTANCE.postnew(context, url + "/uploadConfirm.do", httpParams, new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0){
                    ConfirmBean confirmBean = ConfirmBean.Companion.getData((String) msg.obj);
                    if (confirmBean.isConfirm() == 1){
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage(confirmBean.getConfirmMsg());
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();
                    }else if (confirmBean.isConfirm() == 0){
                        UploadAct.Companion.startUploadActivity((AppCompatActivity) context, videoId, url, isShowSongList, isShowImport);
                    }
                }
            }
        });
    }

    private VideoEnabledWebChromeClient videoEnabledWebChromeClient;
    private boolean addedJavascriptInterface;
    private Context context;

    public VideoEnabledWebView(Context context)
    {
        super(context);
        this.context = context;
        addedJavascriptInterface = false;
    }

    @SuppressWarnings("unused")
    public VideoEnabledWebView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        addedJavascriptInterface = false;
    }

    @SuppressWarnings("unused")
    public VideoEnabledWebView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        this.context = context;
        addedJavascriptInterface = false;
    }

    /**
     * Indicates if the video is being displayed using a custom view (typically full-screen)
     * @return true it the video is being displayed using a custom view (typically full-screen)
     */
    public boolean isVideoFullscreen()
    {
        return videoEnabledWebChromeClient != null && videoEnabledWebChromeClient.isVideoFullscreen();
    }

    /**
     * Pass only a VideoEnabledWebChromeClient instance.
     */
    @Override
    @SuppressLint("SetJavaScriptEnabled")
    public void setWebChromeClient(WebChromeClient client)
    {
        getSettings().setJavaScriptEnabled(true);

        if (client instanceof VideoEnabledWebChromeClient)
        {
            this.videoEnabledWebChromeClient = (VideoEnabledWebChromeClient) client;
        }

        super.setWebChromeClient(client);
    }

    @Override
    public void loadData(String data, String mimeType, String encoding)
    {
        addJavascriptInterface();
        super.loadData(data, mimeType, encoding);
    }

    @Override
    public void loadDataWithBaseURL(String baseUrl, String data, String mimeType, String encoding, String historyUrl)
    {
        addJavascriptInterface();
        super.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
    }

    @Override
    public void loadUrl(String url)
    {
        addJavascriptInterface();
        super.loadUrl(url);
    }

    @Override
    public void loadUrl(String url, Map<String, String> additionalHttpHeaders)
    {
        addJavascriptInterface();
        super.loadUrl(url, additionalHttpHeaders);
    }

    private void addJavascriptInterface()
    {
        if (!addedJavascriptInterface)
        {
            // Add javascript interface to be called when the video ends (must be done before page load)
            addJavascriptInterface(new JavascriptInterface(), "_VideoEnabledWebView"); // Must match Javascript interface name of VideoEnabledWebChromeClient

            addedJavascriptInterface = true;
        }
    }
}
