package com.daomingedu.onecp.mvp.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.*
import com.daomingedu.onecp.R
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.Preference
import com.daomingedu.onecp.di.component.DaggerQuestionComponent
import com.daomingedu.onecp.di.module.QuestionModule
import com.daomingedu.onecp.mvp.contract.QuestionContract
import com.daomingedu.onecp.mvp.model.api.Api
import com.daomingedu.onecp.mvp.presenter.QuestionPresenter
import com.daomingedu.onecp.mvp.ui.activity.LoginAct
import com.daomingedu.onecp.mvp.ui.activity.MainAct
import com.daomingedu.onecp.mvp.ui.widget.VideoEnabledWebChromeClient
import com.daomingedu.onecp.util.AndroidBug5497Workaround
import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.fragment_question.*
import timber.log.Timber

/**
 * Description
 * Created by jianhongxu on 2021/10/13
 */
class QuestionFragment : BaseFragment<QuestionPresenter>(), QuestionContract.View {

    lateinit var webChromeClient: VideoEnabledWebChromeClient

    val mSessionId by Preference(Constant.SESSIONID, "")

    companion object {
        fun newInstance(): QuestionFragment {
            return QuestionFragment()
        }
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerQuestionComponent.builder().appComponent(appComponent)
            .questionModule(QuestionModule(this))
            .build()
            .inject(this)
    }

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }


    @SuppressLint("SetJavaScriptEnabled","JavascriptInterface")
    override fun initData(savedInstanceState: Bundle?) {

        //        View loadingView = getLayoutInflater().inflate(R.layout.view_loading_video, null); // Your own view, read class comments
        webChromeClient =
            object : VideoEnabledWebChromeClient(nonVideoLayout, videoLayout, null, wv_web) {
                override fun onProgressChanged(view: WebView, newProgress: Int) {
                    Timber.d("newProgress:$newProgress")
                    pb_load.setProgress(newProgress)
                }

                override fun onPermissionRequest(request: PermissionRequest) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        request.grant(request.resources)
                    }
                }

                // For Android < 3.0
                fun openFileChooser(valueCallback: ValueCallback<Uri?>) {
//                    uploadMessage = valueCallback
//                    openImageChooserActivity(null)
                }

                // For Android  >= 3.0
                fun openFileChooser(valueCallback: ValueCallback<*>, acceptType: String?) {
//                    uploadMessage = valueCallback
//                    openImageChooserActivity(acceptType)
                }

                //For Android  >= 4.1
                fun openFileChooser(
                    valueCallback: ValueCallback<Uri?>,
                    acceptType: String?,
                    capture: String?
                ) {
//                    uploadMessage = valueCallback
//                    openImageChooserActivity(acceptType)
                }

                override fun onShowFileChooser(
                    webView: WebView,
                    filePathCallback: ValueCallback<Array<Uri>>,
                    fileChooserParams: FileChooserParams
                ): Boolean {
                    var acceptType: String? = null
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        val acceptTypes = fileChooserParams.acceptTypes
                        if (acceptTypes != null && acceptTypes.size > 0) {
                            acceptType = acceptTypes[0]
                        }
                    }
//                    uploadMessageAboveL = filePathCallback
//                    openImageChooserActivity(acceptType)
                    return true
                }
            }
        webChromeClient.setOnToggledFullscreen { fullscreen ->
            // Your code to handle the full-screen change, for example showing and hiding the title bar. Example:
            if (fullscreen) {
                if (getMActivity().requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    getMActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                }
                val attrs: WindowManager.LayoutParams = getMActivity().window.attributes
                attrs.flags = attrs.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN
                attrs.flags = attrs.flags or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                getMActivity().window.attributes = attrs
                getMActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE

                //                    toolbar.setVisibility(View.GONE);
            } else {
                if (getMActivity().requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                    getMActivity().requestedOrientation =
                        ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                }
                val attrs: WindowManager.LayoutParams = getMActivity().window.attributes
                attrs.flags = attrs.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN.inv()
                attrs.flags = attrs.flags and WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON.inv()
                getMActivity().window.attributes = attrs
                getMActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE

                //                    toolbar.setVisibility(View.VISIBLE);
            }
        }
        wv_web.webChromeClient = webChromeClient

        val setting = wv_web.settings
        setting.useWideViewPort = true //????????????????????????Webview??????
        setting.setSupportZoom(true) //????????????
        setting.loadWithOverviewMode = true //?????????????????????
        setting.loadsImagesAutomatically = true //????????????????????????

        // ??????JS

        // ??????JS
        setting.javaScriptEnabled = true //??????js

        //????????????
        setting.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING

        //https??????????????????http??????????????????????????????
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setting.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }


        //WebView??????web??????
        wv_web.loadUrl(Api.APP_DOMAIN + "/questionBank/index.do?sessionId=" + mSessionId)
//        wv_web.loadUrl(Api.APP_DOMAIN+"/questionBank/index.do?sessionId=1b7943e2ad5949fca8135b01e5867ea6")

//??????WebView?????????????????????????????????????????????????????????????????????????????????WebView??????
        wv_web.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                Timber.e(request.toString())
                return super.shouldOverrideUrlLoading(view,request)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pb_load.visibility = View.GONE
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                val cookieManager = CookieManager.getInstance()
                val s = cookieManager.getCookie(url)
                Timber.d("Cookies==$s")
                pb_load.visibility = View.VISIBLE
            }
        }
        //??????js ??? window._VideoEnabledWebView.refreshStudentInfo(); ??????Android

        //??????js ??? window._VideoEnabledWebView.refreshStudentInfo(); ??????Android
        wv_web.addJavascriptInterface(this, "_VideoEnabledWebView")
    }


    //??????h5?????????????????????h5LoginOut????????????h5???????????????????????????
    @JavascriptInterface
    fun h5LogOut() {
        Timber.e("????????????????????????????????????????????????????????????????????????????????????????????????")
        val intent = Intent(getMActivity(), LoginAct::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NO_HISTORY
        getMActivity().startActivity(intent)
//        startActivity<LoginAct>()
//        killMyself()
    }

    //??????h5?????????????????????h5QuitOut????????????h5????????????????????????
    @JavascriptInterface
    fun h5QuitOut(){
        (getMActivity()as MainAct).onTabSelected(0)
    }


    override fun onDestroy() {
        super.onDestroy()
        if (wv_web != null) {
            wv_web.clearCache(true)
        }
    }

    override fun setData(data: Any?) {

    }

    override fun showDialogLoading() {
    }

    override fun dismissDialogLoading() {

    }

    override fun getMActivity() = activity!!

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }
}