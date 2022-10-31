package com.daomingedu.onecp.mvp.ui.activity

import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.text.method.PasswordTransformationMethod
import com.daomingedu.onecp.R
import com.daomingedu.onecp.app.onClick
import com.daomingedu.onecp.di.component.DaggerModifyPasswordComponent
import com.daomingedu.onecp.di.module.ModifyPasswordModule
import com.daomingedu.onecp.mvp.contract.ModifyPasswordContract
import com.daomingedu.onecp.mvp.presenter.ModifyPasswordPresenter
import com.daomingedu.onecp.util.OSUtils
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_forget_password.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_modify_pwd.*
import kotlinx.android.synthetic.main.activity_modify_pwd.btnComplete
import kotlinx.android.synthetic.main.activity_modify_pwd.etInputPwd
import kotlinx.android.synthetic.main.activity_modify_pwd.etInputPwdAgain

/**
 * Created by jianhongxu on 3/17/21
 * Description 修改密码
 */
class ModifyPasswordAct : BaseActivity<ModifyPasswordPresenter>(), ModifyPasswordContract.View {
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerModifyPasswordComponent
            .builder()
            .appComponent(appComponent)
            .modifyPasswordModule(ModifyPasswordModule(this))
            .build()
            .inject(this)

    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_modify_pwd
    }

    override fun initData(savedInstanceState: Bundle?) {

        if (OSUtils.isEMUI() && android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            etInputPwd.inputType = InputType.TYPE_CLASS_TEXT
            etInputPwd.transformationMethod =PasswordTransformationMethod.getInstance()
            etInputPwdAgain.inputType = InputType.TYPE_CLASS_TEXT
            etInputPwdAgain.transformationMethod = PasswordTransformationMethod.getInstance()
        }

        btnComplete.onClick {
            val oldPwd = etInputPwd.text.toString().trim()
            if (TextUtils.isEmpty(oldPwd)) {
                ArmsUtils.makeText(application, "旧密码不能为空")
                return@onClick
            }
            val newPwd = etInputPwdAgain.text.toString().trim()
            if (TextUtils.isEmpty(newPwd)) {
                ArmsUtils.makeText(application, "新密码不能为空")
                return@onClick
            }
            if (oldPwd == newPwd) {
                ArmsUtils.makeText(application, "两次密码相同")
                return@onClick
            }
            mPresenter?.changePwd(oldPwd, newPwd)
        }
    }

    override fun requestModifyPasswordSuccess() {
       ArmsUtils.snackbarText("修改密码成功")
        killMyself()
    }

    override fun showMessage(message: String) {

        ArmsUtils.snackbarText(message)
    }

    override fun killMyself() {
        finish()
    }
}