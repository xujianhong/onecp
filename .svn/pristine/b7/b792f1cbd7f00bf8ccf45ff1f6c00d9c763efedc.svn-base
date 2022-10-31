package com.daomingedu.onecp.di.module

import com.daomingedu.onecp.mvp.contract.CheckGradeContract
import com.daomingedu.onecp.mvp.model.CheckGradeModel
import com.jess.arms.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by jianhongxu on 3/19/21
 * Description
 */
@Module
class CheckGradeModule (private val view:CheckGradeContract.View){

    @ActivityScope
    @Provides
    fun provideCheckGradeView():CheckGradeContract.View{
        return view
    }

    @ActivityScope
    @Provides
    fun provideCheckGradeModel(model:CheckGradeModel):CheckGradeContract.Model{
        return model
    }


}