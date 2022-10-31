package com.daomingedu.onecp.di.component

import com.daomingedu.onecp.di.module.EnrollTestModule
import com.daomingedu.onecp.mvp.ui.activity.EnrollTestAct
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import dagger.Component

/**
 * Created by jianhongxu on 3/18/21
 * Description
 */
@ActivityScope
@Component(modules = arrayOf(EnrollTestModule::class), dependencies = arrayOf(AppComponent::class))
interface EnrollTestComponent {
    fun inject(activity: EnrollTestAct)
}