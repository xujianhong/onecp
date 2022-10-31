package com.daomingedu.talentgame.mvp.ui

import android.os.Bundle
import com.daomingedu.onecp.app.onClick
import com.daomingedu.talentgame.R
import com.daomingedu.talentgame.di.component.DaggerFeedbackComponent
import com.daomingedu.talentgame.di.module.FeedbackModule
import com.daomingedu.talentgame.mvp.contract.FeedbackContract
import com.daomingedu.talentgame.mvp.presenter.FeedbackPresenter
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_feedback.*

/**
 * Description
 * Created by jianhongxu on 4/8/21
 */
class FeedbackActivity : BaseActivity<FeedbackPresenter>(), FeedbackContract.View {
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerFeedbackComponent.builder().appComponent(appComponent)
            .feedbackModule(FeedbackModule(this)).build().inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_feedback
    }

    override fun initData(savedInstanceState: Bundle?) {
        title = "意见反馈"

        btnFeedback.onClick {
            if(etFeedback.text.toString().isEmpty()){
                showMessage("请输入反馈内容")
                return@onClick
            }

            mPresenter?.feedback(etFeedback.text.toString())
        }
    }

    override fun requestFeedbackSuccess() {
        showMessage("提交成功")
        finish()
    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }
}