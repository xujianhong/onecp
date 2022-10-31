package com.daomingedu.onecp.di.component

import com.daomingedu.art.mvp.ui.fragment.StudyCircleFragment
import com.daomingedu.onecp.di.module.StudyCircleModule
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import dagger.Component


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/25/2019 21:53
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
@Component(modules = arrayOf(StudyCircleModule::class), dependencies = arrayOf(AppComponent::class))
interface StudyCircleComponent {
    fun inject(fragment: StudyCircleFragment)
}
