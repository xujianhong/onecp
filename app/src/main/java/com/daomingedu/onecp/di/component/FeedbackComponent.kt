package com.daomingedu.onecp.di.component


import com.daomingedu.onecp.di.module.FeedbackModule
import com.daomingedu.onecp.mvp.ui.activity.FeedbackActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import dagger.Component

/**
 * Description
 * Created by jianhongxu on 4/8/21
 */
@ActivityScope
@Component(modules = [FeedbackModule::class], dependencies = [AppComponent::class])
interface FeedbackComponent {
    fun inject(activity: FeedbackActivity)
}