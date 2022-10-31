package com.daomingedu.onecp.mvp.model.entity

/**
 * Created by jianhongxu on 3/24/21
 * Description
 */
data  class TestInfo(
    var id:String,
    var testName:String,

    var startSignDate:String,
    var endSignDate:String,
    var isSign:Int,

    var startUploadDate:String,
    var endUploadDate:String,
    var attendState:String,
    var attendScore:String,
    var attendComment:String,



    var student:UserInfoBean,


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