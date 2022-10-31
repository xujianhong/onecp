package com.daomingedu.onecp.mvp.contract

import com.daomingedu.onecp.mvp.model.entity.VersionBean
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import io.reactivex.Observable

interface MainContract {
    interface View: IView {
        fun requestVersionInfoSuccess(data: VersionBean?)
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel {
        fun getVersionInfo(
            key: String, systemType: Int
        ): Observable<BaseJson<VersionBean>>
    }
}