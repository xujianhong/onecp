package com.daomingedu.onecp.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.daomingedu.onecp.R
import com.daomingedu.onecp.app.onClick
import com.daomingedu.onecp.di.component.DaggerForgetPasswordComponent
import com.daomingedu.onecp.di.module.ForgetPasswordModule
import com.daomingedu.onecp.mvp.contract.ForgetPasswordContract
import com.daomingedu.onecp.mvp.presenter.ForgetPasswordPresenter
import com.daomingedu.onecp.mvp.ui.widget.LoadingDialog
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_forget_password.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.etInputPhoneNumber
import kotlinx.android.synthetic.main.activity_login.etInputPwd
import java.util.concurrent.TimeUnit

class ForgetPasswordAct : BaseActivity<ForgetPasswordPresenter>(), ForgetPasswordContract.View {
    val loadingDialog: LoadingDialog by lazy {
        LoadingDialog(this)
    }
    var disposable: Disposable? = null

    override fun requestSendSMSSuccess() {
        val count = 60L//倒计时60秒requestRegSuccess
        disposable = Observable.interval(0, 1, TimeUnit.SECONDS)
            .take(count + 1)
            .map { aLong -> count - aLong }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { tvSendCode.isEnabled = false }
            .subscribe({ aLong -> tvSendCode.text = "${aLong}秒" }, { }, {
                tvSendCode.isEnabled = true
                tvSendCode.text = "获取验证码"
            })
    }
    override fun requestGetPswSuccess() {
        ArmsUtils.makeText(application,"找回密码成功,请重新登录")
        killMyself()
    }
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerForgetPasswordComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .forgetPasswordModule(ForgetPasswordModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_forget_password //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        tvSendCode.onClick {
            val mobile = etInputPhoneNumber.text.toString().trim()
            if (TextUtils.isEmpty(mobile)) {
                ArmsUtils.makeText(application,"手机号不能为空")
                return@onClick
            }
            mPresenter?.sendSMS(mobile)
        }

        btnComplete.onClick {
            val mobile = etInputPhoneNumber.text.toString().trim()
            if (TextUtils.isEmpty(mobile)) {
                ArmsUtils.makeText(application,"手机号不能为空")
                return@onClick
            }
            val verCode = etVerCode.text.toString().trim()
            if (TextUtils.isEmpty(verCode)) {
                ArmsUtils.makeText(application,"验证码不能为空")
                return@onClick
            }
            val pwd = etInputPwd.text.toString().trim()
            if (TextUtils.isEmpty(pwd)) {
                ArmsUtils.makeText(application,"密码不能为空")
                return@onClick
            }
            val pwdAgain = etInputPwdAgain.text.toString().trim()
            if (pwd != pwdAgain) {
                ArmsUtils.makeText(application,"两次密码不相同")
                return@onClick
            }
            mPresenter?.getPwd(mobile,verCode,pwd)
        }
    }

    override fun showLoading() {
        loadingDialog.show()
    }

    override fun hideLoading() {
        loadingDialog.dismiss()
    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}
