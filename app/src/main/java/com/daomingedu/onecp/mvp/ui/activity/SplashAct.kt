package com.daomingedu.onecp.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.daomingedu.onecp.R
import com.daomingedu.onecp.di.component.DaggerSplashComponent
import com.daomingedu.onecp.di.module.SplashModule
import com.daomingedu.onecp.mvp.contract.SplashContract
import com.daomingedu.onecp.mvp.model.entity.SessionIdBean
import com.daomingedu.onecp.mvp.presenter.SplashPresenter
import org.jetbrains.anko.startActivity
import timber.log.Timber
import java.util.*

class SplashAct : BaseActivity<SplashPresenter>(), SplashContract.View {
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerSplashComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .splashModule(SplashModule(this))
            .build()
            .inject(this)

    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_splash
    }

    override fun initData(savedInstanceState: Bundle?) {
        mPresenter?.checkSessionId()
    }

    override fun requestCheckSessionIdSuccess(data: SessionIdBean) {
        when (data.status) {
            "ok" -> {
                Timber.e("进入主页")
                startActivity<MainAct>()
                overridePendingTransition(0,0)
                killMyself()
            }
            else -> {
                startActivity<LoginAct>()
                overridePendingTransition(0,0)
                killMyself()
            }
        }
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
}