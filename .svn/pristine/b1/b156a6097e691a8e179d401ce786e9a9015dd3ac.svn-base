package com.daomingedu.onecp.mvp.ui.activity

import android.os.Bundle
import com.daomingedu.onecp.R
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.JHCImageConfig
import com.daomingedu.onecp.app.loadImage
import com.daomingedu.onecp.di.component.DaggerCheckGradeComponent
import com.daomingedu.onecp.di.module.CheckGradeModule
import com.daomingedu.onecp.mvp.contract.CheckGradeContract
import com.daomingedu.onecp.mvp.model.entity.TestSignResultBean
import com.daomingedu.onecp.mvp.presenter.CheckGradePresenter
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_check_grade.*

/**
 * Created by jianhongxu on 3/19/21
 * Description 查看成绩
 */
class CheckGradeAct : BaseActivity<CheckGradePresenter>(), CheckGradeContract.View {
    override fun setupActivityComponent(appComponent: AppComponent) {

        DaggerCheckGradeComponent
            .builder()
            .appComponent(appComponent)
            .checkGradeModule(CheckGradeModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_check_grade
    }

    override fun initData(savedInstanceState: Bundle?) {
        val testId = intent.getStringExtra(Constant.ENROLL_TEST_EXTRA)

        mPresenter?.getTestSignResult(testId)
    }

    override fun requestTestSignResult(data: TestSignResultBean?) {
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
            tvName.text = studentName
            tvTestName.text = testName
            tvScore.text = attendScore
            tvMark.text = attendComment
        }
    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

}