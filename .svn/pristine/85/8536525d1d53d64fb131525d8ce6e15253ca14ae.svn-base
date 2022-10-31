package com.daomingedu.onecp.mvp.presenter

import android.app.Application
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.Preference
import com.daomingedu.onecp.mvp.contract.TestListContract
import com.daomingedu.onecp.mvp.model.api.Api
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.mvp.model.entity.TestListBean
import com.daomingedu.onecp.mvp.ui.adapter.TestListAdapter
import com.jess.arms.di.scope.FragmentScope
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
 * Created by jianhongxu on 3/15/21
 * Description
 */
@FragmentScope
class TestListPresenter
@Inject
constructor(model: TestListContract.Model, rootView: TestListContract.View) :
    BasePresenter<TestListContract.Model, TestListContract.View>(model, rootView) {

    @Inject
    lateinit var mErrorHandler: RxErrorHandler

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mImageLoader: ImageLoader

    @Inject
    lateinit var mAppManager: AppManager

    @Inject
    lateinit var mDatas: MutableList<TestListBean>

    @Inject
    lateinit var mAdapter: TestListAdapter

    private val sessionId: String by Preference(Constant.SESSIONID, "")


    fun getTest() {
        mModel.getTest(sessionId)
            .subscribeOn(Schedulers.io())
            .retryWhen(RetryWithDelay(2, 5))
            .doOnSubscribe {
                mRootView.showLoading()

            }.subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                mRootView.hideLoading()

            }
            .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseJson<MutableList<TestListBean>>>(mErrorHandler) {
                override fun onNext(t: BaseJson<MutableList<TestListBean>>) {
                    if (Api.SUCCESS == t.code) {
                        if (t.data != null) {
                            mDatas.clear()
                            mDatas.addAll(t.data!!)
                            mAdapter.notifyDataSetChanged()
                            mRootView.requestTestListSuccess()
                        }
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