package com.daomingedu.onecp.mvp.presenter

import android.app.Application
import com.daomingedu.art.mvp.model.entity.RegisterBean
import com.daomingedu.onecp.BuildConfig
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.Preference
import com.daomingedu.onecp.mvp.contract.LoginContract
import com.daomingedu.onecp.mvp.model.api.Api
import com.daomingedu.onecp.mvp.model.entity.AboutUsBean
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.util.RxUtils
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.utils.RxLifecycleUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import me.jessyan.rxerrorhandler.handler.RetryWithDelay
import javax.inject.Inject

@ActivityScope
class LoginPresenter
@Inject
constructor(model: LoginContract.Model,rootView: LoginContract.View):
    BasePresenter<LoginContract.Model, LoginContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

    private var sessionId by Preference(Constant.SESSIONID,"")
    override fun onDestroy() {
        super.onDestroy();
    }

    fun login(mobile:String,password:String){
        mModel.login(Constant.KEY,mobile,password,1,null,null, BuildConfig.VERSION_CODE)
            .retryWhen(RetryWithDelay(2, 5))
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseJson<RegisterBean>>(mErrorHandler) {
                override fun onNext(t: BaseJson<RegisterBean>) {
                    if (Api.SUCCESS == t.code) {
                        sessionId = t.data?.sessionId?:""
                        mRootView.requestLoginSuccess()
                    }else{
                        mRootView.showMessage(t.msg)
                    }
                }

                override fun onError(t: Throwable) {
                    super.onError(t)
                    mRootView.showMessage(t.toString())
                }
            })
    }


    fun getProtocol(type:Int){
        mModel.getProtocol(Constant.KEY)
            .retryWhen(RetryWithDelay(2, 5))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseJson<AboutUsBean>>(mErrorHandler) {
                override fun onNext(t: BaseJson<AboutUsBean>) {
                    if (Api.SUCCESS == t.code) {
                        mRootView.requestProtocolSuccess(t.data,type)
                    } else {
                        mRootView.showMessage(t.msg)
                    }
                }

            })
    }
}