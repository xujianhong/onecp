package com.daomingedu.onecp.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent


import com.jess.arms.di.scope.ActivityScope
import com.daomingedu.onecp.di.module.SplashModule
import com.daomingedu.onecp.mvp.ui.activity.SplashAct


@ActivityScope
@Component(modules = arrayOf(SplashModule::class), dependencies = arrayOf(AppComponent::class))
interface SplashComponent {
    fun inject(activity: SplashAct)
}
