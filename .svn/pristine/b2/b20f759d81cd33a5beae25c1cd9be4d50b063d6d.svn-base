package com.daomingedu.onecp.mvp.contract

import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import io.reactivex.Observable

/**
 * Created by jianhongxu on 3/17/21
 * Description
 */

interface ModifyPasswordContract {

    interface View : IView {
        fun requestModifyPasswordSuccess()
    }

    interface Model : IModel {
        fun modifyPwd(sessionId: String, oldPsw: String, newPsw: String): Observable<BaseJson<Any>>
    }
}