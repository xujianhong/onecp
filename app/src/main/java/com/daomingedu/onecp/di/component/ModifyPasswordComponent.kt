package com.daomingedu.onecp.di.component

import com.daomingedu.onecp.di.module.ModifyPasswordModule
import com.daomingedu.onecp.mvp.ui.activity.ModifyPasswordAct
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import dagger.Component

/**
 * Created by jianhongxu on 3/17/21
 * Description
 */
@ActivityScope
@Component(
    modules = arrayOf(ModifyPasswordModule::class),
    dependencies = arrayOf(AppComponent::class)
)
interface ModifyPasswordComponent {
    fun inject(activity: ModifyPasswordAct)
}