package com.daomingedu.onecp.di.component

import com.daomingedu.onecp.di.module.ForgetPasswordModule
import com.daomingedu.onecp.mvp.ui.activity.ForgetPasswordAct
import dagger.Component
import com.jess.arms.di.component.AppComponent



import com.jess.arms.di.scope.ActivityScope



/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/27/2019 16:30
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = arrayOf(ForgetPasswordModule::class), dependencies = arrayOf(AppComponent::class))
interface ForgetPasswordComponent {
    fun inject(activity: ForgetPasswordAct)
}
