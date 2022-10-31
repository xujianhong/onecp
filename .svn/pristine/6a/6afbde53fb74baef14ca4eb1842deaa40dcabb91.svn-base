package com.daomingedu.onecp.di.module

import com.daomingedu.onecp.mvp.contract.ModifyPasswordContract
import com.daomingedu.onecp.mvp.model.ModifyPasswordModel
import com.jess.arms.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by jianhongxu on 3/17/21
 * Description
 */
@Module
class ModifyPasswordModule (private val view:ModifyPasswordContract.View){
    @ActivityScope
    @Provides
    fun provideModifyPasswordView():ModifyPasswordContract.View{
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideModifyPasswordModel(model:ModifyPasswordModel):ModifyPasswordContract.Model{
        return model
    }
}