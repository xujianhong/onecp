package com.daomingedu.onecp.di.component

import com.daomingedu.onecp.di.module.CheckGradeModule
import com.daomingedu.onecp.mvp.contract.CheckGradeContract
import com.daomingedu.onecp.mvp.ui.activity.CheckGradeAct
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import dagger.Component

/**
 * Created by jianhongxu on 3/19/21
 * Description
 */
@ActivityScope
@Component(modules = arrayOf(CheckGradeModule::class),dependencies = arrayOf(AppComponent::class))
interface CheckGradeComponent {
    fun inject(activity:CheckGradeAct)
}