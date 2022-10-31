package com.daomingedu.onecp.di.component

import com.daomingedu.onecp.di.module.QuestionModule
import com.daomingedu.onecp.mvp.ui.fragment.QuestionFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import dagger.Component

/**
 * Description
 * Created by jianhongxu on 2021/10/13
 */
@FragmentScope
@Component(modules = arrayOf(QuestionModule::class),dependencies = arrayOf(AppComponent::class))
interface QuestionComponent {
    fun inject(fragment:QuestionFragment)
}