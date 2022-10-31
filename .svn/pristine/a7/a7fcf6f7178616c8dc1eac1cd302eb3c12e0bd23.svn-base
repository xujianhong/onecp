package com.daomingedu.onecp.mvp.presenter

import android.app.Application
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.Preference
import com.daomingedu.onecp.mvp.contract.ModifyStudentInfoContract
import com.daomingedu.onecp.mvp.model.api.Api
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.mvp.model.entity.UserInfoBean
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
 * Created by jianhongxu on 3/21/21
 * Description
 */
@ActivityScope
class ModifyStudentInfoPresenter
@Inject
constructor(model: ModifyStudentInfoContract.Model, view: ModifyStudentInfoContract.View) :
    BasePresenter<ModifyStudentInfoContract.Model, ModifyStudentInfoContract.View>(model, view) {

    @Inject
    lateinit var mErrorHandler: RxErrorHandler

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mImageLoader: ImageLoader

    @Inject
    lateinit var mAppManager: AppManager

     val mSessionId by Preference(Constant.SESSIONID, "")

    fun saveStudentInfo(data: UserInfoBean) {
        mModel.saveStudentInfo(
            mSessionId,
            data.name,
            data.identityType,
            data.identityCard,
            data.birthDay,
            data.sex,
            data.province,
            data.city,
            data.county,
            data.schoolId,
            data.gradeName,
            data.classesName,
            data.gradeNo,
            data.classesNo
        ).retryWhen(RetryWithDelay(2, 5))
            .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseJson<Any>>(mErrorHandler) {
                override fun onNext(t: BaseJson<Any>) {
                    if (Api.SUCCESS == t.code) {
                        mRootView.requestSaveStudentInfoSuccess()
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

    fun getStudentInfo()
    {
        mModel.getStudentInfo(mSessionId)
            .retryWhen(RetryWithDelay(2, 5))
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseJson<UserInfoBean>>(mErrorHandler) {
                override fun onNext(t: BaseJson<UserInfoBean>) {
                    if (Api.SUCCESS == t.code) {
                        mRootView.requestStudentInfoSuccess(t.data)
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