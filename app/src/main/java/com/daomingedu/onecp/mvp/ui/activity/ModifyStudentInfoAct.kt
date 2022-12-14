package com.daomingedu.onecp.mvp.ui.activity

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
import com.daomingedu.onecp.di.component.DaggerModifyStudentInfoComponent
import com.daomingedu.onecp.di.module.ModifyStudentInfoModule
import com.daomingedu.onecp.mvp.contract.ModifyStudentInfoContract
import com.daomingedu.onecp.mvp.model.entity.UserInfoBean
import com.daomingedu.onecp.mvp.presenter.ModifyStudentInfoPresenter
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_enroll_test.*
import kotlinx.android.synthetic.main.activity_enroll_test.btnEnroll
import kotlinx.android.synthetic.main.activity_enroll_test.etCredentialsNumber
import kotlinx.android.synthetic.main.activity_enroll_test.etName
import kotlinx.android.synthetic.main.activity_enroll_test.etPhone
import kotlinx.android.synthetic.main.activity_enroll_test.ivAvatar
import kotlinx.android.synthetic.main.activity_enroll_test.spCredentialsType
import kotlinx.android.synthetic.main.activity_enroll_test.spSelectSex
import kotlinx.android.synthetic.main.activity_enroll_test.tvSelectBirth
import kotlinx.android.synthetic.main.activity_enroll_test.tvSelectProvince
import kotlinx.android.synthetic.main.activity_enroll_test.tvSelectSchool

import kotlinx.android.synthetic.main.activity_modify_student.*
import kotlinx.android.synthetic.main.activity_modify_student.tvCheck
import kotlinx.android.synthetic.main.activity_modify_student.tvCheckReason
import org.jetbrains.anko.startActivity

import java.text.SimpleDateFormat

/**
 * Created by jianhongxu on 3/21/21
 * Description ??????????????????
 */
class ModifyStudentInfoAct : BaseActivity<ModifyStudentInfoPresenter>(),
    ModifyStudentInfoContract.View, AdapterView.OnItemSelectedListener {

    private lateinit var userInfoBean: UserInfoBean

    private var isFresh: Boolean = false

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerModifyStudentInfoComponent.builder().appComponent(appComponent)
            .modifyStudentInfoModule(
                ModifyStudentInfoModule(this)
            ).build().inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_modify_student
    }

    override fun initData(savedInstanceState: Bundle?) {

        title = "????????????"

        isFresh = true
        mPresenter?.getStudentInfo()

    }

    override fun onResume() {
        super.onResume()
        if (isFresh) return
        mPresenter?.getStudentInfo()
    }

    override fun requestSaveStudentInfoSuccess() {
        showMessage("????????????")
        finish()
    }

    override fun requestStudentInfoSuccess(data: UserInfoBean?) {
        data?.apply {
            if (photoImg != "")
                ivAvatar.loadImage(
                    JHCImageConfig.Builder()
                        .imageView(ivAvatar)
                        .url(photoImg)
                        .isCircle(true)
                        .errorPic(R.mipmap.avatar_default)
                        .placeholder(R.mipmap.avatar_default)
                        .build()
                )

            if (isFresh) { //???????????????????????????????????????
                etName.setText(name)
                etPhone.setText(mobilePhone)

                if (sex == "M") {
                    spSelectSex.setSelection(1)
                } else if (sex == "F") {
                    spSelectSex.setSelection(2)
                }

                tvSelectBirth.text = birthDay

                spCredentialsType.setSelection(identityType)

                etCredentialsNumber.setText(identityCard)

                tvSelectProvince.text = "$provinceName$cityName$countyName"
                tvSelectSchool.text = schoolName

                if (!TextUtils.isEmpty(gradeNo))
                    spSelectGrade.setSelection(gradeNo.toInt())
                if (!TextUtils.isEmpty(classesNo))
                    spSelectClass.setSelection(classesNo.toInt())

                when (photoImgCheck) {
                    0 -> {
                        tvCheck.setTextColor(ArmsUtils.getColor(this@ModifyStudentInfoAct, R.color.black_39))
                        tvCheck.text = "????????????"
                    }
                    1 -> {
                        tvCheck.setTextColor(ArmsUtils.getColor(this@ModifyStudentInfoAct, R.color.blue_500))
                        tvCheck.text = "????????????"
                    }
                    2 -> {
                        tvCheck.setTextColor(ArmsUtils.getColor(this@ModifyStudentInfoAct, R.color.red_c6))
                        tvCheck.text = "???????????????"

                        tvCheckReason.text = reason
                    }
                }
            }
        }

        if (data != null) {
            if (isFresh) {
                userInfoBean = data
                isFresh = false
            } else {

                userInfoBean.photoImg = data.photoImg
                return
            }

            spCredentialsType.onItemSelectedListener = this
            spSelectGrade.onItemSelectedListener = this
            spSelectClass.onItemSelectedListener = this
            spSelectSex.onItemSelectedListener = this

            ivAvatar.onClick {

                startActivity<CommonWebAct>(
                    Constant.URL_EXTRA to Constant.UPLOAD_ID_PHOTO + mPresenter?.mSessionId,
                    Constant.TITLE_EXTRA to "???????????????"
                )
            }

            tvSelectProvince.onClick {
                val intent = Intent(this, ProvinceAct::class.java)
                startActivityForResult(intent, Constant.PROVINCE_TURN_EXTRA)

            }
            tvSelectSchool.onClick {
                val intent = Intent(this, SchoolAct::class.java)
                intent.putExtra(Constant.REGION_EXTRA, userInfoBean.county)
                startActivityForResult(intent, Constant.SCHOOL_TURN_EXTRA)
            }

            tvSelectBirth.onClick {
                val dialog = DatePickDialog(this)
                //????????????????????????
                dialog.setYearLimt(80)
                //????????????
                dialog.setTitle("????????????")
                //????????????
                dialog.setType(DateType.TYPE_YMD)
                //?????????????????????????????????????????????
                dialog.setMessageFormat("yyyy-MM-dd")
                //??????????????????
                dialog.setOnChangeLisener(null)
                //??????????????????????????????
                dialog.setOnSureLisener {
                    val sdf = SimpleDateFormat("yyyy-MM-dd")
                    userInfoBean.birthDay = sdf.format(it)
                    tvSelectBirth.text = userInfoBean.birthDay

                }
                dialog.show()
            }

            btnEnroll.onClick {
                mPresenter?.saveStudentInfo(userInfoBean)
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

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.spCredentialsType -> userInfoBean.identityType =
                when (spCredentialsType.getItemAtPosition(position)) {
                    "?????????" -> 1
                    "???????????????" -> 2
                    "?????????" -> 3
                    "??????" -> 4
                    else
                    -> 0
                }
            R.id.spSelectGrade -> {
                when (position) {
                    0 -> {

                    }
                    else -> {
                        userInfoBean.gradeName =
                            spSelectGrade.getItemAtPosition(position).toString()
                        userInfoBean.gradeNo = position.toString().trim()
                    }
                }
            }

            R.id.spSelectClass -> {
                when (position) {
                    0 -> {

                    }
                    else -> {
                        userInfoBean.classesName =
                            spSelectClass.getItemAtPosition(position).toString()
                        userInfoBean.classesNo = position.toString().trim()

                    }
                }

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