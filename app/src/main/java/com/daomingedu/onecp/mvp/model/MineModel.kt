package com.daomingedu.onecp.mvp.model

import android.app.Application
import com.daomingedu.onecp.mvp.model.entity.AboutUsBean
import com.daomingedu.onecp.mvp.model.entity.UserInfoBean
import com.daomingedu.onecp.mvp.contract.MineContract
import com.daomingedu.onecp.mvp.model.api.service.CommonService
import com.daomingedu.onecp.mvp.model.api.service.CustomerService
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.google.gson.Gson
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/25/2019 21:53
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
class MineModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    MineContract.Model {


    override fun aboutUs(key: String): Observable<BaseJson<AboutUsBean>> {
        return mRepositoryManager.obtainRetrofitService(CommonService::class.java).aboutUs(key)
    }




    override fun logout(sessionId: String): Observable<BaseJson<Any>> {
        return mRepositoryManager.obtainRetrofitService(CustomerService::class.java)
            .logout(sessionId)
    }

    override fun getStudentInfo(sessionId: String): Observable<BaseJson<UserInfoBean>> {
        return mRepositoryManager.obtainRetrofitService(CustomerService::class.java)
            .getStudentInfo(sessionId)
    }

    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }
}
