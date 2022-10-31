package com.daomingedu.onecp.mvp.model.entity

/**
 * Created by jianhongxu on 3/17/21
 * Description 评测对象类
 */
data class TestListBean(
    val id: String,
    val testSignId: String,
    val testName: String,
    val startSignDate: String,
    val endSignDate: String,
    val isSign: Int,
    val isUpload: Int,
    val isResult: Int,
    val isAudit: Int,
    val startUploadDate: String,
    val endUploadDate: String
)