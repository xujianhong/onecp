package com.daomingedu.onecp.mvp.model

import android.app.Application
import com.daomingedu.onecp.mvp.contract.TestListContract
import com.daomingedu.onecp.mvp.model.api.service.TestService
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.mvp.model.entity.TestListBean
import com.google.gson.Gson
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jianhongxu on 3/15/21
 * Description
 */
@FragmentScope
class TestListModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    TestListContract.Model {

    override fun getTest(sessionId: String): Observable<BaseJson<MutableList<TestListBean>>> {
        return mRepositoryManager.obtainRetrofitService(TestService::class.java).getTest(sessionId)
    }

    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;


    override fun onDestroy() {
        super.onDestroy();
    }
}