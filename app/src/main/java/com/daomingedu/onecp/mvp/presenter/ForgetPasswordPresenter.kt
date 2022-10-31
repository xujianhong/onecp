package com.daomingedu.onecp.mvp.presenter

import android.app.Application
import android.text.TextUtils
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.mvp.contract.ForgetPasswordContract
import com.daomingedu.onecp.mvp.model.api.Api
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.util.RxUtils
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import me.jessyan.rxerrorhandler.handler.RetryWithDelay
import javax.inject.Inject

@ActivityScope
class ForgetPasswordPresenter
@Inject
constructor(model: ForgetPasswordContract.Model, rootView: ForgetPasswordContract.View) :
    BasePresenter<ForgetPasswordContract.Model, ForgetPasswordContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mImageLoader: ImageLoader

    @Inject
    lateinit var mAppManager: AppManager


    override fun onDestroy() {
        super.onDestroy();
    }

    fun sendSMS(mobile: String) {
        mModel.sendSMS(Constant.KEY, mobile, 2)
            .retryWhen(RetryWithDelay(2, 5))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ErrorHandleSubscriber<BaseJson<Any>>(mErrorHandler) {
                override fun onNext(t: BaseJson<Any>) {
                    if (Api.SUCCESS == t.code) {
                        mRootView.requestSendSMSSuccess()
                    }
                    if (!TextUtils.isEmpty(t.msg)) {
                        mRootView.showMessage(t.msg)
                    }
                }

                override fun onError(t: Throwable) {
                    super.onError(t)
                    mRootView.showMessage(t.toString())
                }
            })
    }

    fun getPwd(mobile: String, code: String, newPsw: String) {
        mModel.getPsw(Constant.KEY, mobile, code, newPsw)
            .retryWhen(RetryWithDelay(2, 5))
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseJson<Any>>(mErrorHandler) {
                override fun onNext(t: BaseJson<Any>) {
                    if (Api.SUCCESS == t.code) {
                        mRootView.requestGetPswSuccess()
                    } else {
                        mRootView.showMessage(t.msg)
                    }
                }

            })
    }
}