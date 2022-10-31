package com.daomingedu.onecp.mvp.presenter

import android.app.Application
import com.daomingedu.onecp.mvp.contract.ProvinceContract
import com.daomingedu.onecp.mvp.model.api.Api
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.mvp.model.entity.ProvinceBean
import com.daomingedu.onecp.mvp.ui.adapter.ProvinceAdapter
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
 * Created by jianhongxu on 3/16/21
 * Description
 */
@ActivityScope
class ProvincePresenter
@Inject
constructor(model: ProvinceContract.Model, view: ProvinceContract.View) :
    BasePresenter<ProvinceContract.Model, ProvinceContract.View>(model, view) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mImageLoader: ImageLoader

    @Inject
    lateinit var mAppManager: AppManager

    @Inject
    lateinit var mProvinceAdapter: ProvinceAdapter

    @Inject
    lateinit var mDatas: MutableList<ProvinceBean>

    fun province(pid: String, key: String) {
        mModel.province(pid, key)
            .retryWhen(RetryWithDelay(2, 5))
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
            .subscribe(object :ErrorHandleSubscriber<BaseJson<MutableList<ProvinceBean>>>(mErrorHandler){
                override fun onNext(t: BaseJson<MutableList<ProvinceBean>>) {
                   if(Api.SUCCESS ==t.code){
                       if(t.data!=null){
                           mDatas.clear()
                           mDatas.addAll(t.data!!)
                           mProvinceAdapter.notifyDataSetChanged()
                       }
                       mRootView.requestProvinceSuccess()
                   }
                    else{
                        mRootView.showMessage(t.msg)
                    }
                }

            })


    }


}

