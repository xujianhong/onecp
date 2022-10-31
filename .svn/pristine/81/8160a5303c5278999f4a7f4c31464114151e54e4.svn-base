package com.daomingedu.talentgame.mvp.model

import com.daomingedu.talentgame.mvp.contract.FeedbackContract
import com.daomingedu.talentgame.mvp.model.api.service.CustomerService
import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Description
 * Created by jianhongxu on 4/8/21
 */
@ActivityScope
class FeedbackModel
@Inject
constructor(repositoryManager:IRepositoryManager):BaseModel(repositoryManager),FeedbackContract.Model{
    override fun feedback(sessionId: String,feedback: String): Observable<BaseJson<Any>> {
        return mRepositoryManager.obtainRetrofitService(CustomerService::class.java).feedback(sessionId, feedback)
    }

}