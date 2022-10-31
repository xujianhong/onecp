package com.daomingedu.onecp.mvp.contract

import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.mvp.model.entity.ProvinceBean
import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import io.reactivex.Observable

/**
 * Created by jianhongxu on 3/16/21
 * Description
 */
interface ProvinceContract {

    interface View : IView {
        fun requestProvinceSuccess()
    }

    interface Model : IModel {
        fun province(
            pid: String, key: String
        ):Observable<BaseJson<MutableList<ProvinceBean>>>
    }
}