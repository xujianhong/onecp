package com.daomingedu.onecp.mvp.model

import android.app.Application
import com.daomingedu.onecp.mvp.contract.CheckGradeContract
import com.daomingedu.onecp.mvp.model.api.service.TestService
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.mvp.model.entity.TestSignResultBean
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jianhongxu on 3/19/21
 * Description
 */
@ActivityScope
class CheckGradeModel
@Inject
constructor(repositoryManager:IRepositoryManager):BaseModel(repositoryManager),CheckGradeContract.Model{
    override fun getTestSignResult(
        sessionId: String,
        testId: String
    ): Observable<BaseJson<TestSignResultBean>> {
        return mRepositoryManager.obtainRetrofitService(TestService::class.java)
            .getTestSignResult(sessionId, testId)
    }

    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

}