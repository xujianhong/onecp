package com.daomingedu.talentgame.mvp.contract

import com.daomingedu.talentgame.mvp.model.entity.BaseJson
import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import io.reactivex.Observable

/**
 * Description
 * Created by jianhongxu on 4/8/21
 */
interface FeedbackContract {

    interface View : IView {
        fun requestFeedbackSuccess()
    }

    interface Model : IModel {
        fun feedback(sessionId: String, feedback: String): Observable<BaseJson<Any>>
    }
}