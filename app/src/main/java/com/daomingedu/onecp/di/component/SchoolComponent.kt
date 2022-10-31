package com.daomingedu.onecp.di.component

import com.daomingedu.onecp.di.module.SchoolModule
import com.daomingedu.onecp.mvp.ui.activity.SchoolAct
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import dagger.Component

/**
 * Created by jianhongxu on 3/17/21
 * Description
 */
@ActivityScope
@Component(modules = arrayOf(SchoolModule::class), dependencies = arrayOf(AppComponent::class))
interface SchoolComponent {

    fun inject(activity: SchoolAct)
}