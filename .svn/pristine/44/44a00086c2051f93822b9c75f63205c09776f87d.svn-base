package com.daomingedu.onecp.di.module

import com.daomingedu.onecp.mvp.contract.EnrollTestContract
import com.daomingedu.onecp.mvp.model.EnrollTestModel
import com.jess.arms.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by jianhongxu on 3/18/21
 * Description
 *
 */
@Module
class EnrollTestModule(private val view: EnrollTestContract.View) {

    @ActivityScope
    @Provides
    fun provideEnrollTestView():EnrollTestContract.View{
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideEnrollTestModel(model:EnrollTestModel):EnrollTestContract.Model{
        return model
    }
}