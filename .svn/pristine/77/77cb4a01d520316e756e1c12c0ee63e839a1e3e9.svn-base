package com.daomingedu.onecp.mvp.presenter

import android.app.Application
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.Preference
import com.daomingedu.onecp.mvp.contract.CheckGradeContract
import com.daomingedu.onecp.mvp.model.api.Api
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.mvp.model.entity.TestSignResultBean
import com.daomingedu.onecp.util.RxUtils
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import me.jessyan.rxerrorhandler.handler.RetryWithDelay
import javax.inject.Inject

/**
 * Created by jianhongxu on 3/19/21
 * Description
 */
@ActivityScope
class CheckGradePresenter
@Inject
constructor(model: CheckGradeContract.Model, view: CheckGradeContract.View) :
    BasePresenter<CheckGradeContract.Model, CheckGradeContract.View>(model, view) {

    @Inject
    lateinit var mErrorHandler: RxErrorHandler

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mImageLoader: ImageLoader

    @Inject
    lateinit var mAppManager: AppManager

    private val mSessionId by Preference(Constant.SESSIONID, "")

    /**
     * 获取评测成绩
     */
    fun getTestSignResult(testId:String){
        mModel.getTestSignResult(mSessionId,testId)
            .retryWhen(RetryWithDelay(2, 5))
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :ErrorHandleSubscriber<BaseJson<TestSignResultBean>>(mErrorHandler){
                override fun onNext(t: BaseJson<TestSignResultBean>) {
                    if (Api.SUCCESS == t.code) {

                        mRootView.requestTestSignResult(t.data)
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
}