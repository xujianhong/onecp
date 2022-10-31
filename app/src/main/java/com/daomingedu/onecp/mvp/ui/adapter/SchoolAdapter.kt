package com.daomingedu.onecp.mvp.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.daomingedu.onecp.R
import com.daomingedu.onecp.mvp.model.entity.SchoolBean

/**
 * Created by jianhongxu on 3/17/21
 * Description
 */
class SchoolAdapter(datas: MutableList<SchoolBean>) :
    BaseQuickAdapter<SchoolBean, BaseViewHolder>(R.layout.item_province, datas) {
    override fun convert(helper: BaseViewHolder?, item: SchoolBean?) {
        helper?.setText(R.id.tvProvince, item?.name)
    }
}