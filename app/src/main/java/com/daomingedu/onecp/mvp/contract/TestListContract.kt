package com.daomingedu.onecp.mvp.contract

import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.mvp.model.entity.TestListBean
import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import io.reactivex.Observable

/**
 * Created by jianhongxu on 3/15/21
 * Description
 */
interface TestListContract {

    interface View : IView {
        fun requestTestListSuccess()
    }

    interface Model : IModel {
        fun getTest(sessionId: String): Observable<BaseJson<MutableList<TestListBean>>>
    }

}