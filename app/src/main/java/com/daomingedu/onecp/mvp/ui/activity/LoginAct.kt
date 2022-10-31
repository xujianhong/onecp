package com.daomingedu.onecp.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.daomingedu.onecp.R
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.Preference
import com.daomingedu.onecp.app.onClick
import com.daomingedu.onecp.di.component.DaggerLoginComponent
import com.daomingedu.onecp.di.module.LoginModule
import com.daomingedu.onecp.mvp.contract.LoginContract
import com.daomingedu.onecp.mvp.model.entity.AboutUsBean
import com.daomingedu.onecp.mvp.presenter.LoginPresenter
import com.daomingedu.onecp.mvp.ui.widget.LoadingDialog
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.integration.AppManager
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.diag_privacy.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import timber.log.Timber

class LoginAct : BaseActivity<LoginPresenter>(), LoginContract.View {

    var isFirst by Preference("isFrist", true)
    val loadingDialog: LoadingDialog by lazy {
        LoadingDialog(this)
    }


    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .loginModule(LoginModule(this))
            .build()
            .inject(this)

    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login
    }

    override fun initData(savedInstanceState: Bundle?) {
        AppManager.getAppManager().killAll(LoginAct::class.java)

        tvRegisterProtocol.onClick {
            mPresenter?.getProtocol(1)
        }
        tvRegisterPrivate.onClick {
            mPresenter?.getProtocol(2)
        }
        tvForgetPassword.onClick {
            startActivity<ForgetPasswordAct>()
        }
        tvRegister.onClick {
            startActivity<RegisterAct>()
        }
        btnLogin.onClick {
            val mobile = etInputPhoneNumber.text.toString().trim()
            if (TextUtils.isEmpty(mobile)) {
                ArmsUtils.makeText(application, "手机号不能为空")
                return@onClick
            }
            val pwd = etInputPwd.text.toString().trim()
            if (TextUtils.isEmpty(pwd)) {
                ArmsUtils.makeText(application, "密码不能为空")
                return@onClick
            }
            mPresenter?.login(mobile, pwd)
        }
        btnExit.onClick {
            killMyself()
        }

        if (isFirst)
            showDialog()
    }

    override fun requestLoginSuccess() {
        Timber.d("登陆成功")
        startActivity<MainAct>()
    }

    override fun requestProtocolSuccess(data: AboutUsBean?, type: Int) {
        data?.let {
            if (type == 1) {
                startActivity<CommonWebAct>(
                    Constant.URL_EXTRA to it.userAgreement,
                    Constant.TITLE_EXTRA to "用户协议"
                )
            } else if (type == 2) {
                startActivity<CommonWebAct>(
                    Constant.URL_EXTRA to it.privacyAgreement,
                    Constant.TITLE_EXTRA to "隐私政策"
                )
            }
        }

    }

    override fun showLoading() {
        loadingDialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        loadingDialog.dismiss()
    }

    override fun hideLoading() {
        loadingDialog.hide()
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


    private fun showDialog() {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.show()
        alertDialog.setCancelable(false)
        val window = alertDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.diag_privacy);
            window.setGravity(Gravity.CENTER);

            val tvContent: TextView = window.findViewById(R.id.tv_content);
            val tvCancel: TextView = window.findViewById(R.id.tv_cancel);
            val tvAgree: TextView = window.findViewById(R.id.tv_agree)


            val str = "    感谢您对本公司的支持!本公司非常重视您的个人信息和隐私保护。" +
                    "为了更好地保障您的个人权益，在您使用我们的产品前，" +
                    "请务必审慎阅读《隐私政策》和《用户协议》内的所有条款，" +
                    "尤其是:\n" +
                    " 1.我们对您的个人信息的收集/保存/使用/对外提供/保护等规则条款，以及您的用户权利等条款;\n" +
                    " 2. 约定我们的限制责任、免责条款;\n" +
                    " 3.其他以颜色或加粗进行标识的重要条款。\n" +
                    "您点击“同意并继续”的行为即表示您已阅读完毕并同意以上协议的全部内容。" +
                    "如您同意以上协议内容，请点击“同意”，开始使用我们的产品和服务!";

            val ssb = SpannableStringBuilder();
            ssb.append(str);
            val start = str.indexOf("《");//第一个出现的位置
            ssb.setSpan(object : ClickableSpan() {

                override fun onClick(widget: View) {
                    mPresenter?.getProtocol(2)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = getResources().getColor(R.color.blue_500);
                    ds.isUnderlineText = false;
                }
            }, start, start + 6, 0);

            val end = str.lastIndexOf("《");
            ssb.setSpan(object : ClickableSpan() {

                override fun onClick(widget: View) {
                    mPresenter?.getProtocol(1)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = getResources().getColor(R.color.blue_500);
                    ds.isUnderlineText = false;
                }
            }, end, end + 6, 0);

            tvContent.movementMethod = LinkMovementMethod.getInstance();
            tvContent.setText(ssb, TextView.BufferType.SPANNABLE);


            tvCancel.setOnClickListener {
                alertDialog.cancel();
                finish();
            };

            tvAgree.setOnClickListener {
                isFirst = false
                alertDialog.cancel();
            }

        }

    }
}