package com.daomingedu.onecp.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import com.daomingedu.onecp.mvp.contract.SplashContract
import com.daomingedu.onecp.mvp.model.api.service.CommonService
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.mvp.model.entity.SessionIdBean
import javax.inject.Inject


@ActivityScope
class SplashModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), SplashContract.Model {
    override fun checkSessionId(sessionId: String): Observable<BaseJson<SessionIdBean>> {
        return mRepositoryManager.obtainRetrofitService(CommonService::class.java)
            .checkSessionId(sessionId)
    }

    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy()
    }
}
