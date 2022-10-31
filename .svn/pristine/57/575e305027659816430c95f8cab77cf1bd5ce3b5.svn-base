package com.daomingedu.onecp.mvp.model

import com.daomingedu.onecp.mvp.contract.EnrollTestContract
import com.daomingedu.onecp.mvp.model.api.service.CustomerService
import com.daomingedu.onecp.mvp.model.api.service.TestService
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.mvp.model.entity.TestInfo
import com.daomingedu.onecp.mvp.model.entity.UserInfoBean
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jianhongxu on 3/18/21
 * Description
 */
@ActivityScope
class EnrollTestModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    EnrollTestContract.Model {
    override fun getTestInfo(sessionId: String, testId: String): Observable<BaseJson<TestInfo>> {
        return mRepositoryManager.obtainRetrofitService(TestService::class.java)
            .getTestInfo(sessionId,testId)
    }


    override fun saveTestSign(
        testSignId:String,
        sessionId: String,
        testId: String,
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
        return mRepositoryManager.obtainRetrofitService(TestService::class.java)
            .saveTestSign(
                testSignId,
                sessionId,
                testId,
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

}