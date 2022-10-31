package com.daomingedu.onecp.mvp.model

import android.app.Application
import com.daomingedu.onecp.mvp.contract.ModifyStudentInfoContract
import com.daomingedu.onecp.mvp.model.api.service.CustomerService
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.mvp.model.entity.UserInfoBean
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jianhongxu on 3/21/21
 * Description
 */
@ActivityScope
class ModifyStudentInfoModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    ModifyStudentInfoContract.Model {
    override fun saveStudentInfo(
        sessionId: String,
        name: String,
        identityType: Int,
        identityCard: String,
        birthDay: String,
        sex: String,
        province: String,
        city: String,
        county: String,
        schoolId: String,
        gradeName: String,
        classesName: String,
        gradeNo: String,
        classesNo: String
    ): Observable<BaseJson<Any>> {
        return mRepositoryManager.obtainRetrofitService(CustomerService::class.java)
            .saveStudentInfo(
                sessionId,
                name,
                identityType,
                identityCard,
                birthDay,
                sex,
                province,
                city,
                county,
                schoolId,
                gradeName,
                classesName,
                gradeNo,
                classesNo
            )
    }

    override fun getStudentInfo(sessionId: String): Observable<BaseJson<UserInfoBean>> {
        return mRepositoryManager.obtainRetrofitService(CustomerService::class.java)
            .getStudentInfo(sessionId)
    }

    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;

}