package com.daomingedu.onecp.mvp.presenter

import android.app.Application
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.Preference
import com.daomingedu.onecp.mvp.contract.ModifyPasswordContract
import com.daomingedu.onecp.mvp.model.api.Api
import com.daomingedu.onecp.mvp.model.entity.BaseJson
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

/**
 * Created by jianhongxu on 3/17/21
 * Description
 */
@ActivityScope
class ModifyPasswordPresenter
@Inject
constructor(model: ModifyPasswordContract.Model, view: ModifyPasswordContract.View) :
    BasePresenter<ModifyPasswordContract.Model, ModifyPasswordContract.View>(model, view) {

    @Inject
    lateinit var mErrorHandler: RxErrorHandler

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mImageLoader: ImageLoader

    @Inject
    lateinit var mAppManager: AppManager

    private var sessionId by Preference(Constant.SESSIONID, "")

    fun changePwd(oldPsw: String, newPsw: String){
        mModel.modifyPwd(sessionId,oldPsw, newPsw)
            .retryWhen(RetryWithDelay(2, 5))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :ErrorHandleSubscriber<BaseJson<Any>>(mErrorHandler){
                override fun onNext(t: BaseJson<Any>) {
                    if(Api.SUCCESS ==t.code){
                        mRootView.requestModifyPasswordSuccess()
                    }else{
                        mRootView.showMessage(t.msg)
                    }
                }

            })
    }


}