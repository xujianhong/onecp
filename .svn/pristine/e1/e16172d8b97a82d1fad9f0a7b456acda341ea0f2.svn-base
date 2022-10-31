package com.daomingedu.talentgame.di.component

import com.daomingedu.talentgame.di.module.FeedbackModule
import com.daomingedu.talentgame.mvp.ui.FeedbackActivity
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