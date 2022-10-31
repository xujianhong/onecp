package com.daomingedu.onecp.mvp.model

import android.app.Application
import com.daomingedu.onecp.mvp.contract.ProvinceContract
import com.daomingedu.onecp.mvp.model.api.service.CommonService
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.mvp.model.entity.ProvinceBean
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jianhongxu on 3/16/21
 * Description
 */
@ActivityScope
class ProvinceModel
@Inject constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    ProvinceContract.Model {

    override fun province(pid: String, key: String): Observable<BaseJson<MutableList<ProvinceBean>>> {
        return mRepositoryManager.obtainRetrofitService(CommonService::class.java)
            .getProvince(pid, key)
    }

    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;



}