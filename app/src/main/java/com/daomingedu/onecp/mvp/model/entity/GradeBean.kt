package com.daomingedu.art.mvp.model.entity

import com.daomingedu.onecp.mvp.model.entity.TestCityBean

data class GradeBean(
    val gradedNamae:String,
    val logoImg:String,
    val cityList:MutableList<TestCityBean>,
    val cityName:String,
    val url:String,
    val remark:String
)