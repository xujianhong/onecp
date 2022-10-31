package com.daomingedu.onecp.di.module

import com.daomingedu.onecp.mvp.contract.TestListContract
import com.daomingedu.onecp.mvp.model.TestListModel
import com.daomingedu.onecp.mvp.model.entity.TestListBean
import com.daomingedu.onecp.mvp.ui.adapter.TestListAdapter
import com.jess.arms.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * Created by jianhongxu on 3/15/21
 * Description
 */
@Module
class TestListModule (private  val view:TestListContract.View){
    @FragmentScope
    @Provides
    fun provideTestView():TestListContract.View{
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideTestModel(model: TestListModel):TestListContract.Model{
        return model
    }

    @FragmentScope
    @Provides
    fun provideDatas() = mutableListOf<TestListBean>()


    @FragmentScope
    @Provides
    fun provideTestListAdapter(datas:MutableList<TestListBean>) = TestListAdapter(datas)
}