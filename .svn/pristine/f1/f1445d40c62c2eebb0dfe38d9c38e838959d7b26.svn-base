package com.daomingedu.onecp.mvp.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.daomingedu.onecp.R
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.Preference
import com.daomingedu.onecp.di.component.DaggerProvinceComponent
import com.daomingedu.onecp.di.module.ProvinceModule
import com.daomingedu.onecp.mvp.contract.ProvinceContract
import com.daomingedu.onecp.mvp.model.entity.ProvinceBean
import com.daomingedu.onecp.mvp.presenter.ProvincePresenter
import com.daomingedu.onecp.mvp.ui.adapter.ProvinceAdapter
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_province.*
import kotlinx.android.synthetic.main.include_title.*
import org.jetbrains.anko.startActivity
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * Created by jianhongxu on 3/16/21
 * Description 省市区选择
 */
class ProvinceAct : BaseActivity<ProvincePresenter>(), ProvinceContract.View {
    @Inject
    lateinit var mAdapter: ProvinceAdapter

    //选择省市区三次跳转
    var provinceTime: Int = 0

    var provinceNumber: String? = null
    var cityNumber: String? = null


    var strShow:String? =null



    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerProvinceComponent
            .builder()
            .appComponent(appComponent)
            .provinceModule(ProvinceModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_province
    }

    override fun initData(savedInstanceState: Bundle?) {

        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = mAdapter.apply {
            setOnItemClickListener { _, _, position ->
                mAdapter.getItem(position)?.apply {
//                    Timber.d(this.id + " " + this.name)



                    provinceTime++;
                    when (provinceTime) {
                        1 -> {

                            provinceNumber = this.id;
                            strShow =this.name
                            mPresenter?.province(this.id,Constant.KEY)


                        }
                        2 -> {

                            cityNumber =this.id
                            strShow+=this.name
                            mPresenter?.province(this.id,Constant.KEY)

                        }
                        else
                        ->{
                            strShow+=this.name
                            var intent = Intent()
                            intent.putExtra(Constant.PROVINCE_EXTRA,provinceNumber)
                            intent.putExtra(Constant.CITY_EXTRA,cityNumber)
                            intent.putExtra(Constant.REGION_EXTRA,this.id)
                            intent.putExtra(Constant.PROVINCE_SHOW_EXTRA,strShow)
                            setResult(Activity.RESULT_OK,intent)
                            killMyself()
                        }


                    }


                }
            }
        }

        provinceTime = intent.getIntExtra(Constant.PROVINCE_TIME_EXTRA, 0)

        title = "选择省市区"

        mPresenter?.province("0",Constant.KEY)


    }


    override fun requestProvinceSuccess() {

    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun killMyself() {
        finish()
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }
}