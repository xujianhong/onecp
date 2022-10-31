package com.daomingedu.onecp.mvp.model

import android.app.Application
import com.daomingedu.onecp.mvp.contract.SchoolContract
import com.daomingedu.onecp.mvp.model.api.service.CommonService
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.mvp.model.entity.SchoolBean
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
class SchoolModel
@Inject constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    SchoolContract.Model {
    override fun school(
        county: String,
        keyWords: String,
        key: String
    ): Observable<BaseJson<MutableList<SchoolBean>>> {
        return mRepositoryManager.obtainRetrofitService(CommonService::class.java)
            .getSchool(county, keyWords, key)
    }

    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;
}