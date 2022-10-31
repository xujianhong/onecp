package com.daomingedu.onecp.mvp.ui.adapter

import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.daomingedu.onecp.R
import com.daomingedu.onecp.mvp.model.entity.TestListBean
import com.jess.arms.utils.ArmsUtils

/**
 * Created by jianhongxu on 3/18/21
 * Description
 */
class TestListAdapter(datas: MutableList<TestListBean>) :
    BaseQuickAdapter<TestListBean, BaseViewHolder>(
        R.layout.item_test_list, datas
    ) {
    override fun convert(helper: BaseViewHolder?, item: TestListBean?) {
        if (item?.endUploadDate == null || item?.startUploadDate == null) {
            helper?.setText(R.id.tvEnrollTime,"暂无安排")
        } else
            helper?.setText(
                R.id.tvEnrollTime,
                "视频上传日期：" + item?.startUploadDate + "至" + item?.endUploadDate
            )
        helper?.setText(R.id.tvTestName, item?.testName)


        when (item?.isSign) {
            1 -> {
                helper?.getView<Button>(R.id.btnEnroll)?.setBackgroundResource(R.drawable.gradient_blue_bg)
                helper?.getView<Button>(R.id.btnEnroll)?.isClickable = true
                helper?.addOnClickListener(R.id.btnEnroll)
            }
            else
            ->{
                helper?.getView<Button>(R.id.btnEnroll)?.setBackgroundResource(R.drawable.gradient_gray_bg)
                helper?.getView<Button>(R.id.btnEnroll)?.isClickable = false
            }
        }
        when (item?.isResult) {
            1 -> {
                helper?.getView<Button>(R.id.btnCheck)?.setBackgroundResource(R.drawable.gradient_blue_bg)
                helper?.getView<Button>(R.id.btnCheck)?.isClickable = true
                helper?.addOnClickListener(R.id.btnCheck)
            }
            else
            ->{
                helper?.getView<Button>(R.id.btnCheck)?.setBackgroundResource(R.drawable.gradient_gray_bg)
                helper?.getView<Button>(R.id.btnCheck)?.isClickable = false
            }
        }
        when (item?.isUpload) {
            1 -> {
                helper?.getView<Button>(R.id.btnUpdate)?.setBackgroundResource(R.drawable.gradient_blue_bg)
                helper?.getView<Button>(R.id.btnUpdate)?.isClickable = true
                helper?.addOnClickListener(R.id.btnUpdate)
            }
            else
            -> {
                helper?.getView<Button>(R.id.btnUpdate)?.setBackgroundResource(R.drawable.gradient_gray_bg)
                helper?.getView<Button>(R.id.btnUpdate)?.isClickable = false
            }
        }

        when (item?.isAudit) {
            2 -> {
                helper?.getView<TextView>(R.id.tvState)
                    ?.setTextColor(ArmsUtils.getColor(mContext, R.color.red_c6))
                helper?.setText(R.id.tvState, "视频未上传")
            }
            1 -> {
                helper?.getView<TextView>(R.id.tvState)
                    ?.setTextColor(ArmsUtils.getColor(mContext, R.color.red_c6))
                helper?.setText(R.id.tvState, "视频待审核")
            }
            0 -> {
                helper?.getView<TextView>(R.id.tvState)
                    ?.setTextColor(ArmsUtils.getColor(mContext, R.color.blue_500))
                helper?.setText(R.id.tvState, "上传成功")

            }

        }

    }
}