package com.daomingedu.onecp.di.module

import com.daomingedu.onecp.mvp.contract.SchoolContract
import com.daomingedu.onecp.mvp.model.SchoolModel
import com.daomingedu.onecp.mvp.model.entity.SchoolBean
import com.daomingedu.onecp.mvp.ui.adapter.SchoolAdapter
import com.jess.arms.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by jianhongxu on 3/17/21
 * Description
 */
@Module
class SchoolModule (private val view:SchoolContract.View){
    @ActivityScope
    @Provides
    fun provideSchoolView():SchoolContract.View{
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideSchoolModel(model:SchoolModel):SchoolContract.Model{
        return model
    }

    @ActivityScope
    @Provides
    fun provideDatas()= mutableListOf<SchoolBean>()

    @ActivityScope
    @Provides
    fun provideSchoolAdapter(datas:MutableList<SchoolBean>):SchoolAdapter=SchoolAdapter(datas)
}