package com.daomingedu.onecp.mvp.presenter

import android.app.Application
import com.daomingedu.onecp.mvp.model.entity.VersionBean
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.Preference
import com.daomingedu.onecp.mvp.contract.MainContract
import com.daomingedu.onecp.mvp.model.api.Api
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.util.RxUtils
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject

@ActivityScope
class MainPresenter
@Inject
constructor(model: MainContract.Model, rootView: MainContract.View) :
    BasePresenter<MainContract.Model, MainContract.View>(model, rootView){

    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

    private var videoTime by Preference(Constant.VIDEO_TIME,0)
    private var videoPixel by Preference(Constant.VIDEO_PIXEL,0)

    override fun onDestroy() {
        super.onDestroy()
    }

    fun getVersionInfo(){
        mModel.getVersionInfo(Constant.KEY,1)
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseJson<VersionBean>>(mErrorHandler){
                override fun onNext(t: BaseJson<VersionBean>) {
                    if (t.code == Api.SUCCESS) {
                        videoTime = t.data?.videoTime ?: 0
                        videoPixel = t.data?.videoPixel ?: 0
                        mRootView.requestVersionInfoSuccess(t.data)
                    }else{
                        mRootView.showMessage(t.msg)
                    }
                }
            })
    }
}