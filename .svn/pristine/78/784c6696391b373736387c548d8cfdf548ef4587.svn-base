package com.daomingedu.onecp.mvp.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.daomingedu.onecp.R
import com.daomingedu.onecp.mvp.model.entity.ProvinceBean

/**
 * Created by jianhongxu on 3/16/21
 * Description
 */
class ProvinceAdapter(datas:MutableList<ProvinceBean>):BaseQuickAdapter<ProvinceBean,BaseViewHolder>(
    R.layout.item_province,datas) {
    override fun convert(helper: BaseViewHolder, item: ProvinceBean) {
        helper.setText(R.id.tvProvince,item.name)
    }
}