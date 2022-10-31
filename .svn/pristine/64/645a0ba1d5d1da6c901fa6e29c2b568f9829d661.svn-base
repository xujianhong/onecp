package com.daomingedu.onecp.di.component

import com.daomingedu.onecp.di.module.ProvinceModule
import com.daomingedu.onecp.mvp.ui.activity.ProvinceAct
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import dagger.Component

/**
 * Created by jianhongxu on 3/16/21
 * Description
 */
@ActivityScope
@Component(modules = arrayOf(ProvinceModule::class), dependencies = arrayOf(AppComponent::class))
interface ProvinceComponent {
    fun inject(activity: ProvinceAct)
}