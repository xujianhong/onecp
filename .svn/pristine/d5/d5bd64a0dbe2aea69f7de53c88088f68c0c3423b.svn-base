package com.daomingedu.onecp.di.module

import com.daomingedu.art.mvp.model.entity.NewsBean
import com.daomingedu.onecp.mvp.contract.ProvinceContract
import com.daomingedu.onecp.mvp.model.ProvinceModel
import com.daomingedu.onecp.mvp.model.entity.ProvinceBean
import com.daomingedu.onecp.mvp.ui.adapter.ProvinceAdapter
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * Created by jianhongxu on 3/16/21
 * Description
 */
@Module
class ProvinceModule(private val view: ProvinceContract.View) {
    @ActivityScope
    @Provides
    fun provideProvinceView(): ProvinceContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideProvinceModel(model: ProvinceModel): ProvinceContract.Model {
        return model
    }

    @ActivityScope
    @Provides
    fun provideDatas() = mutableListOf<ProvinceBean>()

    @ActivityScope
    @Provides
    fun provideProvinceAdapter(datas: MutableList<ProvinceBean>): ProvinceAdapter =
        ProvinceAdapter(datas)

}