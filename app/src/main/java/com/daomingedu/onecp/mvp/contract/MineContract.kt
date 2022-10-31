package com.daomingedu.onecp.mvp.contract

import com.daomingedu.onecp.mvp.model.entity.AboutUsBean
import com.daomingedu.art.mvp.model.entity.CheckCerBean
import com.daomingedu.onecp.mvp.model.entity.UserInfoBean
import com.daomingedu.onecp.mvp.model.entity.BaseJson
import com.jess.arms.mvp.IModel
import com.jess.arms.mvp.IView
import io.reactivex.Observable


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
interface MineContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView {
        fun requestLogoutSuccess()
        fun requestGetCustomerInfoSuccess(data: UserInfoBean?)
        fun requestAboutUsSuccess(data: AboutUsBean? ,type:Int)
        fun requestCheckCerSuccess(data: CheckCerBean?)
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel {
        fun logout(
            sessionId: String
        ): Observable<BaseJson<Any>>

        fun getStudentInfo(
            sessionId: String
        ): Observable<BaseJson<UserInfoBean>>

        fun aboutUs(
            key: String
        ): Observable<BaseJson<AboutUsBean>>


    }

}
