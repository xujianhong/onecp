package com.daomingedu.onecp.mvp.presenter

import android.app.Application
import android.text.TextUtils
import com.daomingedu.onecp.mvp.model.entity.AboutUsBean
import com.daomingedu.art.mvp.model.entity.RegisterBean
import com.daomingedu.onecp.BuildConfig
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.Preference
import com.daomingedu.onecp.mvp.contract.RegisterContract
import com.daomingedu.onecp.mvp.model.api.Api
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


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/25/2019 20:07
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class RegisterPresenter
@Inject
constructor(model: RegisterContract.Model, rootView: RegisterContract.View) :
    BasePresenter<RegisterContract.Model, RegisterContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mImageLoader: ImageLoader

    @Inject
    lateinit var mAppManager: AppManager

    private var sessionId by Preference(Constant.SESSIONID, "")
    override fun onDestroy() {
        super.onDestroy()
    }

    fun getProtocol(type:Int) {
        mModel.aboutUs(Constant.KEY).retryWhen(RetryWithDelay(2, 5))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseJson<AboutUsBean>>(mErrorHandler) {
                override fun onNext(t: BaseJson<AboutUsBean>) {
                    if (Api.SUCCESS == t.code) {
                        mRootView.requestAboutUsSuccess(t.data,type)
                    } else {
                        mRootView.showMessage(t.msg)
                    }
                }

            })
    }

    fun sendSMS(mobile: String) {
        mModel.sendSMS(Constant.KEY, mobile, 1)
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

    fun reg(
        name: String,
        mobile: String,
        password: String,
        identityType: Int,

        identityCard: String,
        birthDay: String,
        sex: String,
        province: String,
        city: String,
        county: String,
        schoolId: String,
        gradeName: String,
        classesName: String,
        gradeNo: String,
        classesNo: String,

        code: String
    ) {
        mModel.reg(
            name,
            mobile,
            password,
            identityType,
            identityCard,
            birthDay,
            sex,
            province,
            city,
            county,
            schoolId,
            gradeName,
            classesName,
            gradeNo,
            classesNo,
            1,
            null,
            BuildConfig.VERSION_CODE,
            code
        )
            .retryWhen(RetryWithDelay(2, 5))
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseJson<RegisterBean>>(mErrorHandler) {
                override fun onNext(t: BaseJson<RegisterBean>) {
                    if (Api.SUCCESS == t.code) {
                        sessionId = t.data?.sessionId ?: ""
                        mRootView.requestRegSuccess()
                    } else {
                        mRootView.showMessage(t.msg)
                    }
                }

                override fun onError(t: Throwable) {
                    super.onError(t)
                    mRootView.showMessage(t.toString())
                }
            })
    }
}
