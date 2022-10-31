package com.daomingedu.onecp.di.component

import com.daomingedu.onecp.di.module.UpdateVideoModule
import com.daomingedu.onecp.mvp.ui.activity.UpdateVideoAct
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import dagger.Component

/**
 * Created by jianhongxu on 3/18/21
 * Description
 */
@ActivityScope
@Component(modules = arrayOf(UpdateVideoModule::class),dependencies = arrayOf(AppComponent::class))
interface UpdateVideoComponent {
    fun inject(activity:UpdateVideoAct)
}