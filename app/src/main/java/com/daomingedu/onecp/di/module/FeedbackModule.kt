package com.daomingedu.onecp.di.module

import com.daomingedu.onecp.mvp.contract.FeedbackContract
import com.daomingedu.onecp.mvp.model.FeedbackModel
import com.jess.arms.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Description
 * Created by jianhongxu on 4/8/21
 */
@Module
class FeedbackModule(val view: FeedbackContract.View) {

    @ActivityScope
    @Provides
    fun provideFeedbackView():FeedbackContract.View{
        return view
    }

    @ActivityScope
    @Provides
    fun provideFeedbackModel(model: FeedbackModel):FeedbackContract.Model{
        return model
    }
}