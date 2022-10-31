package com.daomingedu.onecp.mvp.contract

import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.daomingedu.onecp.mvp.model.entity.SchoolBean
import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import io.reactivex.Observable

/**
 * Created by jianhongxu on 3/17/21
 * Description
 */
interface SchoolContract {

    interface View:IView{

    }

    interface Model:IModel{
        fun school(
            county:String,
            keyWords:String,
            key:String
        ):Observable<BaseJson<MutableList<SchoolBean>>>
    }
}