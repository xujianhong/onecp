package com.daomingedu.onecp.di.component

import com.daomingedu.onecp.di.module.TestListModule
import com.daomingedu.onecp.mvp.ui.fragment.TestFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import dagger.Component

/**
 * Created by jianhongxu on 3/18/21
 * Description
 */
@FragmentScope
@Component(modules = arrayOf(TestListModule::class),dependencies = arrayOf(AppComponent::class))
interface TestListComponent {
    fun inject(fragment:TestFragment)
}