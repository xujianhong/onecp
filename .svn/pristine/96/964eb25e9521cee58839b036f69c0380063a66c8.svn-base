package com.daomingedu.onecp.mvp.model

import android.app.Application
import com.daomingedu.onecp.mvp.contract.ModifyPasswordContract
import com.daomingedu.onecp.mvp.model.api.service.CustomerService
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jianhongxu on 3/17/21
 * Description
 */
@ActivityScope
class ModifyPasswordModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    ModifyPasswordContract.Model {
    override fun modifyPwd(
        sessionId: String,
        oldPsw: String,
        newPsw: String
    ): Observable<BaseJson<Any>> {
        return mRepositoryManager.obtainRetrofitService(CustomerService::class.java)
            .changePsw(sessionId, oldPsw, newPsw)
    }

    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;

}