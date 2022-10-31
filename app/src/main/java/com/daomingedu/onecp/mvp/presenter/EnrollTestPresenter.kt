package com.daomingedu.onecp.mvp.presenter

import android.app.Application
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.Preference
import com.daomingedu.onecp.mvp.contract.EnrollTestContract
import com.daomingedu.onecp.mvp.model.api.Api
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.mvp.model.entity.TestInfo
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
 * Created by jianhongxu on 3/18/21
 * Description
 */
@ActivityScope
class EnrollTestPresenter
@Inject
constructor(model: EnrollTestContract.Model, view: EnrollTestContract.View) :
    BasePresenter<EnrollTestContract.Model, EnrollTestContract.View>(model, view) {

    @Inject
    lateinit var mErrorHandler: RxErrorHandler

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mImageLoader: ImageLoader

    @Inject
    lateinit var mAppManager: AppManager

    val mSessionId by Preference(Constant.SESSIONID, "")

    fun getTestInfo(testId: String){
        mModel.getTestInfo(mSessionId,testId)
            .retryWhen(RetryWithDelay(1, 5))
            .observeOn(AndroidSchedulers.mainThread())
            .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
            .subscribe(object :ErrorHandleSubscriber<BaseJson<TestInfo>>(mErrorHandler){
                override fun onNext(t: BaseJson<TestInfo>) {
                    if(Api.SUCCESS ==t.code){
                        mRootView.requestGetTestInfoSuccess(t.data)
                    }else{
                        mRootView.showMessage(t.msg)
                    }
                }

            })
    }



    fun saveTestSign(testSignId:String,testId: String, data: UserInfoBean) {
        mModel.saveTestSign(
            testSignId,
            mSessionId,
            testId,
            data.name,
            data.identityType,
            data.identityCard,
            data.birthDay,
            data.sex
        )
            .retryWhen(RetryWithDelay(2, 5))
            .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseJson<Any>>(mErrorHandler) {
                override fun onNext(t: BaseJson<Any>) {
                    if (Api.SUCCESS == t.code) {
                        mRootView.requestSaveTestSign()
                    } else {
                        mRootView.showMessage(t.msg)
                    }
                }

            })
    }
}