package com.daomingedu.onecp.mvp.contract

import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.mvp.model.entity.TestSignResultBean
import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import io.reactivex.Observable

/**
 * Created by jianhongxu on 3/19/21
 * Description
 */
interface CheckGradeContract {

    interface View:IView{
        fun requestTestSignResult(data:TestSignResultBean?)
    }

    interface Model:IModel{
        fun getTestSignResult(
            sessionId:String,
            testId:String
        ):Observable<BaseJson<TestSignResultBean>>
    }
}