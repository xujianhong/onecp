package com.daomingedu.onecp.mvp.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import com.codbking.widget.DatePickDialog
import com.codbking.widget.bean.DateType
import com.daomingedu.onecp.R
import com.daomingedu.onecp.app.Constant

import com.daomingedu.onecp.app.JHCImageConfig
import com.daomingedu.onecp.app.loadImage
import com.daomingedu.onecp.app.onClick
import com.daomingedu.onecp.di.component.DaggerEnrollTestComponent
import com.daomingedu.onecp.di.module.EnrollTestModule
import com.daomingedu.onecp.mvp.contract.EnrollTestContract
import com.daomingedu.onecp.mvp.model.entity.TestInfo
import com.daomingedu.onecp.mvp.model.entity.UserInfoBean
import com.daomingedu.onecp.mvp.presenter.EnrollTestPresenter
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_enroll_test.*
import kotlinx.android.synthetic.main.activity_enroll_test.ivAvatar
import kotlinx.android.synthetic.main.activity_enroll_test.spCredentialsType

import kotlinx.android.synthetic.main.activity_enroll_test.spSelectSex
import kotlinx.android.synthetic.main.activity_enroll_test.tvCheck
import kotlinx.android.synthetic.main.activity_enroll_test.tvCheckReason
import kotlinx.android.synthetic.main.activity_enroll_test.tvSelectBirth
import kotlinx.android.synthetic.main.activity_enroll_test.tvSelectProvince
import kotlinx.android.synthetic.main.activity_enroll_test.tvSelectSchool
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_mine.*
import org.jetbrains.anko.startActivity
import timber.log.Timber
import java.text.SimpleDateFormat


/**
 * Created by jianhongxu on 3/18/21
 * Description 报名页面
 */
class EnrollTestAct : BaseActivity<EnrollTestPresenter>(), EnrollTestContract.View,
    AdapterView.OnItemSelectedListener {

    private lateinit var userInfoBean: UserInfoBean

    //测评Id
    private lateinit var testId: String

    private lateinit var testSignId: String

    private var isFresh: Boolean = false

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerEnrollTestComponent
            .builder()
            .appComponent(appComponent)
            .enrollTestModule(EnrollTestModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_enroll_test
    }

    override fun initData(savedInstanceState: Bundle?) {
        testId = intent.getStringExtra(Constant.ENROLL_TEST_EXTRA)
        testSignId = intent.getStringExtra(Constant.ENROLL_ID_EXTRA)
        title = "报名信息"
        isFresh = true
        mPresenter?.getTestInfo(testId)


    }

    override fun onResume() {
        super.onResume()
        if (isFresh) return
        mPresenter?.getTestInfo(testId)
    }


    override fun requestGetTestInfoSuccess(data: TestInfo?) {

        data?.apply {
            if (student.photoImg != "")
                ivAvatar.loadImage(
                    JHCImageConfig.Builder()
                        .imageView(ivAvatar)
                        .url(student.photoImg)
                        .isCircle(true)
                        .errorPic(R.mipmap.avatar_default)
                        .placeholder(R.mipmap.avatar_default)
                        .build()
                )

            if (isFresh) {
                etName.setText(student.name)
                etPhone.setText(student.mobilePhone)

                if (student.sex == "M") {
                    spSelectSex.setSelection(1)
                } else if (student.sex == "F") {
                    spSelectSex.setSelection(2)
                }

                tvSelectBirth.text = student.birthDay

                spCredentialsType.setSelection(student.identityType)

                etCredentialsNumber.setText(student.identityCard)


                tvSelectProvince.text = "$provinceName$cityName$countyName"
                tvSelectSchool.text = schoolName

                tvSelectGrade.text = gradeName
                tvSelectClass.text = classesName

                if(!TextUtils.isEmpty(startUploadDate)&&!TextUtils.isEmpty(endUploadDate)){

                    tvUpdate.text ="$startUploadDate 至 $endUploadDate"
                }else{
                    tvUpdate.text="暂未安排"
                }




                when (student.photoImgCheck) {
                    0 -> {
                        tvCheck.setTextColor(ArmsUtils.getColor(this@EnrollTestAct, R.color.black_39))
                        tvCheck.text = "待审核中"
                    }
                    1 -> {
                        tvCheck.setTextColor(ArmsUtils.getColor(this@EnrollTestAct, R.color.blue_500))
                        tvCheck.text = "审核通过"
                    }
                    2 -> {
                        tvCheck.setTextColor(ArmsUtils.getColor(this@EnrollTestAct, R.color.red_c6))
                        tvCheck.text = "审核未通过"

                        tvCheckReason.text = student.reason
                    }
                }


            }

        }

        if (data != null) {
            if (isFresh) {
                userInfoBean = data.student
                isFresh = false

            } else {
                userInfoBean.photoImg = data.student.photoImg
                return
            }

            spCredentialsType.onItemSelectedListener = this
            spSelectSex.onItemSelectedListener = this

            ivAvatar.onClick {
                startActivity<CommonWebAct>(
                    Constant.URL_EXTRA to Constant.UPLOAD_ID_PHOTO + mPresenter?.mSessionId,
                    Constant.TITLE_EXTRA to "上传证件照"
                )
            }

            tvSelectBirth.onClick {
                val dialog = DatePickDialog(this)
                //设置上下年分限制
                dialog.setYearLimt(80)
                //设置标题
                dialog.setTitle("选择时间")
                //设置类型
                dialog.setType(DateType.TYPE_YMD)
                //设置消息体的显示格式，日期格式
                dialog.setMessageFormat("yyyy-MM-dd")
                //设置选择回调
                dialog.setOnChangeLisener(null)
                //设置点击确定按钮回调
                dialog.setOnSureLisener {
                    val sdf = SimpleDateFormat("yyyy-MM-dd")
                    userInfoBean.birthDay = sdf.format(it)
                    tvSelectBirth.text = userInfoBean.birthDay

                }
                dialog.show()
            }

            btnEnroll.onClick {
                mPresenter?.saveTestSign(testSignId, testId, userInfoBean)
            }


        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            Constant.PROVINCE_TURN_EXTRA -> if (resultCode == Activity.RESULT_OK) {
                userInfoBean.province = data?.getStringExtra(Constant.PROVINCE_EXTRA).toString()
                userInfoBean.city = data?.getStringExtra(Constant.CITY_EXTRA).toString()
                userInfoBean.county = data?.getStringExtra(Constant.REGION_EXTRA).toString()
                tvSelectProvince.text = data?.getStringExtra(Constant.PROVINCE_SHOW_EXTRA)


            }

            Constant.SCHOOL_TURN_EXTRA -> if (resultCode == Activity.RESULT_OK) {
                tvSelectSchool.text = data?.getStringExtra(Constant.SCHOOL_NAME_EXTRA)
                userInfoBean.schoolId =
                    data?.getStringExtra(Constant.SCHOOL_NUMBER_EXTRA).toString()
            }
        }
    }


    override fun requestSaveTestSign() {
        showMessage("提交成功")
        killMyself()
    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun killMyself() {
        finish()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.spCredentialsType -> userInfoBean.identityType =
                when (spCredentialsType.getItemAtPosition(position)) {
                    "身份证" -> 1
                    "港澳通行证" -> 2
                    "台胞证" -> 3
                    "护照" -> 4
                    else
                    -> 0
                }

            R.id.spSelectSex -> {
                when (position) {
                    1 -> {
                        userInfoBean.sex = "M"
                    }
                    2 -> {
                        userInfoBean.sex = "F"
                    }
                }
            }

        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}