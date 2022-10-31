package com.daomingedu.onecp.di.module

import com.daomingedu.onecp.mvp.contract.UpdateVideoContract
import com.daomingedu.onecp.mvp.model.UpdateVideoModel
import com.jess.arms.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by jianhongxu on 3/18/21
 * Description
 */
@Module
class UpdateVideoModule (private val view:UpdateVideoContract.View){
    @ActivityScope
    @Provides
    fun provideUpdateVideoView():UpdateVideoContract.View{
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideUpdateVideoModel(model:UpdateVideoModel):UpdateVideoContract.Model{
        return model
    }
}