package com.daomingedu.onecp.di.module

import com.daomingedu.onecp.mvp.contract.QuestionContract
import com.daomingedu.onecp.mvp.model.QuestionModel
import com.jess.arms.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * Description
 * Created by jianhongxu on 2021/10/13
 */
@Module
class QuestionModule (private val view:QuestionContract.View){
    @FragmentScope
    @Provides
    fun provideQuestionView():QuestionContract.View{
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideQuestionModel(model:QuestionModel):QuestionContract.Model{
        return model
    }
}