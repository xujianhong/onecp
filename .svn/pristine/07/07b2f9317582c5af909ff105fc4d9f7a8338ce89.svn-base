package com.daomingedu.onecp.di.component

import com.daomingedu.onecp.di.module.ModifyStudentInfoModule
import com.daomingedu.onecp.mvp.ui.activity.ModifyStudentInfoAct
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import dagger.Component

/**
 * Created by jianhongxu on 3/21/21
 * Description
 */
@ActivityScope
@Component(modules = arrayOf(ModifyStudentInfoModule::class),dependencies = arrayOf(AppComponent::class))
interface ModifyStudentInfoComponent {
    fun inject(activity:ModifyStudentInfoAct)
}