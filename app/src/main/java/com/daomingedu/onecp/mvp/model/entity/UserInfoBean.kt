package com.daomingedu.onecp.mvp.model.entity

data class UserInfoBean(
    var mobilePhone: String,
    var birthDay: String,
    var name: String,
    var sex: String,
    var identityType: Int,
    var identityCard: String,
    var photoImg: String,
    var photoImgCheck: Int,
    var reason:String,

    var status: String,
    var province: String,
    var provinceName: String,
    var city: String,
    var cityName: String,
    var county: String,
    var countyName: String,

    var schoolName: String,
    var schoolId: String,
    var gradeName: String,
    var gradeNo: String,
    var classesName: String,
    var classesNo: String
)