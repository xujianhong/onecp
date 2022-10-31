package com.daomingedu.onecp.di.module

import com.daomingedu.onecp.mvp.contract.ModifyStudentInfoContract
import com.daomingedu.onecp.mvp.model.ModifyStudentInfoModel
import com.jess.arms.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by jianhongxu on 3/21/21
 * Description
 */
@Module
class ModifyStudentInfoModule(val view: ModifyStudentInfoContract.View) {
    @ActivityScope
    @Provides
    fun provideModifyStudentInfoView(): ModifyStudentInfoContract.View {
        return view
    }

    @ActivityScope
    @Provides
    fun provideModifyStudentInfoModel(model: ModifyStudentInfoModel): ModifyStudentInfoContract.Model {
        return model
    }
}