package com.daomingedu.onecp.mvp.presenter

import android.app.Application
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.mvp.contract.SchoolContract
import com.daomingedu.onecp.mvp.model.api.Api
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.mvp.model.entity.SchoolBean
import com.daomingedu.onecp.mvp.ui.adapter.SchoolAdapter
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.utils.RxLifecycleUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import me.jessyan.rxerrorhandler.handler.RetryWithDelay
import javax.inject.Inject

/**
 * Created by jianhongxu on 3/17/21
 * Description
 */
@ActivityScope
class SchoolPresenter
@Inject
constructor(model: SchoolContract.Model, view: SchoolContract.View) :
    BasePresenter<SchoolContract.Model, SchoolContract.View>(model, view) {

    @Inject
    lateinit var mErrorHandler: RxErrorHandler

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mImageLoader: ImageLoader

    @Inject
    lateinit var mAppManager: AppManager


    @Inject
    lateinit var mSchoolAdapter: SchoolAdapter

    @Inject
    lateinit var mDatas: MutableList<SchoolBean>

    fun school(county: String, keyWords: String) {
        mModel.school(county, keyWords, Constant.KEY)
            .retryWhen(RetryWithDelay(2, 5))
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseJson<MutableList<SchoolBean>>>(mErrorHandler) {
                override fun onNext(t: BaseJson<MutableList<SchoolBean>>) {
                    if (Api.SUCCESS == t.code) {
                        if (t.data != null) {
                            mDatas.clear()
                            mDatas.addAll(t.data!!)
                            mSchoolAdapter.notifyDataSetChanged()
                        }
                    } else
                        mRootView.showMessage(t.msg)
                }

            })
    }
}